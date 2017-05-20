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
package onlyloveyd.com.gankioclient.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import onlyloveyd.com.gankioclient.decorate.Visitable
import onlyloveyd.com.gankioclient.factory.GankTypeFactory
import onlyloveyd.com.gankioclient.factory.TypeFactory
import onlyloveyd.com.gankioclient.viewholder.BaseViewHolder

/**
 * 文 件 名: MultiRecyclerAdapter
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：通用Adapter
 */
class MultiRecyclerAdapter(internal var mData: List<Visitable>?) : RecyclerView.Adapter<BaseViewHolder<Any>>() {
    internal var typeFactory: TypeFactory

    init {
        this.typeFactory = GankTypeFactory()
    }

    var data: List<Visitable>?
        get() = mData
        set(data) {
            mData = data
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Any> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return typeFactory.createViewHolder(viewType, view) as BaseViewHolder<Any>
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Any>, position: Int) {
        mData?.get(position)?.let { holder.bindViewData(it) }
    }

    override fun getItemViewType(position: Int): Int = mData!![position].type(typeFactory)


    override fun getItemCount(): Int = if (mData != null) mData!!.size else 0
}
