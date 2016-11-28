package me.aheadlcx.health.data.repository;

import android.util.Log;

import javax.inject.Inject;

import me.aheadlcx.health.data.datasource.HealthNewsLocalRepo;
import me.aheadlcx.health.data.datasource.HealthNewsNetRepo;
import me.aheadlcx.health.domain.repository.HealthNewsRepository;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
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
    private HealthNewsRepository mNetRepo;
    ConnectableObservable replay;

    @Inject
    public HealthNewsDataRepository() {
        mLocalRepo = new HealthNewsLocalRepo();
        mNetRepo = new HealthNewsNetRepo();
    }

    @Override
    public Observable buildHealthNewsObservabler(String page) {
        Observable observable = mNetRepo.buildHealthNewsObservabler(page);
        replay = observable.replay(20);
        return replay;
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
            subscribeAnthoer();
//            new Thread(){
//                @Override
//                public void run() {
//                    super.run();
                    replay.connect();
//                }
//            }.start();
        }
    }
}
