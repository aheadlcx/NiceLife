package me.aheadlcx.health.domain.interactor;

import me.aheadlcx.health.constant.HealthType;
import me.aheadlcx.health.domain.executor.PostExecutionThread;
import me.aheadlcx.health.domain.executor.ThreadExecutor;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午8:45
 */

public abstract class Case {

    private Subscription mSubscription = Subscriptions.empty();

    private final PostExecutionThread mPostExecutionThread;
    private final ThreadExecutor mThreadExecutor;

    public Case(PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
        mPostExecutionThread = postExecutionThread;
        mThreadExecutor = threadExecutor;
    }

    public Observable caseListObservable(String page, @HealthType final int healthType, boolean isLoadMore) {
        return null;
    }

    public Observable caseDetailObservable(long id, @HealthType final int healthType) {
        return null;
    }


    public void execute(String page, Subscriber subscriber) {
//        mSubscription = this.caseListObservable(page)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread(), true)
//                .subscribe(subscriber);
//        connect();
    }

    public Scheduler getSubscribeOnScheduler() {
        return Schedulers.from(mThreadExecutor);
    }

    public Scheduler getObserveOnScheduler() {
        return mPostExecutionThread.getScheduler();
    }

    public void execute(long id, Subscriber subscriber, @HealthType final int healthType) {
        mSubscription = this.caseDetailObservable(id, healthType)
                .observeOn(AndroidSchedulers.mainThread(), true)
                .subscribe(subscriber);
        connect();
    }

    public void connect() {

    }

    public void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

}
