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
package onlyloveyd.com.gankioclient.viewholder

import android.text.TextUtils
import android.view.View
import android.widget.TextView

import onlyloveyd.com.gankioclient.R
import onlyloveyd.com.gankioclient.data.MindData
import onlyloveyd.com.gankioclient.utils.PublicTools

/**
 * 文 件 名: MindViewHoder
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：单类数据ViewHolder
 */
class MindViewHolder(itemView: View) : BaseViewHolder<MindData>(itemView) {

    override fun bindViewData(data: MindData) {

        if (data != null) {
            val tvTitle = getView(R.id.tv_title) as TextView
            val tvAuthor = getView(R.id.tv_author) as TextView
            val tvDate = getView(R.id.tv_date) as TextView
            // 标题
            if (TextUtils.isEmpty(data.title)) {
                tvTitle.text = ""
            } else {
                tvTitle.text = data.title.trim { it <= ' ' }
            }
            // 时间
            if (data.time == null) {
                tvDate.text = ""
            } else {
                val time = data.time
                if (time == null) {
                    tvDate.text = ""
                } else {
                    tvDate.text = time
                }
            }

            // 作者
            if (TextUtils.isEmpty(data.author)) {
                tvAuthor.text = ""
            } else {
                tvAuthor.text = data.author
            }

            itemView.setOnClickListener { PublicTools.startWebActivity(itemView.context, data.url) }
        }
    }
}
