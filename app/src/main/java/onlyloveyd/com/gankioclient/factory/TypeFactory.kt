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
package onlyloveyd.com.gankioclient.factory

import android.view.View
import onlyloveyd.com.gankioclient.data.*

import onlyloveyd.com.gankioclient.viewholder.BaseViewHolder

/**
 * 文 件 名: TypeFactory
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：MultiType数据工厂接口
 */
interface TypeFactory {
    fun type(detailsData: DetailsData):Int
    fun type(categoryData: CategoryData):Int
    fun type(emptyData: EmptyData):Int
    fun type(mindData : MindData):Int

    fun createViewHolder(type: Int, itemView: View): BaseViewHolder<*>
}
