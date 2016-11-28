package me.aheadlcx.health.domain.interactor;

import me.aheadlcx.health.domain.executor.PostExecutionThread;
import me.aheadlcx.health.domain.executor.ThreadExecutor;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午8:45
 */

public abstract class Case {

    private Subscription mSubscription = Subscriptions.empty();

    private final PostExecutionThread mPostExecutionThread ;
    private final ThreadExecutor mThreadExecutor;

    public Case(PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
        mPostExecutionThread = postExecutionThread;
        mThreadExecutor = threadExecutor;
    }

    public abstract Observable buildCaseObservable(String page);


    public void execute(String page, Subscriber subscriber){
         mSubscription = this.buildCaseObservable(page).subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutionThread.getScheduler())
//        mSubscription = this.buildCaseObservable(page).subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
                .subscribe(subscriber);
        connect();
    }

    public void connect(){

    }

    public void unSubscribe(){
        if (mSubscription != null && ! mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

}
