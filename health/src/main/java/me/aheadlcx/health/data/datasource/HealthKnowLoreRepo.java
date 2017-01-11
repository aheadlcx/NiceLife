package me.aheadlcx.health.data.datasource;

import android.util.Log;

import java.util.List;

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

/**
 * Description:
 * author: aheadlcx
 * Date:2017/1/10 下午4:30
 */

public class HealthKnowLoreRepo implements HealthNewsRepository {
    private static final String TAG = "HealthKnowLoreRepo";
    @Override
    public Observable healthNewsListObservabler(String page, Scheduler subscribeOnScheduler,
                                                Scheduler observeOnScheduler, @HealthType int
                                                            healthType, boolean isLoadMore) {
        return ApiUtils.getRetrofit().create(HealthNewsListService.class).getHealthLoreList(page,
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
                                                  Scheduler observeOnScheduler, @HealthType int healthType) {
        return ApiUtils.getRetrofit().create(HealthNewsListService.class).getHealthLoreDetail(id);
    }
}
