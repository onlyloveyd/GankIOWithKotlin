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
package onlyloveyd.com.gankioclient.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import onlyloveyd.com.gankioclient.fragment.AboutFragment
import onlyloveyd.com.gankioclient.fragment.DailyFragment
import onlyloveyd.com.gankioclient.fragment.MindFragment
import onlyloveyd.com.gankioclient.fragment.SortFragment
import onlyloveyd.com.gankioclient.utils.Constant

/**
 * 文 件 名: GankAdapter
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：主界面ViewPagerAdapter
 */
class GankAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return DailyFragment.newInstance()
            1 -> return SortFragment.newInstance()
            2 -> return MindFragment.newInstance()
            3 -> return AboutFragment.newInstance()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return Constant.sTabTitles.size
    }
}
