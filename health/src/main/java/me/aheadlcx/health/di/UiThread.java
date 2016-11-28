package me.aheadlcx.health.di;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.aheadlcx.health.domain.executor.PostExecutionThread;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午10:34
 */
@Singleton
public class UiThread implements PostExecutionThread {
    @Inject
    public UiThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
