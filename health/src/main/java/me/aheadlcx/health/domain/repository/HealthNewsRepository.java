package me.aheadlcx.health.domain.repository;

import me.aheadlcx.health.constant.HealthType;
import rx.Observable;
import rx.Scheduler;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午9:18
 */

public interface HealthNewsRepository {

    Observable healthNewsListObservabler(String page, Scheduler subscribeOnScheduler, Scheduler
            observeOnScheduler, @HealthType int healthType, boolean isLoadMore);
    Observable healthNewsDetailObservabler(long page, Scheduler subscribeOnScheduler, Scheduler
            observeOnScheduler, @HealthType int healthType);

}
