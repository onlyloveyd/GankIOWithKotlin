package onlyloveyd.com.gankioclient.http

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.widget.ProgressBar
import onlyloveyd.com.gankioclient.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

/**
 * Copyright 2017 yidong
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class UpdateManager(private val mContext: Context) {


    private var mSavePath: String? = null  // 下载保存路径
    private var progress: Int = 0      // 记录进度条数量
    private var cancelUpdate = false   // 是否取消更新
    private var mProgress: ProgressBar? = null            // 更新进度条
    private var mDownloadDialog: Dialog? = null          // 下载对话框
    private var apkName: String? = null                   // 程序名
    private var downUrl: String? = null                   // 程序下载地址

    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                DOWNLOAD  // 正在下载
                -> mProgress!!.progress = progress  // 设置进度条位置
                DOWNLOAD_FINISH -> installApk()  // 安装文件
                else -> {
                }
            }
        }
    }

    fun setDownUrl(url: String) {
        downUrl = url
    }

    fun setApkName(name: String) {
        apkName = name
    }


    /**
     * 显示软件下载对话框
     */
    fun showDownloadDialog() {
        val builder = AlertDialog.Builder(mContext)
        builder.setTitle("正在更新...")

        // 给下载对话框增加进度条
        val inflater = LayoutInflater.from(mContext)
        val v = inflater.inflate(R.layout.dialog_update_progress, null)
        mProgress = v.findViewById(R.id.update_progress) as ProgressBar
        builder.setView(v)
        // 取消更新
        builder.setNegativeButton("取消更新") { dialog, which ->
            dialog.dismiss()
            cancelUpdate = true  // 设置取消状态
        }
        builder.setCancelable(false)
        mDownloadDialog = builder.create()
        mDownloadDialog!!.show()

        downloadApk()   // 下载文件文件
    }

    /**
     * 下载apk文件
     */
    private fun downloadApk() {
        // 启动新线程下载软件
        downloadApkThread().start()
    }

    /**
     * 下载文件线程

     * @author coolszy
     * *
     * @date 2012-4-26
     * *
     * @blog http://blog.92coding.com
     */
    private inner class downloadApkThread : Thread() {
        override fun run() {
            try {
                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                    // 获得存储卡的路径
                    val sdpath = Environment.getExternalStorageDirectory().toString() + "/"
                    mSavePath = sdpath + "download"
                    val url = URL(downUrl)

                    // 创建连接
                    val conn = url.openConnection() as HttpURLConnection
                    conn.connect()
                    // 获取文件大小
                    val length = conn.contentLength
                    // 创建输入流
                    val `is` = conn.inputStream

                    val file = File(mSavePath!!)
                    // 判断文件目录是否存在
                    if (!file.exists()) {
                        file.mkdir()
                    }
                    val apkFile = File(mSavePath, apkName!!)
                    if (!apkFile.exists()) {
                        apkFile.createNewFile()
                    }
                    val fos = FileOutputStream(apkFile)
                    var count = 0
                    // 缓存
                    val buf = ByteArray(1024)
                    // 写入到文件中
                    do {
                        val numread = `is`.read(buf)
                        count += numread
                        // 计算进度条位置
                        progress = (count.toFloat() / length * 100).toInt()
                        // 更新进度
                        mHandler.sendEmptyMessage(DOWNLOAD)
                        if (numread <= 0) {
                            mHandler.sendEmptyMessage(DOWNLOAD_FINISH)  // 下载完成
                            break
                        }
                        fos.write(buf, 0, numread)  // 写入文件
                    } while (!cancelUpdate)// 点击取消就停止下载.
                    fos.close()
                    `is`.close()
                }
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            mDownloadDialog!!.dismiss()  // 取消下载对话框显示
        }
    }

    /**
     * 安装APK文件
     */
    private fun installApk() {
        val apkfile = File(mSavePath, apkName!!)
        if (!apkfile.exists()) {
            return
        }
        // 通过Intent安装APK文件
        val i = Intent(Intent.ACTION_VIEW)
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
                "application/vnd.android.package-archive")
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        mContext.startActivity(i)
    }


    companion object {
        private val DOWNLOAD = 1  // 下载中
        private val DOWNLOAD_FINISH = 2   // 下载结束
    }
}
