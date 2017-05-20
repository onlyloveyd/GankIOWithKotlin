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
import onlyloveyd.com.gankioclient.gsonbean.ResultsBean
import onlyloveyd.com.gankioclient.utils.Constant
import onlyloveyd.com.gankioclient.utils.PublicTools
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * 文 件 名: DataViewHolder
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：单类数据ViewHolder
 */
class DataViewHolder(itemView: View) : BaseViewHolder<ResultsBean>(itemView) {

    override fun bindViewData(data: ResultsBean) {

        if (data != null) {
            val tvTitle = getView(R.id.tv_title) as TextView
            val tvAuthor = getView(R.id.tv_author) as TextView
            val tvDate = getView(R.id.tv_date) as TextView
            val tvType = getView(R.id.tv_type) as TextView
            // 标题
            if (TextUtils.isEmpty(data.desc)) {
                tvTitle.text = ""
            } else {
                tvTitle.text = data.desc.trim { it <= ' ' }
            }
            // 时间
            if (data.publishedAt == null) {
                tvDate.text = ""
            } else {
                var time = data.publishedAt
                time = time.substring(0, 19).replace("T", " ")
                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                var date: Date? = null
                try {
                    date = sdf.parse(time)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }

                if (date == null) {
                    tvDate.text = ""
                } else {
                    tvDate.text = PublicTools.getTimestampString(date)
                }
            }

            // 作者
            if (TextUtils.isEmpty(data.who)) {
                tvAuthor.text = ""
            } else {
                tvAuthor.text = data.who
            }

            if (TextUtils.isEmpty(data.type)) {
                tvType.text = ""
            } else {
                tvType.text = data.type
                Constant.sTypeColor[data.type]?.let {
                    tvType.setBackgroundResource(
                            it)
                }
            }

            itemView.setOnClickListener { PublicTools.startWebActivity(itemView.context, data.url) }
        }
    }
}
