package onlyloveyd.com.gankioclient.http

import io.reactivex.Observable
import okhttp3.ResponseBody
import onlyloveyd.com.gankioclient.gsonbean.DailyBean
import onlyloveyd.com.gankioclient.gsonbean.DataBean
import onlyloveyd.com.gankioclient.gsonbean.SearchBean
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

/**
 * 文 件 名: ContentService
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：Retrofit api接口
 */
interface ContentService {

    /* category:  福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * pagesize： 数字，大于0
     * pagenum ： 数字，大于0 */

    @GET("data/{category}/{pagesize}/{pagenum}")
    fun getContent(
            @Path("category") category: String, @Path("pagesize") pagesize: String,
            @Path("pagenum") pagenum: Int): Observable<DataBean>

    /**
     * 获取某天的干货
     */
    @GET("day/{date}")
    fun getRecentlyGanHuo(@Path("date") date: String): Observable<DataBean>

    /**
     * 搜索
     */
    @GET("search/query/{keyword}/category/{category}/count/20/page/{pageIndex}")
    fun search(@Path("category") category: String,
               @Path("keyword") keyword: String,
               @Path("pageIndex") pageIndex: Int): Observable<SearchBean>

    @GET("history/content/10/{pageIndex}")
    fun getRecently(
            @Path("pageIndex") pageIndex: Int): Observable<DataBean>

    /**
     * @param year  year
     * *
     * @param month month
     * *
     * @param day   day
     * *
     * @return Observable<GankDaily>
    </GankDaily> */
    @GET("day/{year}/{month}/{day}")
    fun getDaily(@Path("year") year: Int,
                 @Path("month") month: Int, @Path("day") day: Int): Observable<DailyBean>

    @GET
    fun downloadUrl(@Url url: String): Observable<ResponseBody>
}
