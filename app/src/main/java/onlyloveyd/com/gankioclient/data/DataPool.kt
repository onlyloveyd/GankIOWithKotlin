/**
 * Copyright 2017 yidong
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package onlyloveyd.com.gankioclient.data

import com.google.gson.annotations.SerializedName
import onlyloveyd.com.gankioclient.decorate.Visitable
import onlyloveyd.com.gankioclient.factory.TypeFactory
import java.util.*

/**
 * 文 件 名: DataPool
 * 创 建 人: 易冬
 * 创建日期: 2017/05/20 20/26
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
data class DailyData(var isError: Boolean,
                     var results: ResultData,
                     var category: List<String>)

data class TypeData(var error: Boolean, var results: List<CategoryData>)

data class ResultData(
        @SerializedName("Android")
        var android: List<DetailsData>,
        @SerializedName("App")
        var app: List<DetailsData>,
        @SerializedName("iOS")
        var ios: List<DetailsData>,
        @SerializedName("休息视频")
        var video: List<DetailsData>,
        @SerializedName("前端")
        var js: List<DetailsData>,
        @SerializedName("拓展资源")
        var res: List<DetailsData>,
        @SerializedName("瞎推荐")
        var rec: List<DetailsData>,
        @SerializedName("福利")
        var bonus: List<DetailsData>)

data class DetailsData(var _id: String,
                       var createdAt: String,
                       var desc: String,
                       var publishedAt: Date,
                       var source: String,
                       var type: String,
                       var url: String,
                       var isUsed: Boolean,
                       var who: String,
                       var images: List<String>) : Visitable {
    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}


data class CategoryData(var desc: String,
                        var ganhuo_id: String,
                        var publishedAt: String,
                        var readability: String,
                        var type: String,
                        var url: String,
                        var who: String) : Visitable {
    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}

data class EmptyData(val message: String) : Visitable {
    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}

data class MindData(var title: String,
                    var author: String,
                    var time: String) : Visitable {
    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)

    var url: String = ""
        get() = "http://gank.io" + field
}

data class SearchData(
        @SerializedName("count")
        var count: Int,
        var error: Boolean,
        var results: List<CategoryData>)


data class VersionData(var name: String,
                       var version: String,
                       var changelog: String,
                       var updated_at: Int,
                       var versionShort: String,
                       var build: String,
                       var installUrl: String,
                       var install_url: String,
                       var direct_install_url: String,
                       var update_url: String,
                       var binary: BinaryData)

data class BinaryData(var fsize: Int)