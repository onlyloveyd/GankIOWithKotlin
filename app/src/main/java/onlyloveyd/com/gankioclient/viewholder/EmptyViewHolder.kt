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
import android.widget.TextView

import onlyloveyd.com.gankioclient.R
import onlyloveyd.com.gankioclient.data.EmptyData

/**
 * 文 件 名: EmptyViewHolder
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 14:19
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：处理空数据和异常信息
 */
class EmptyViewHolder(itemView: View) : BaseViewHolder<EmptyData>(itemView) {

    override fun bindViewData(data: EmptyData) {
        val textView = getView(R.id.tv_empty) as TextView
        if (textView != null) {
            textView.text = data.message
        }
    }
}
