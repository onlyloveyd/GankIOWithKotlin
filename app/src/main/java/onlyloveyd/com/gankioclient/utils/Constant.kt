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
package onlyloveyd.com.gankioclient.utils

import onlyloveyd.com.gankioclient.R
import java.util.*

/**
 * 文 件 名: Constant
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：常量
 */
object Constant {

    val ONE_SECOND: Long = 1000
    val ONE_MINUTE = ONE_SECOND * 60
    val ONE_HOUR = ONE_MINUTE * 60
    val ONE_DAY = ONE_HOUR * 24

    val BONUS = "福利"
    val APP_NAME = "技术船"
    val SUFFIX_JPEG = ".jpg"
    val FIR_API_TOKEN = "3dc58a8e3aafb6a54a72c279b8584b36"
    val APP_FIR_IM_URL = "https://fir.im/md5y"
    val GITHUB_LATEST_APK = "https://github.com/onlyloveyd/GankIOClient/raw/master/latestversion/latestversion.apk"

    var sTabTitles = arrayOf("每日干货", "分类阅读", "匠心写作", "关于")

    var sTypeColor: HashMap<String, Int> = object : HashMap<String, Int>() {
        init {
            put("Android", R.drawable.bg_android_tag)
            put("iOS", R.drawable.bg_ios_tag)
            put("瞎推荐", R.drawable.bg_rec_tag)
            put("拓展资源", R.drawable.bg_res_tag)
            put("App", R.drawable.bg_app_tag)
            put("福利", R.drawable.bg_bonus_tag)
            put("前端", R.drawable.bg_js_tag)
            put("休息视频", R.drawable.bg_video_tag)
        }
    }

    var sCategoryList: ArrayList<String> = object : ArrayList<String>() {
        init {
            add("all")
            add("Android")
            add("瞎推荐")
            add("iOS")
            add("前端")
            add("拓展资源")
            add("App")
            add("休息视频")
            add("福利")
        }
    }

    var sCategryListChanged = false

    var YEAR = -1
    var MONTH = -1
    var DAY = -1
}
