package me.aheadlcx.health.data.datasource;

import me.aheadlcx.health.domain.repository.HealthNewsRepository;
import rx.Observable;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午9:57
 */

public class HealthNewsLocalRepo implements HealthNewsRepository {
    @Override
    public Observable buildHealthNewsObservabler(String page) {
        return null;
    }

    @Override
    public Observable buildHealthNewsDetailObservabler(long page) {
        return null;
    }
}
