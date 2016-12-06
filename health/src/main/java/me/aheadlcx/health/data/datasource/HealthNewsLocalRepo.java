package me.aheadlcx.health.data.datasource;

import android.os.Looper;
import android.util.Log;

import java.io.File;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import me.aheadlcx.health.MyApplication;
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
    public Observable healthNewsListObservabler(String page, Scheduler subscribeOnScheduler, Scheduler observeOnScheduler) {
        final boolean isUiThread = Looper.getMainLooper() == Looper.myLooper();

        Observable<List<HealthNewsItem>> listObservable = Observable.create(new Observable.OnSubscribe<List<HealthNewsItem>>() {
            @Override
            public void call(Subscriber<? super List<HealthNewsItem>> subscriber) {
                Realm realm = Realm.getDefaultInstance();
                RealmResults<HealthNewsItem> healthNewsItems = realm.where(HealthNewsItem.class)
                        .findAll();
                List<HealthNewsItem> results = realm.copyFromRealm(healthNewsItems);
                if (results != null && results.size() > 0) {
                    Log.i("notify", "local call:  " + " ---  isUiThread = " + isUiThread
                            + " size = " + results.size());
                    realm.close();
                    subscriber.onNext(results);
                } else {
                    Log.i("notify", "local call: null or size = 0 ");
                    Observable.empty();
                    realm.close();
                }

                subscriber.onCompleted();
            }
        });
        return listObservable;
    }

    @Override
    public Observable healthNewsDetailObservabler(long page, Scheduler subscribeOnScheduler, Scheduler observeOnScheduler) {
        return null;
    }

    public void insertToDb(List<HealthNewsItem> healthNewsItems) {
        if (healthNewsItems != null) {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();

            HealthNewsItem item = healthNewsItems.get(0);

            HealthNewsItem realmObject = realm.createObject(HealthNewsItem.class);
//            realmObject.setCount(item.getCount());
            realmObject.setDescription(item.getDescription());
            realmObject.setImg(item.getImg());
            realmObject.setTitle(item.getTitle());
            realmObject.setKeywords(item.getKeywords());
            realmObject.setId((int) (item.getId() + System.currentTimeMillis()));
//            realmObject.setKeyId(System.currentTimeMillis());

            Log.e("notify", "insertToDb:  != null");
//            realm.copyToRealmOrUpdate(realmObject);
            realm.copyToRealmOrUpdate(healthNewsItems);
//            realm.insertOrUpdate(healthNewsItems);
//            for (int i = 0; i < healthNewsItems.size(); i++) {
//                realm.insertOrUpdate(healthNewsItems.get(i));
//                healthNewsItems.get(i);
//            }
            realm.commitTransaction();
            realm.close();
        }
    }
}
