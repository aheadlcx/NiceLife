package me.aheadlcx.health.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmMigration;
import io.realm.RealmResults;
import me.aheadlcx.health.R;
import me.aheadlcx.health.base.BaseActivity;
import me.aheadlcx.health.constant.HealthType;
import me.aheadlcx.health.data.datasource.HealthNewsLocalRepo;
import me.aheadlcx.health.model.HealthNewsItem;
import me.aheadlcx.health.ui.health.HealthNewsAdapter;
import me.aheadlcx.health.ui.health.HealthNewsListAct;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/12/3 下午8:44
 */

public class TestListAct extends BaseActivity {
    private static final String TAG = "TestListAct";

    private android.support.v7.widget.RecyclerView recycleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_health_newslist);
        this.recycleView = (RecyclerView) findViewById(R.id.recycleView);
    }

    public void setData(List<HealthNewsItem> lists) {
        if (lists != null && lists.size() != 0) {
            HealthNewsAdapter adapter = new HealthNewsAdapter(lists
                    , this);
            recycleView.setLayoutManager(new LinearLayoutManager(this));
            recycleView.setAdapter(adapter);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
//        getDbData();
        testDb();
//        getPageDb();
    }

    private void testDb() {
        HealthNewsLocalRepo repo = new HealthNewsLocalRepo();
        repo.healthNewsListObservabler("1", null, null, HealthType.TYPE_INFO, false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<HealthNewsItem>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(List<HealthNewsItem> healthNewsItems) {
                        Log.i(TAG, "onNext: size = " + healthNewsItems.size());
                        setData(healthNewsItems);

                    }
                });
    }

    public void getDbData(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<HealthNewsItem> all = realm.where(HealthNewsItem.class).findAll();
        List<HealthNewsItem> results = realm.copyFromRealm(all);
        Log.i(TAG, "getDbData: getDbData all size = " + all.size());
        setData(results);
        realm.close();
    }

    public void getPageDb(){
        final Realm realm = Realm.getDefaultInstance();
        final Page page = realm.where(Page.class)
                .equalTo("query", "test")
                .findFirst();
        Page fromRealm = realm.copyFromRealm(page);
        if (fromRealm != null){
            String content = fromRealm.getContent();
            Log.i(TAG, "getPageDb: content = " + content);
        }
    }

    public void test(){
        new RealmMigration(){

            @Override
            public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

            }
        };
    }
}
