/*
 * Copyright (C) 2015 Paul Burke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package onlyloveyd.com.gankioclient.adapter

import android.content.Context
import android.support.v4.view.MotionEventCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import onlyloveyd.com.gankioclient.R
import onlyloveyd.com.gankioclient.decorate.ItemTouchHelperAdapter
import onlyloveyd.com.gankioclient.decorate.ItemTouchHelperViewHolder
import onlyloveyd.com.gankioclient.decorate.OnStartDragListener
import onlyloveyd.com.gankioclient.utils.Constant
import java.util.*

/**
 * 文 件 名: OrderAdapter
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：分类数据排序界面RecyclerView adapter
 */
class OrderAdapter(private val mContext: Context, private val mDragStartListener: OnStartDragListener) : RecyclerView.Adapter<OrderAdapter.ItemViewHolder>(), ItemTouchHelperAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_sort, parent,
                false)
        val itemViewHolder = ItemViewHolder(view)
        return itemViewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.textView.text = Constant.sCategoryList[position]
        if (Constant.sTypeColor[Constant.sCategoryList[position]] != null) {
            Constant.sTypeColor[Constant.sCategoryList[position]]?.let {
                holder.itemView.setBackgroundResource(
                        it)
            }
        }

        // Start a drag whenever the handle view it touched
        holder.handleView.setOnTouchListener { v, event ->
            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                mDragStartListener.onStartDrag(holder)
            }
            false
        }
    }

    override fun onItemDismiss(position: Int) {
        Constant.sCategoryList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(Constant.sCategoryList, fromPosition, toPosition)
        Constant.sCategryListChanged = true
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun getItemCount(): Int {
        return Constant.sCategoryList.size
    }

    /**
     * Simple example of a view holder that implements [ItemTouchHelperViewHolder] and has a
     * "handle" view that initiates a drag event when touched.
     */
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ItemTouchHelperViewHolder {

        val textView: TextView
        val handleView: ImageView

        init {
            textView = itemView.findViewById(R.id.text) as TextView
            handleView = itemView.findViewById(R.id.handle) as ImageView
        }

        override fun onItemSelected() {

            //itemView.setBackgroundColor(Color.LTGRAY);
        }

        override fun onItemClear() {
            //itemView.setBackgroundColor(0);
        }
    }
}
