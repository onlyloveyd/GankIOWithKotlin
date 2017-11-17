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
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_gank.*
import onlyloveyd.com.gankioclient.data.TypeData
import onlyloveyd.com.gankioclient.http.HttpMethods

/**
 * 文 件 名: GankFragment
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：分类数据界面下每个数据显示界面
 */
open class GankFragment : BaseFragment() {
    internal var pagenum = 1

    override fun initBGAData() {
        rl_gank_refresh.beginRefreshing()
    }

    fun getContent(category: String, pagenum: Int) {
        val observer = object : Observer<TypeData> {
            override fun onComplete() {
                endLoading()
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                endLoading()
                onNetworkError()
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(httpBean: TypeData) {
                if(rl_gank_refresh==null) {
                    return
                }
                if (rl_gank_refresh.isLoadingMore()) {
                } else {
                    mVisitableList.clear()
                }
                if (httpBean.results == null || httpBean.results.size == 0) {
                    onDataEmpty()
                } else {
                    mVisitableList.addAll(httpBean.results)
                }
                mMultiRecyclerAdapter?.data = mVisitableList
            }
        }
        HttpMethods.instance.getData(observer, category, "10", pagenum)
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout) {
        getContent(arg!!, 1)
    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout): Boolean {
        getContent(arg!!, ++pagenum)
        return true
    }

    companion object {

        fun newInstance(arg: String): GankFragment {
            val args = Bundle()
            args.putString("ARG", arg)
            val fragment = GankFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
