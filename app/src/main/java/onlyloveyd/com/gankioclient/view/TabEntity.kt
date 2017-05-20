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
package onlyloveyd.com.gankioclient.view

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * 文 件 名: TabEntity
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：底部Tab自定义Entity,配合FlycoTabLayout使用
 */
class TabEntity(var title: String, var selectedIcon: Int, var unSelectedIcon: Int) : CustomTabEntity {

    override fun getTabTitle(): String = title

    override fun getTabSelectedIcon(): Int = selectedIcon

    override fun getTabUnselectedIcon(): Int = unSelectedIcon
}
