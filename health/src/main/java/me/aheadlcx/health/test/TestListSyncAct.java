package me.aheadlcx.health.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import me.aheadlcx.health.R;
import me.aheadlcx.health.base.BaseActivity;
import me.aheadlcx.health.data.datasource.HealthNewsLocalRepo;
import me.aheadlcx.health.model.HealthNewsItem;
import me.aheadlcx.health.ui.health.HealthNewsAdapter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/12/4 上午9:41
 */

public class TestListSyncAct extends BaseActivity {
    private static final String TAG = "TestListSyncAct";

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

        Realm realm = Realm.getDefaultInstance();
        RealmResults<HealthNewsItem> healthNewsItems = realm.where(HealthNewsItem.class)
                .findAll();
        List<HealthNewsItem> results = realm.copyFromRealm(healthNewsItems);
//        realm.close();
        if (results != null && results.size() > 0) {
            setData(healthNewsItems);
        } else {
        }

//        save();
//        savePage();
    }

    public void save() {
        HealthNewsLocalRepo localRepo = new HealthNewsLocalRepo();
//        HealthNewsItem item = new HealthNewsItem();
        HealthNewsItem item = new HealthNewsItem();
        item.setTitle("save title");
        item.setDescription("save des");
        item.setId(1);
        List<HealthNewsItem> list = new ArrayList<>();
        list.add(item);

        localRepo.insertToDb(list);
    }

    public void savePage() {
//        public void save(@NonNull String query, @NonNull Page page) {
        Page page = new Page();
        page.setId(111l);
        page.setTitle("page title");
        page.setContent("page content");
        String query = "test";
        page.setQuery(query);

        final Realm realm = Realm.getDefaultInstance();
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

}
