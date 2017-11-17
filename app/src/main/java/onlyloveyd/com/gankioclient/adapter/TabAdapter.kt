/*
 * Copyright (C) 2015 Paul Burke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
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
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter

import onlyloveyd.com.gankioclient.fragment.GankFragment
import onlyloveyd.com.gankioclient.utils.Constant

/**
 * 文 件 名: TabAdapter
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：分类数据显示界面SlidingTablayout PagerAdapter
 */
class TabAdapter
//福利 | Android | iOS | 休息视频 | 拓展资源 | 前端

(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = GankFragment.newInstance(Constant.sCategoryList[position])


    override fun getCount(): Int = Constant.sCategoryList.size


    override fun getPageTitle(position: Int): CharSequence = Constant.sCategoryList[position]


    override fun getItemPosition(`object`: Any): Int = PagerAdapter.POSITION_NONE
}
