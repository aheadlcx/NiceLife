package me.aheadlcx.health.api;

import me.aheadlcx.health.model.HealthNewsDetailResponse;
import me.aheadlcx.health.model.HealthNewsListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/27 下午6:54
 */
public interface HealthNewsListService {

    @GET("api/info/list")
    Observable<HealthNewsListResponse> getHealthNewsList(@Query("page") String page, @Query
            ("rows") String rows);

    @GET("api/info/list")
    Call<HealthNewsListResponse> getHealthNewsListSync();

    @GET("api/info/show")
    Observable<HealthNewsDetailResponse> getHealthNewsDetail(@Query("id") long id);
}
