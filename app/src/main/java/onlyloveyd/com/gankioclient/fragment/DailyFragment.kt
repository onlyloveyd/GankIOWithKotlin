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
import onlyloveyd.com.gankioclient.BuildConfig
import onlyloveyd.com.gankioclient.activity.GankActivity
import onlyloveyd.com.gankioclient.data.DailyData
import onlyloveyd.com.gankioclient.decorate.OnDatePickedListener
import onlyloveyd.com.gankioclient.http.HttpMethods
import onlyloveyd.com.gankioclient.utils.Constant
import java.util.*

/**
 * 文 件 名: DailyFragment
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：每日干货数据
 */
class DailyFragment : BaseFragment(), OnDatePickedListener {

    override fun initBGAData() {
        (activity as GankActivity).setOnDatePickedListener(this)
        rl_gank_refresh.beginRefreshing()
    }

    private fun getDaily(year: Int, month: Int, day: Int) {
        val observer = object : Observer<DailyData> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onComplete() {
                endLoading()
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                endLoading()
                onNetworkError()
            }

            override fun onNext(dailyData: DailyData) {
                if (rl_gank_refresh.isLoadingMore()) {
                } else {
                    mVisitableList.clear()
                }
                if (dailyData.category == null || dailyData.category.size == 0) {
                    onDataEmpty()
                } else {
                    dailyData.results.android?.let { mVisitableList.addAll(it) }
                    dailyData.results.app?.let { mVisitableList.addAll(it) }
                    dailyData.results.bonus?.let { mVisitableList.addAll(it) }
                    dailyData.results.ios?.let { mVisitableList.addAll(it) }
                    dailyData.results.js?.let { mVisitableList.addAll(it) }
                    dailyData.results.rec?.let { mVisitableList.addAll(it) }
                    dailyData.results.res?.let { mVisitableList.addAll(it) }
                    dailyData.results.video?.let { mVisitableList.addAll(it) }
                }
                mMultiRecyclerAdapter?.data = mVisitableList;
            }
        }
        HttpMethods.instance.getDailyData(observer, year, month, day)
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout) {
        doRefresh()
    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout): Boolean {
        return false
    }


    override fun onDatePicked(year: Int, month: Int, day: Int) {
        rl_gank_refresh.beginRefreshing()
        // getDaily(year,  month+1, day);
    }

    private fun doRefresh() {
        if (BuildConfig.YLog) {
            System.err.println(
                    "yidong --year= " + Constant.YEAR + " Month = " + Constant.MONTH + " day = "
                            + Constant.DAY)
        }
        if (Constant.YEAR == -1 && Constant.MONTH == -1 && Constant.DAY == -1) {
            val date = Date(System.currentTimeMillis())
            getDaily(date.year + 1900, date.month + 1, date.date)
        } else {
            getDaily(Constant.YEAR, Constant.MONTH + 1, Constant.DAY)
        }
    }

    companion object {

        fun newInstance(): DailyFragment {

            val args = Bundle()

            val fragment = DailyFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
