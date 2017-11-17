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
package onlyloveyd.com.gankioclient.fragment

import android.os.Bundle
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_gank.*
import onlyloveyd.com.gankioclient.data.MindData
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import java.util.*

/**
 * 文 件 名: MindFragment
 * 创 建 人: 易冬
 * 创建日期: 2017/5/11 16:39
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
class MindFragment : BaseFragment() {

    override fun initBGAData() {
        rl_gank_refresh.beginRefreshing()
    }

    fun getContent(category: String?, pagenum: Int) {
        val observer = object : Observer<ArrayList<MindData>> {
            override fun onError(e: Throwable) {
                e.printStackTrace()
                endLoading()
                onNetworkError()
            }

            override fun onComplete() {
                endLoading()
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(mindDataArrayList: ArrayList<MindData>) {
                if (rl_gank_refresh == null) {
                    return
                }
                if (rl_gank_refresh.isLoadingMore()) {
                } else {
                    mVisitableList.clear()
                }
                if (mindDataArrayList.size == 0) {
                    onDataEmpty()
                } else {
                    mVisitableList.addAll(mindDataArrayList)
                }
                mMultiRecyclerAdapter?.data = mVisitableList;

            }
        }

        val observable = Observable.create(
                ObservableOnSubscribe<ArrayList<MindData>> { emitter ->
                    val mindDataArrayList = ArrayList<MindData>()
                    try {
                        var doc: Document? = null
                        try {
                            doc = Jsoup.connect("http://gank.io/post/published").get()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }

                        val trs = doc!!.select("table").select("tr")
                        for (i in trs.indices) {
                            var data = MindData("", "", "")
                            val time = trs[i].select("td")[1]
                            data.time = time.text()

                            val detail = trs[i].select("td")[0]
                            val url = detail.select("a").attr("href")
                            data.url = url
                            data.title = detail.select("a").text()
                            data.author = detail.select("small").text()
                            mindDataArrayList.add(data)
                            System.err.println("yidong data = " + data)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        emitter.onError(e)
                    }

                    emitter.onNext(mindDataArrayList)
                    emitter.onComplete()
                })

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout) {
        getContent(arg, 1)
    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout): Boolean {
        return false
    }

    companion object {

        fun newInstance(): MindFragment {

            val args = Bundle()

            val fragment = MindFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
