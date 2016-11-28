package me.aheadlcx.health.domain.executor;

import rx.Scheduler;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午8:38
 */

public interface PostExecutionThread {
    Scheduler getScheduler();
}
