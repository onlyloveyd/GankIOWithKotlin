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
package onlyloveyd.com.gankioclient.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v4.app.TaskStackBuilder
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.MenuItem
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_sort.*
import onlyloveyd.com.gankioclient.R
import onlyloveyd.com.gankioclient.adapter.OrderAdapter
import onlyloveyd.com.gankioclient.decorate.OnStartDragListener
import onlyloveyd.com.gankioclient.decorate.SimpleItemTouchHelperCallback

/**
 * 文 件 名: OrderActivity
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：分类数据排序Activity
 */
class OrderActivity : AppCompatActivity(), OnStartDragListener {

    private var mItemTouchHelper: ItemTouchHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sort)

        setSupportActionBar(tl_sort)
        tl_sort.setNavigationIcon(R.drawable.back)
        tl_sort.setTitleTextAppearance(this, R.style.ToolBarTextAppearance)

        val adapter = OrderAdapter(this, this)

        rv_sort.setHasFixedSize(true)
        rv_sort.adapter = adapter
        rv_sort.layoutManager = LinearLayoutManager(this)

        val callback = SimpleItemTouchHelperCallback(adapter)
        mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper!!.attachToRecyclerView(rv_sort)
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        mItemTouchHelper!!.startDrag(viewHolder)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val upIntent = NavUtils.getParentActivityIntent(this)
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    TaskStackBuilder.create(this).addNextIntentWithParentStack(
                            upIntent).startActivities()
                } else {
                    upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    NavUtils.navigateUpTo(this, upIntent)
                }
            }
            else -> {
            }
        }
        return true
    }
}
