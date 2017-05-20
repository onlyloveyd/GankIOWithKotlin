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
package onlyloveyd.com.gankioclient.activity

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_web.*
import onlyloveyd.com.gankioclient.R

/**
 * 文 件 名: WebActivity
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：内部网页显示Activity
 */
class WebActivity : AppCompatActivity() {

    private var URL: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        ButterKnife.bind(this)

        val intent = intent
        val bundle = intent.extras
        if (bundle != null) {
            URL = bundle.getString("URL")
        }

        setSupportActionBar(tl_web)
        tl_web.setNavigationIcon(R.drawable.back)
        tl_web.setTitleTextAppearance(this, R.style.ToolBarTextAppearance)
        initWebViewSettings()

        wv_content.removeJavascriptInterface("searchBoxJavaBridge_")
        wv_content.removeJavascriptInterface("accessibilityTraversal")
        wv_content.removeJavascriptInterface("accessibility")
        wv_content.loadUrl(URL)
    }

    public override fun onPause() {
        super.onPause()
        if (wv_content != null) wv_content.onPause()
    }

    public override fun onResume() {
        super.onResume()
        if (wv_content != null) wv_content.onResume()
    }

    public override fun onDestroy() {
        super.onDestroy()
        if (wv_content != null) wv_content.destroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.web_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.share -> {//share url with system share windows
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, URL)
                startActivity(Intent.createChooser(intent, title))
            }
            R.id.openinbrowse -> {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.data = Uri.parse(URL)
                startActivity(intent)
            }
            R.id.copyurl -> {
                val clipboardManager = getSystemService(
                        Context.CLIPBOARD_SERVICE) as ClipboardManager
                clipboardManager.text = URL
                Snackbar.make(tl_web, "已复制到剪切板", Snackbar.LENGTH_SHORT).show()
            }
            R.id.refresh -> {
                wv_content.reload()
            }
            else -> {
            }
        }
        return true
    }

    private fun initWebViewSettings() {
        val settings = wv_content.settings
        settings.javaScriptEnabled = true
        settings.loadWithOverviewMode = true
        settings.setAppCacheEnabled(true)
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        settings.setSupportZoom(true)
        settings.savePassword = false
        wv_content.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                progressbar!!.progress = newProgress
                if (newProgress == 100) {
                    progressbar!!.visibility = View.GONE
                } else {
                    progressbar!!.visibility = View.VISIBLE
                }
            }


            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, title)
                setTitle(title)
            }
        })
        wv_content.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                if (url != null) view.loadUrl(url)
                return true
            }
        })
    }
}