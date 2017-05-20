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

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View


/**
 * 文 件 名: BaseViewHolder
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：ViewHolder 泛型基类
 */
abstract class BaseViewHolder<T>(internal var mItemView: View) : RecyclerView.ViewHolder(mItemView) {
    internal var mViews: SparseArray<View>

    init {
        mViews = SparseArray<View>()
    }

    fun getView(resId: Int): View {
        var view: View? = mViews.get(resId)

        if (view == null) {
            view = mItemView.findViewById(resId)
            mViews.put(resId, view)
        }
        return view as View
    }

    abstract fun bindViewData(data: T)
}