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
package onlyloveyd.com.gankioclient.viewholder

import android.graphics.Bitmap
import android.os.Environment
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import onlyloveyd.com.gankioclient.R
import onlyloveyd.com.gankioclient.data.CategoryData
import onlyloveyd.com.gankioclient.utils.Constant
import onlyloveyd.com.gankioclient.utils.PublicTools
import onlyloveyd.com.gankioclient.utils.RxPermissionUtils
import org.jetbrains.anko.toast
import java.io.File

/**
 * 文 件 名: BonusViewHolder
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：福利ViewHolder
 */
class BonusViewHolder(itemView: View) : BaseViewHolder<CategoryData>(itemView) {

    override fun bindViewData(data: CategoryData) {

        if (data != null) {
            val ivPic = getView(R.id.imgPicture) as ImageView
            val ibDownload = getView(R.id.ib_download) as ImageButton
            Glide.with(itemView.context).load(data.url).into(ivPic)

            val rxPermissions = RxPermissionUtils.instance
            ibDownload.setOnClickListener {
                if (rxPermissions != null) {
                    rxPermissions
                            .request(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe(object : Observer<Boolean> {
                                override fun onSubscribe(d: Disposable) {

                                }

                                override fun onNext(granted: Boolean?) {
                                    if (granted!!) { // Always true pre-M
                                        // I can control the camera now
                                        itemView.context.toast(data.desc +
                                                Constant.SUFFIX_JPEG + "开始下载")
                                        Glide.with(itemView.context).load(
                                                data.url).asBitmap().into(
                                                object : SimpleTarget<Bitmap>() {
                                                    override fun onResourceReady(
                                                            resource: Bitmap,
                                                            glideAnimation: GlideAnimation<in Bitmap>) {

                                                        val subscriber = object : Observer<Bitmap> {
                                                            override fun onSubscribe(
                                                                    d: Disposable) {

                                                            }

                                                            override fun onNext(
                                                                    s: Bitmap) {
                                                            }

                                                            override fun onError(
                                                                    e: Throwable) {

                                                                itemView.context.toast(data.desc +
                                                                        Constant.SUFFIX_JPEG
                                                                        +
                                                                        "下载失败")
                                                                e.printStackTrace()
                                                            }

                                                            override fun onComplete() {

                                                                itemView.context.toast(data.desc +
                                                                        Constant.SUFFIX_JPEG
                                                                        +
                                                                        "下载成功")
                                                            }
                                                        }

                                                        val observable = Observable.create(
                                                                ObservableOnSubscribe<Bitmap> { emitter ->
                                                                    try {
                                                                        PublicTools.saveBitmap(
                                                                                resource,
                                                                                Environment
                                                                                        .getExternalStorageDirectory().absolutePath
                                                                                        + File.separator
                                                                                        + data.desc)
                                                                    } catch (e: Exception) {
                                                                        e.printStackTrace()
                                                                        emitter.onError(
                                                                                e)
                                                                    }

                                                                    emitter.onComplete()
                                                                })

                                                        observable.subscribeOn(Schedulers.io())
                                                                .unsubscribeOn(Schedulers.io())
                                                                .observeOn(
                                                                        AndroidSchedulers
                                                                                .mainThread())
                                                                .subscribe(subscriber)
                                                    }
                                                })
                                    } else {
                                        itemView.context.toast("请在设置中开启存储权限后再试")
                                    }
                                }

                                override fun onError(e: Throwable) {

                                }

                                override fun onComplete() {

                                }
                            })
                }
            }
        }
    }
}
