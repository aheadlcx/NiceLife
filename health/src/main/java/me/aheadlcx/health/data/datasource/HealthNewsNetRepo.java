package me.aheadlcx.health.data.datasource;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import me.aheadlcx.health.api.ApiUtils;
import me.aheadlcx.health.api.HealthNewsListService;
import me.aheadlcx.health.constant.HealthConstant;
import me.aheadlcx.health.constant.HealthType;
import me.aheadlcx.health.domain.repository.HealthNewsRepository;
import me.aheadlcx.health.model.HealthNewsItem;
import me.aheadlcx.health.model.HealthNewsListResponse;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Description: 健康资讯
 * Creator: aheadlcx
 * Date:2016/11/28 上午9:57
 */

public class HealthNewsNetRepo implements HealthNewsRepository {
    private static final String TAG = "notify";
    @Override
    public Observable healthNewsListObservabler(String page, Scheduler subscribeOnScheduler,
                                                Scheduler observeOnScheduler, @HealthType int
                                                            healthType, boolean isLoadMore) {
        return ApiUtils.getRetrofit().create(HealthNewsListService.class).getHealthNewsList(page,
                HealthConstant.pageSize)
                .flatMap(new Func1<HealthNewsListResponse, Observable<List<HealthNewsItem>>>() {
                    @Override
                    public Observable<List<HealthNewsItem>> call(HealthNewsListResponse response) {
                                                     Log.i("notify", "net call: size = " +
                                                             response.getTngou().size());
                        return Observable.just(response.getTngou());
                    }
                });
    }

    @Override
    public Observable healthNewsDetailObservabler(long id, Scheduler subscribeOnScheduler,
                                                  Scheduler observeOnScheduler, @HealthType final
                                                      int healthType) {
        Log.i(TAG, "healthNewsDetailObservabler: ");
        return ApiUtils.getRetrofit().create(HealthNewsListService.class).getHealthNewsDetail(id);
//                .subscribeOn(subscribeOnScheduler).observeOn(observeOnScheduler);
    }
}
