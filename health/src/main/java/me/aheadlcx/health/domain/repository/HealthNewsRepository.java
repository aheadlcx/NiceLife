package me.aheadlcx.health.domain.repository;

import rx.Observable;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午9:18
 */

public interface HealthNewsRepository {

    Observable buildHealthNewsObservabler(String page);

}
