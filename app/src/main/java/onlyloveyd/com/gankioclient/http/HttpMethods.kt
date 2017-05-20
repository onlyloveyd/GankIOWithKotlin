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
package onlyloveyd.com.gankioclient.http

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import onlyloveyd.com.gankioclient.data.DailyData
import onlyloveyd.com.gankioclient.data.SearchData
import onlyloveyd.com.gankioclient.data.TypeData
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * 文 件 名: HttpMethods
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：单例Retrofit
 */

class HttpMethods//构造方法私有
private constructor() {

    private val retrofit: Retrofit
    private val contentService: ContentService
    private val mOkHttpClient: OkHttpClient

    init {
        //手动创建一个OkHttpClient并设置超时时间
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
        mOkHttpClient = httpClientBuilder.build()

        retrofit = Retrofit.Builder().client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()

        contentService = retrofit.create(ContentService::class.java)
    }

    /**
     * 用于获取干货数据

     * @param subscriber 由调用者传过来的观察者对象
     * *
     * @param category   类别
     * *
     * @param pagesize   请求数据个数
     * *
     * @param pagenum    页码
     */
    fun getData(subscriber: Observer<TypeData>, category: String, pagesize: String,
                pagenum: Int) {
        contentService.getContent(category, pagesize, pagenum)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
    }

    /**
     * 查询干货数据
     */
    fun searchData(subscriber: Observer<SearchData>, keyword: String, category: String,
                   pageindex: Int) {
        contentService.search(category, keyword, pageindex)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
    }

    /**
     * 获取每日数据
     */
    fun getDailyData(subscriber: Observer<DailyData>, year: Int, month: Int, day: Int) {
        contentService.getDaily(year, month, day)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
    }

    /**
     * 下载应用
     */
    fun downloadApk(subscriber: Observer<ResponseBody>, url: String) {
        contentService.downloadUrl(url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
    }

    //在访问HttpMethods时创建单例
    private object SingletonHolder {
        val INSTANCE = HttpMethods()
    }

    companion object {

        val BASE_URL = "http://gank.io/api/"

        private val DEFAULT_TIMEOUT = 5


        //获取单例
        val instance: HttpMethods
            get() = SingletonHolder.INSTANCE
    }
}