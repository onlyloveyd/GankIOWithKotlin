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
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_sort.*
import onlyloveyd.com.gankioclient.R
import onlyloveyd.com.gankioclient.adapter.TabAdapter
import onlyloveyd.com.gankioclient.utils.Constant

/**
 * 文 件 名: SortFragment
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：分类数据界面
 */
class SortFragment : Fragment() {


    private var tabAdapter: TabAdapter? = null
    private var mCurrentTag = "all"

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_sort, null, false)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabAdapter = TabAdapter(childFragmentManager)
        vp_view.adapter = tabAdapter
        indicator.setViewPager(vp_view)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        if (Constant.sCategryListChanged) {
            tabAdapter = null
            tabAdapter = TabAdapter(childFragmentManager)
            vp_view.removeAllViews()
            vp_view.adapter = tabAdapter
            indicator!!.setViewPager(vp_view)
            for (i in Constant.sCategoryList.indices) {
                if (Constant.sCategoryList[i] == mCurrentTag) {
                    vp_view.setCurrentItem(i, true)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        vp_view?.let { mCurrentTag = Constant.sCategoryList[it.currentItem] }
        Constant.sCategryListChanged = false
    }

    companion object {

        fun newInstance(): SortFragment {
            val args = Bundle()
            val fragment = SortFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
