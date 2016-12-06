package me.aheadlcx.health.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;

import java.io.Serializable;
import java.util.List;

import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/12/5 下午10:32
 */

@Singleton
public final class SearchLocalDataSource implements Serializable {
    @NonNull
    private Context mContext;

    @NonNull
    private RealmConfiguration mRealmConfiguration;

    public SearchLocalDataSource(@NonNull Context context) {
        this.mContext = context;
        this.mRealmConfiguration = new RealmConfiguration.Builder(mContext).build();
    }

//    @Override
    @WorkerThread
    public Observable<Page> search(@NonNull final String query) {
        return Observable.create(new Observable.OnSubscribe<Page>() {
            @Override
            public void call(Subscriber<? super Page> subscriber) {
                final Realm realm = Realm.getInstance(mRealmConfiguration);
                final Page page = realm.where(Page.class)
                        .equalTo("query", query)
                        .findFirst();
                if (page != null && page.isLoaded() && page.isValid()) {
                    subscriber.onNext(realm.copyFromRealm(page));
                } else {
                    Observable.empty();
                }
                subscriber.onCompleted();
                realm.close();
            }
        });
    }

//    @Override
    @WorkerThread
    public void save(@NonNull String query, @NonNull Page page) {
        final Realm realm = Realm.getInstance(mRealmConfiguration);
        realm.beginTransaction();

        final Page realmPage = realm.createObject(Page.class);
        realmPage.setQuery(query);
        realmPage.setId(page.getId());
        realmPage.setTitle(page.getTitle());
        realmPage.setContent(page.getContent());
        realm.copyToRealmOrUpdate(realmPage);
        realm.commitTransaction();
        realm.close();
    }

//    @Override
    @WorkerThread
    public Observable<List<Page>> searchHistory(@Nullable final String query) {
        return Observable.create(new Observable.OnSubscribe<List<Page>>() {
            @Override
            public void call(Subscriber<? super List<Page>> subscriber) {
                if (TextUtils.isEmpty(query)) {
                    subscriber.onNext(getAllHistoryQueries());
                }
            }
        });
    }

    @WorkerThread
    private List<Page> getAllHistoryQueries() {
        final Realm realm = Realm.getInstance(mRealmConfiguration);
        realm.beginTransaction();

        final RealmResults<Page> realmResults = realm.where(Page.class).findAll();
        final List<Page> result = realm.copyFromRealm(realmResults);
        realm.commitTransaction();
        realm.close();
        return result;
    }
}