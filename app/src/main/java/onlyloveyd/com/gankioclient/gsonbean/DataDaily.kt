///**
// * Copyright 2017 yidong
// * <p/>
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// * <p/>
// * http://www.apache.org/licenses/LICENSE-2.0
// * <p/>
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package onlyloveyd.com.gankioclient.gsonbean
//
//import com.google.gson.annotations.SerializedName
//import java.util.*
//
///**
// * 文 件 名: DataDaily
// * 创 建 人: 易冬
// * 创建日期: 2017/05/20 18/37
// * 邮   箱: onlyloveyd@gmail.com
// * 博   客: https://onlyloveyd.cn
// * 描   述：
// */
//
//
//data class DailyBean(var isError: Boolean,
//                     var result: ResultBean,
//                     var category: List<String>)
//
//data class ResultBean(
//        @SerializedName("Android")
//        var android: List<DetailsBean>,
//        @SerializedName("App")
//        var app: List<DetailsBean>,
//        @SerializedName("iOS")
//        var ios: List<DetailsBean>,
//        @SerializedName("休息视频")
//        var video: List<DetailsBean>,
//        @SerializedName("前端")
//        var js: List<DetailsBean>,
//        @SerializedName("拓展资源")
//        var res: List<DetailsBean>,
//        @SerializedName("瞎推荐")
//        var rec: List<DetailsBean>,
//        @SerializedName("福利")
//        var bonus: List<DetailsBean>)
//
//data class DetailsBean(var _id: String,
//                       var createdAt: String,
//                       var desc: String,
//                       var publishedAt: Date,
//                       var source: String,
//                       var type: String,
//                       var url: String,
//                       var isUsed: Boolean,
//                       var who: String,
//                       var images: List<String>)
//
//
//
//
