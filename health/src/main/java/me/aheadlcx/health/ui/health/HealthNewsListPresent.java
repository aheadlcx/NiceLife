package me.aheadlcx.health.ui.health;

import android.content.Context;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import me.aheadlcx.health.constant.HealthType;
import me.aheadlcx.health.data.repository.HealthNewsDataRepository;
import me.aheadlcx.health.di.Type;
import me.aheadlcx.health.domain.interactor.Case;
import me.aheadlcx.health.domain.interactor.DefaultSubscriber;
import me.aheadlcx.health.model.HealthNewsItem;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    private boolean isLoadMore = false;
    @Inject
    HealthNewsDataRepository mDataRepository;

    public HealthNewsListPresent() {
    }

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
    public void loadFirst(String page) {
        Log.i(TAG, "loadFirst: ");
        isLoadMore = false;
        loadData(page, getHealthType());
    }

    private int getHealthType(){
        return HealthType.TYPE_INFO;
    }

    @Override
    public void loadData(String page, @HealthType final int healthType) {
        mDataRepository.healthNewsListObservabler(page, null, null, healthType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), true)
                .subscribe(new HealthNewsListSubscriber());
    }

    public boolean isLoadMore() {
        return isLoadMore;
    }

    @Override
    public void loadMoreData(String page) {
        isLoadMore = true;
        loadData(page, getHealthType());
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
            if (mUi == null) {
                return;
            }
            if (isLoadMore()) {
                mUi.addData(healthNewsItems);
            }else {
                mUi.setData(healthNewsItems);
            }

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
}
