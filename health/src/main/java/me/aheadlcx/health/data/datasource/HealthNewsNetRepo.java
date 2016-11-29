package me.aheadlcx.health.data.datasource;

import java.util.List;

import me.aheadlcx.health.api.ApiUtils;
import me.aheadlcx.health.api.HealthNewsListService;
import me.aheadlcx.health.domain.repository.HealthNewsRepository;
import me.aheadlcx.health.model.HealthNewsItem;
import me.aheadlcx.health.model.HealthNewsListResponse;
import rx.Observable;
import rx.functions.Func1;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午9:57
 */

public class HealthNewsNetRepo implements HealthNewsRepository {
    @Override
    public Observable buildHealthNewsObservabler(String page) {
        return ApiUtils.getRetrofit().create(HealthNewsListService.class).getHealthNewsList(page)
                .map(new Func1<HealthNewsListResponse, List<HealthNewsItem>>() {
                    @Override
                    public List<HealthNewsItem> call(HealthNewsListResponse response) {
                        if (null != response) {
                            return response.getTngou();
                        }else {
                            return null;
                        }
                    }
                }
    );
    }

    @Override
    public Observable buildHealthNewsDetailObservabler(long id) {
        return ApiUtils.getRetrofit().create(HealthNewsListService.class).getHealthNewsDetail(id);
    }
}
