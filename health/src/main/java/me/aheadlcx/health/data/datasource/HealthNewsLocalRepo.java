package me.aheadlcx.health.data.datasource;

import android.os.Looper;
import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import me.aheadlcx.health.domain.repository.HealthNewsRepository;
import me.aheadlcx.health.model.HealthNewsItem;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午9:57
 */

public class HealthNewsLocalRepo implements HealthNewsRepository {
    @Override
    public Observable buildHealthNewsObservabler(String page, Scheduler subscribeOnScheduler, Scheduler observeOnScheduler) {
        boolean isUiThread = Looper.getMainLooper() == Looper.myLooper();
        long id = Thread.currentThread().getId();
        Log.i("notify", "local call: id = " + id + " ---  isUiThread = " + isUiThread);
//        Realm realm = Realm.getDefaultInstance();
//        RealmResults<HealthNewsItem> all = realm.where(HealthNewsItem.class).findAllAsync();
//        return all.asObservable().subscribeOn(AndroidSchedulers.mainThread());

        Observable<List<HealthNewsItem>> listObservable = Observable.create(new Observable.OnSubscribe<List<HealthNewsItem>>() {
            @Override
            public void call(Subscriber<? super List<HealthNewsItem>> subscriber) {
                Realm realm = Realm.getDefaultInstance();
                RealmResults<HealthNewsItem> healthNewsItems = realm.where(HealthNewsItem.class)
                        .findAll();
                List<HealthNewsItem> results = realm.copyFromRealm(healthNewsItems);
                if (results != null && results.size() > 0) {
                    subscriber.onNext(results);
                } else {
                    Observable.empty();
                }

                subscriber.onCompleted();
                realm.close();
            }
        }).subscribeOn(Schedulers.newThread());
        return listObservable;
    }

    @Override
    public Observable buildHealthNewsDetailObservabler(long page, Scheduler subscribeOnScheduler, Scheduler observeOnScheduler) {
        return null;
    }
}
