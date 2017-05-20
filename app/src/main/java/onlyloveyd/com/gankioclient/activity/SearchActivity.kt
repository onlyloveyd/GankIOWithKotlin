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

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.widget.ArrayAdapter
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.OnTextChanged
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_search.*
import onlyloveyd.com.gankioclient.R
import onlyloveyd.com.gankioclient.adapter.MultiRecyclerAdapter
import onlyloveyd.com.gankioclient.data.SearchData
import onlyloveyd.com.gankioclient.decorate.Visitable
import onlyloveyd.com.gankioclient.http.HttpMethods
import onlyloveyd.com.gankioclient.utils.PublicTools
import java.util.*

/**
 * 文 件 名: SearchActivity
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：搜索Activity
 */
class SearchActivity : AppCompatActivity(), BGARefreshLayout.BGARefreshLayoutDelegate {

    internal var mMultiRecyclerAdapter: MultiRecyclerAdapter? = null
    internal var mVisitableList: MutableList<Visitable> = ArrayList()

    private var pageindex = 1
    private var keyword: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        ButterKnife.bind(this)
        setSupportActionBar(tl_search)
        tl_search.setNavigationIcon(R.drawable.back)

        initBGALayout()
        initRvContent()

        val adapter = ArrayAdapter.createFromResource(this,
                R.array.dummy_items, R.layout.spinner_item_text)
        adapter.setDropDownViewResource(R.layout.spinner_item_dropdown_list)

        sp_category.adapter = adapter
    }

    private fun initBGALayout() {
        // 为BGARefreshLayout 设置代理
        rl_search_content.setDelegate(this)
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        val refreshViewHolder = BGANormalRefreshViewHolder(this, true)
        refreshViewHolder.setLoadingMoreText("加载更多")
        refreshViewHolder.setLoadMoreBackgroundColorRes(R.color.white)
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.white)
        rl_search_content.setRefreshViewHolder(refreshViewHolder)
    }

    private fun initRvContent() {
        val llm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false)
        mMultiRecyclerAdapter = MultiRecyclerAdapter(null)
        rv_content.layoutManager = llm
        rv_content.adapter = mMultiRecyclerAdapter
    }

    @OnClick(R.id.tv_search)
    fun onClick() {
        PublicTools.hide_keyboard_from(this, et_search)
        refreshData()
    }

    private fun queryGanks(keyword: String, category: String, pageindex: Int) {
        val subscriber = object : Observer<SearchData> {
            override fun onComplete() {
                if (rl_search_content.isLoadingMore) {
                    rl_search_content.endLoadingMore()
                } else {
                    rl_search_content.endRefreshing()
                }
            }

            override fun onError(e: Throwable) {
                Snackbar.make(rv_content, "网络请求错误", Snackbar.LENGTH_SHORT).show()
                e.printStackTrace()
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(searchData: SearchData) {
                if (rl_search_content.isLoadingMore) {
                } else {
                    mVisitableList.clear()
                }
                mVisitableList.addAll(searchData.results)
                mMultiRecyclerAdapter?.data = mVisitableList
            }
        }
        HttpMethods.instance.searchData(subscriber, keyword, category, pageindex)
    }

    @OnTextChanged(R.id.et_search)
    fun onTextChange(text: CharSequence) {
        keyword = text.toString()
        if (text.toString() == null || text.length == 0) {
            tv_search.setTextColor(resources.getColor(R.color.colorPrimary))
            tv_search.isClickable = false
        } else {
            tv_search.setTextColor(resources.getColor(R.color.white))
            tv_search.isClickable = true
        }
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout) {
        refreshData()
    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout): Boolean {
        if (keyword != null && keyword!!.length > 0) {
            val category = sp_category!!.selectedItem as String
            queryGanks(keyword as String, category, ++pageindex)
        }
        return true
    }

    private fun refreshData() {
        pageindex = 1
        rl_search_content!!.beginRefreshing()
        if (keyword != null && keyword!!.length > 0) {
            val category = sp_category!!.selectedItem as String
            queryGanks(keyword as String, category, pageindex)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            else -> {
            }
        }
        return true
    }
}
