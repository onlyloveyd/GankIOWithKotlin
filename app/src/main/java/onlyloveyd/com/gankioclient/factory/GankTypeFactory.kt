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
import onlyloveyd.com.gankioclient.R
import onlyloveyd.com.gankioclient.gsonbean.DailyBean
import onlyloveyd.com.gankioclient.gsonbean.EmptyBean
import onlyloveyd.com.gankioclient.gsonbean.MindBean
import onlyloveyd.com.gankioclient.gsonbean.ResultsBean
import onlyloveyd.com.gankioclient.utils.Constant
import onlyloveyd.com.gankioclient.viewholder.*

/**
 * 文 件 名: GankTypeFactory
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：MultiType数据工厂实现
 */
class GankTypeFactory : TypeFactory {

    override fun type(dailyBean: DailyBean.ResultsBean.DetailsBean): Int = DAILY_ITEM_LAYOUT

    override fun type(contentBean: ResultsBean): Int {
        if (contentBean.type == Constant.BONUS) {
            return BONUS_ITEM_LAYOUT
        } else {
            return DATA_ITEM_LAYOUT
        }
    }

    override fun type(emptyBean: EmptyBean): Int = EMPTY_ITEM_LAYOUT

    override fun type(mindBean: MindBean): Int = MIND_ITEM_LAYOUT

    override fun createViewHolder(type: Int, itemView: View): BaseViewHolder<*> {
        when (type) {
            DAILY_ITEM_LAYOUT -> return DailyViewHolder(itemView)
            DATA_ITEM_LAYOUT -> return DataViewHolder(itemView)
            BONUS_ITEM_LAYOUT -> return BonusViewHolder(itemView)
            EMPTY_ITEM_LAYOUT -> return EmptyViewHolder(itemView)
            MIND_ITEM_LAYOUT -> return MindViewHolder(itemView)
            else -> return null!!
        }
    }

    companion object {

        val DAILY_ITEM_LAYOUT = R.layout.rv_item_daily
        val DATA_ITEM_LAYOUT = R.layout.rv_item_text
        val BONUS_ITEM_LAYOUT = R.layout.rv_item_image
        val EMPTY_ITEM_LAYOUT = R.layout.rv_item_empty
        val MIND_ITEM_LAYOUT = R.layout.rv_item_mind
    }
}
