package me.aheadlcx.health.domain.repository;

import rx.Observable;
import rx.Scheduler;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午9:18
 */

public interface HealthNewsRepository {

    Observable buildHealthNewsObservabler(String page, Scheduler subscribeOnScheduler, Scheduler observeOnScheduler);
    Observable buildHealthNewsDetailObservabler(long page, Scheduler subscribeOnScheduler, Scheduler observeOnScheduler);

}
