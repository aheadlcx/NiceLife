package me.aheadlcx.health.data.repository;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.aheadlcx.health.data.datasource.HealthNewsLocalRepo;
import me.aheadlcx.health.data.datasource.HealthNewsNetRepo;
import me.aheadlcx.health.domain.repository.HealthNewsRepository;
import me.aheadlcx.health.model.HealthNewsItem;
import me.aheadlcx.health.model.HealthNewsListResponse;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午9:56
 */
public class HealthNewsDataRepository implements HealthNewsRepository {
    private static final String TAG = "HealthNewsDataRepositor";
    private HealthNewsLocalRepo mLocalRepo;
    private HealthNewsNetRepo mNetRepo;
    ConnectableObservable replay;

    @Inject
    public HealthNewsDataRepository() {
        mLocalRepo = new HealthNewsLocalRepo();
        mNetRepo = new HealthNewsNetRepo();
    }

    @Override
    public Observable healthNewsListObservabler(String page, Scheduler subscribeOnScheduler, Scheduler observeOnScheduler) {
        final Observable localObservale = mLocalRepo.healthNewsListObservabler(page,
                subscribeOnScheduler, observeOnScheduler);
        final Observable netObservale = mNetRepo
                .healthNewsListObservabler(page, subscribeOnScheduler, observeOnScheduler)
                .doOnNext(new Action1<List<HealthNewsItem>>() {
                    @Override
                    public void call(List<HealthNewsItem> data) {
                        mLocalRepo.insertToDb(data);
                    }
                });
//        return Observable.concat(testCode(), netObservale);
        return Observable.concat(localObservale, netObservale);
    }

    @Override
    public Observable healthNewsDetailObservabler(long id, Scheduler subscribeOnScheduler, Scheduler observeOnScheduler) {
        return mNetRepo.healthNewsDetailObservabler(id, subscribeOnScheduler, observeOnScheduler);
    }

    private void subscribeAnthoer() {
        replay.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: " + e.getMessage() + e.getLocalizedMessage() + e.toString());
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.i(TAG, "onNext: ");
                    }
                });
    }

    public void connect() {
        if (replay != null) {
//            subscribeAnthoer();
//            new Thread(){
//                @Override
//                public void run() {
//                    super.run();
//                    replay.connect();
//                }
//            }.start();
        }
    }

    private Observable testCode(){
        Observable<List<HealthNewsItem>> createObservable = Observable.create(new Observable.OnSubscribe<HealthNewsListResponse>() {
            @Override
            public void call(Subscriber<? super HealthNewsListResponse> subscriber) {
                Log.i(TAG, "call:  create call ");
                HealthNewsListResponse response = new HealthNewsListResponse();
                ArrayList<HealthNewsItem> tngou = new ArrayList<>();
                HealthNewsItem healthNewsItem = new HealthNewsItem();
                healthNewsItem.setTitle("create by aheadlcx");
                tngou.add(healthNewsItem);
                response.setTngou(tngou);
                subscriber.onNext(response);
                subscriber.onCompleted();
            }
        }).map(new Func1<HealthNewsListResponse, List<HealthNewsItem>>() {
            @Override
            public List<HealthNewsItem> call(HealthNewsListResponse response) {
                return response.getTngou();
            }
        });
        return createObservable;
    }
}
