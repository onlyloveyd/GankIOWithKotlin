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

import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide

import onlyloveyd.com.gankioclient.R
import onlyloveyd.com.gankioclient.data.DetailsData
import onlyloveyd.com.gankioclient.utils.Constant
import onlyloveyd.com.gankioclient.utils.PublicTools


/**
 * 文 件 名: DailyViewHolder
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：每日干货ViewHolder
 */
class DailyViewHolder(itemView: View) : BaseViewHolder<DetailsData>(itemView) {

    override fun bindViewData(data: DetailsData) {
        if (data != null) {
            val ivDaily = getView(R.id.iv_daily) as ImageView
            val tvTitleDaily = getView(R.id.tv_title_daily) as TextView
            val tvTypeDaily = getView(R.id.tv_type_daily) as TextView
            val tvDateDaily = getView(R.id.tv_date_daily) as TextView

            tvTitleDaily.setText(data.desc.trim({ it <= ' ' }))
            tvDateDaily.text = PublicTools.date2String(data.publishedAt.getTime(), "yyyy.MM.dd")

            if (data.images != null && data.images.size > 0) {
                Glide.with(itemView.context)
                        .load(data.images.get(0))
                        .placeholder(R.mipmap.img_default_gray)
                        .into(ivDaily)
            } else {
                if (data.type == "福利") {
                    Glide.with(itemView.context)
                            .load(data.url)
                            .placeholder(R.mipmap.img_default_gray)
                            .into(ivDaily)
                } else {
                    ivDaily.visibility = View.GONE
                }
            }
            val type = data.type
            tvTypeDaily.setText(type)
            Constant.sTypeColor[type]?.let { tvTypeDaily.setBackgroundResource(it) }

            itemView.setOnClickListener { PublicTools.startWebActivity(itemView.context, data.url) }
        }

    }
}
