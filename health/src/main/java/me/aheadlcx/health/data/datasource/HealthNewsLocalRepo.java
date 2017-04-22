package me.aheadlcx.health.data.datasource;

import android.os.Looper;
import android.util.Log;

import java.io.File;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import me.aheadlcx.health.MyApplication;
import me.aheadlcx.health.constant.HealthType;
import me.aheadlcx.health.domain.repository.HealthNewsRepository;
import me.aheadlcx.health.model.HealthNewsDetailResponse;
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
    private static final String TAG = "notify";

    @Override
    public Observable healthNewsListObservabler(String page, Scheduler subscribeOnScheduler,
                                                Scheduler observeOnScheduler, @HealthType final
                                                int healthType, boolean isLoadMore) {
        final boolean isUiThread = Looper.getMainLooper() == Looper.myLooper();

        Observable<List<HealthNewsItem>> listObservable = Observable.create(new Observable.OnSubscribe<List<HealthNewsItem>>() {
            @Override
            public void call(Subscriber<? super List<HealthNewsItem>> subscriber) {
                boolean isUi = Looper.getMainLooper() == Looper.myLooper();
                Log.i(TAG, "healthNewsListObservabler call: isUi = " + isUi);
                Realm realm = Realm.getDefaultInstance();
                RealmResults<HealthNewsItem> healthNewsItems = null;
                healthNewsItems = getRealByType(realm, healthType);

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
        }).subscribeOn(Schedulers.io());
        return listObservable;
    }

    private RealmResults<HealthNewsItem> getRealByType(Realm realm, @HealthType int healthType) {
        RealmResults<HealthNewsItem> healthNewsItems = null;
        switch (healthType) {
            case HealthType.TYPE_INFO:
                healthNewsItems = realm.where(HealthNewsItem.class)
                        .between("infoclass", 1, Integer.MAX_VALUE)
                        .findAll();
                break;
            case HealthType.TYPE_LORE:
                healthNewsItems = realm.where(HealthNewsItem.class)
                        .between("loreclass", 1, Integer.MAX_VALUE)
                        .findAll();
                break;
        }

        return healthNewsItems;
    }

    @Override
    public Observable healthNewsDetailObservabler(long page, Scheduler subscribeOnScheduler,
                                                  Scheduler observeOnScheduler, @HealthType final
                                                  int healthType) {
        return null;
    }

    public void insertToDb(List<HealthNewsItem> healthNewsItems) {
        if (healthNewsItems != null) {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();

            HealthNewsItem item = healthNewsItems.get(0);
            Log.e("notify", "insertToDb:  != null");
            realm.copyToRealmOrUpdate(healthNewsItems);
            realm.commitTransaction();
            realm.close();
        }
    }

    public void delete() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.delete(HealthNewsItem.class);
        realm.commitTransaction();
        realm.close();
        ;
    }

    public void insertDetail(HealthNewsDetailResponse response) {
        if (response == null) {
            return;
        }
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
//        HealthNewsDetailResponse realmObject = realm.createObject(HealthNewsDetailResponse.class);
//        realm.copyToRealmOrUpdate(response);
        realm.insertOrUpdate(response);
        realm.commitTransaction();
        realm.close();
    }

    public Observable<HealthNewsDetailResponse> getDetail(final long id, @HealthType final int healthType) {
        return Observable.create(new Observable.OnSubscribe<HealthNewsDetailResponse>() {
            @Override
            public void call(Subscriber<? super HealthNewsDetailResponse> subscriber) {
                boolean isUi = Looper.getMainLooper() == Looper.myLooper();
                Log.i(TAG, "getDetail call: isUi = " + isUi);
                Realm realm = Realm.getDefaultInstance();
                HealthNewsDetailResponse detailResponse = null;
                RealmQuery<HealthNewsDetailResponse> realmQuery = realm.where(HealthNewsDetailResponse.class).equalTo("id", id);
                getReamlQuery(realmQuery, id, healthType)
                        .findFirst();
                if (detailResponse == null) {
                    subscriber.onCompleted();
                    return;
                }
                HealthNewsDetailResponse result = realm.copyFromRealm(detailResponse);
                realm.close();
                if (detailResponse != null) {
                    subscriber.onNext(result);
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());
    }

    private RealmQuery<HealthNewsDetailResponse> getReamlQuery(RealmQuery<HealthNewsDetailResponse> realmQuery, final long id, @HealthType
    final int healthType) {
        String fieldName = "infoclass";
        if (healthType == HealthType.TYPE_LORE) {
            fieldName = "loreclass";
        } else if (healthType == HealthType.TYPE_INFO) {
            fieldName = "infoclass";
        }
        realmQuery.between(fieldName, 1, Integer.MAX_VALUE);
        return realmQuery;

    }
}
