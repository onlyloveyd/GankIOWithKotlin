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

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.activity_gank.*
import onlyloveyd.com.gankioclient.R
import onlyloveyd.com.gankioclient.adapter.GankAdapter
import onlyloveyd.com.gankioclient.decorate.OnDatePickedListener
import onlyloveyd.com.gankioclient.utils.Constant
import onlyloveyd.com.gankioclient.utils.PublicTools
import onlyloveyd.com.gankioclient.utils.RxPermissionUtils
import onlyloveyd.com.gankioclient.view.TabEntity
import org.jetbrains.anko.alert
import java.util.*

/**
 * 文 件 名: GankActivity
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:22
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：home Activity
 */
class GankActivity : AppCompatActivity() {


    private var mainMenu: Menu? = null

    private var mOnDatePickedListener: OnDatePickedListener? = null

    private val mIconUnselectIds = intArrayOf(R.mipmap.tab_daily_unselect, R.mipmap.tab_sort_unselect, R.mipmap.tab_bonus_unselect, R.mipmap.tab_about_unselect)
    private val mIconSelectIds = intArrayOf(R.mipmap.tab_daily_select, R.mipmap.tab_sort_select, R.mipmap.tab_bonus_select, R.mipmap.tab_about_select)
    private val mTabEntities = ArrayList<CustomTabEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gank)

        setSupportActionBar(toolbar)

        for (i in Constant.sTabTitles.indices) {
            mTabEntities.add(
                    TabEntity(Constant.sTabTitles[i], mIconSelectIds[i], mIconUnselectIds[i]))
        }
        val gankAdapter = GankAdapter(supportFragmentManager)
        vp_main.setAdapter(gankAdapter)

        t2_2.setTabData(mTabEntities)
        t2_2.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                vp_main.setCurrentItem(position)
            }

            override fun onTabReselect(position: Int) {}
        })

        vp_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float,
                                        positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if (position != 0) {
                    hideDateMenu(mainMenu)
                } else {
                    showDateMenu(mainMenu)
                }

                if (position != 1) {
                    hideFilter(mainMenu)
                } else {
                    showFilter(mainMenu)
                }
                t2_2.setCurrentTab(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
        PublicTools.checkUpdate(this, true)
        RxPermissionUtils.createInstance(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        mainMenu = menu
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        hideFilter(menu)
        showDateMenu(menu)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_filter) {
            val intent = Intent()
            intent.setClass(this, OrderActivity::class.java)
            this.startActivity(intent)
        } else if (id == R.id.action_search) {
            val intent = Intent()
            intent.setClass(this, SearchActivity::class.java)
            this.startActivity(intent)
        } else if (id == R.id.action_datepicker) {
            // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
            if (Constant.YEAR == -1 && Constant.MONTH == -1 && Constant.DAY == -1) {
                val c = Calendar.getInstance()
                showDatePickerDialog(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH))
            } else {
                showDatePickerDialog(Constant.YEAR, Constant.MONTH, Constant.DAY)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun hideFilter(menu: Menu?) {
        if (menu != null) {
            menu.findItem(R.id.action_filter).isVisible = false
        }
    }

    private fun showFilter(menu: Menu?) {
        if (menu != null) {
            menu.findItem(R.id.action_filter).isVisible = true
        }
    }

    private fun hideDateMenu(menu: Menu?) {
        if (menu != null) {
            menu.findItem(R.id.action_datepicker).isVisible = false
        }
    }

    private fun showDateMenu(menu: Menu?) {
        if (menu != null) {
            menu.findItem(R.id.action_datepicker).isVisible = true
        }
    }

    private fun showDatePickerDialog(year: Int, month: Int, day: Int) {

        DatePickerDialog(this@GankActivity,
                // 绑定监听器
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    Constant.YEAR = year
                    Constant.MONTH = month
                    Constant.DAY = dayOfMonth
                    if (mOnDatePickedListener != null) {
                        mOnDatePickedListener!!.onDatePicked(year, month, dayOfMonth)
                    }
                }, year, month, day)// 设置初始日期
                .show()
    }

    fun setOnDatePickedListener(onDatePickedListener: OnDatePickedListener) {
        mOnDatePickedListener = onDatePickedListener
    }

    override fun onBackPressed() {
        alert("提示", "确认要退出嘛？") {
            positiveButton("取消") { dialog -> dialog.dismiss() }
            negativeButton("确认") { dialog ->
                dialog.dismiss()
                finish()
            }
        }.show()
    }
}
