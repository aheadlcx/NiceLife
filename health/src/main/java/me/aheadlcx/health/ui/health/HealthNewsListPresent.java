package me.aheadlcx.health.ui.health;

import android.content.Context;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import me.aheadlcx.health.di.Type;
import me.aheadlcx.health.domain.interactor.Case;
import me.aheadlcx.health.domain.interactor.DefaultSubscriber;
import me.aheadlcx.health.model.HealthNewsItem;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/27 下午8:27
 */

public class HealthNewsListPresent implements HealthNewslistContract.Present {

    private static final String TAG = "HealthNewsListPresent";
    private Context mContext;
    private Case mCase;
    private HealthNewslistContract.View mUi;

    @Inject
    public HealthNewsListPresent(@Type("list") Case aCase, Context context) {
        mCase = aCase;
        mContext = context;
    }

    public HealthNewsListPresent setUi(HealthNewslistContract.View ui) {
        mUi = ui;
        return this;
    }

    @Override
    public void loadData(String page) {
        Log.i(TAG, "loadData: ");
        mCase.execute(page, new HealthNewsListSubscriber());
    }


    private  class HealthNewsListSubscriber extends DefaultSubscriber<List<HealthNewsItem>>{
        @Override
        public void onNext(final List<HealthNewsItem> healthNewsItems) {
            int size = -1;
            if (null != healthNewsItems) {
                size = healthNewsItems.size();
            }
            Log.i(TAG, "onNext: size " + size);
            super.onNext(healthNewsItems);
            if (mUi != null) {
                mUi.setData(healthNewsItems);
            }
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    insertToDb(healthNewsItems);
                }
            }.start();
        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "onError: " + e.getMessage() + e.getLocalizedMessage() + e.toString());
            super.onError(e);
        }

        @Override
        public void onCompleted() {
            Log.i(TAG, "onCompleted: ");
            super.onCompleted();
        }
    }

    protected void insertToDb(List<HealthNewsItem> healthNewsItems){
        if (healthNewsItems != null) {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            for (int i = 0; i < healthNewsItems.size(); i++) {
                realm.insertOrUpdate(healthNewsItems.get(i));
                healthNewsItems.get(i);
            }
            realm.commitTransaction();
            realm.close();
        }
    }
}
