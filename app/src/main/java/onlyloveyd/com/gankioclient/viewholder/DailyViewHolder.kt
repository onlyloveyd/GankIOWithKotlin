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
import onlyloveyd.com.gankioclient.gsonbean.DailyBean
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
class DailyViewHolder(itemView: View) : BaseViewHolder<DailyBean.ResultsBean.DetailsBean>(itemView) {

    override fun bindViewData(data: DailyBean.ResultsBean.DetailsBean) {
        if (data != null) {
            val ivDaily = getView(R.id.iv_daily) as ImageView
            val tvTitleDaily = getView(R.id.tv_title_daily) as TextView
            val tvTypeDaily = getView(R.id.tv_type_daily) as TextView
            val tvDateDaily = getView(R.id.tv_date_daily) as TextView

            tvTitleDaily.setText(data!!.getDesc().trim({ it <= ' ' }))
            tvDateDaily.text = PublicTools.date2String(data!!.getPublishedAt().getTime(), "yyyy.MM.dd")

            if (data!!.getImages() != null && data!!.getImages().size > 0) {
                Glide.with(itemView.context)
                        .load(data!!.getImages().get(0))
                        .placeholder(R.mipmap.img_default_gray)
                        .into(ivDaily)
            } else {
                if (data!!.getType() == "福利") {
                    Glide.with(itemView.context)
                            .load(data!!.getUrl())
                            .placeholder(R.mipmap.img_default_gray)
                            .into(ivDaily)
                } else {
                    ivDaily.visibility = View.GONE
                }
            }
            val type = data!!.getType()
            tvTypeDaily.setText(type)
            Constant.sTypeColor[type]?.let { tvTypeDaily.setBackgroundResource(it) }

            itemView.setOnClickListener { PublicTools.startWebActivity(itemView.context, data!!.getUrl()) }
        }

    }
}
