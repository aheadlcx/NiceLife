package me.aheadlcx.health.ui.health;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import me.aheadlcx.health.MyApplication;
import me.aheadlcx.health.R;
import me.aheadlcx.health.api.ApiUtils;
import me.aheadlcx.health.api.HealthNewsListService;
import me.aheadlcx.health.base.BaseFragment;
import me.aheadlcx.health.di.components.DaggerHealthNewsComponents;
import me.aheadlcx.health.di.components.DaggerTestComponent;
import me.aheadlcx.health.di.modules.ActivityModule;
import me.aheadlcx.health.di.modules.HealthNewsModule;
import me.aheadlcx.health.model.HealthNewsItem;
import me.aheadlcx.health.model.HealthNewsListResponse;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/27 下午8:20
 */

public class HealthNewsListFragment extends BaseFragment implements HealthNewslistContract.View {
    private static final String TAG = "HealthNewsListFragment";
    private android.support.v7.widget.RecyclerView recycleView;

    @Inject
    HealthNewslistContract.Present mPresent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_health_newslist, container, false);
        this.recycleView = (RecyclerView) view.findViewById(R.id.recycleView);
        return view;
    }

    @Override
    protected void inject() {
        DaggerHealthNewsComponents.builder()
                .applicationComponent(MyApplication.getInstance().getApplicationComponent())
                .activityModule(new ActivityModule(getActivity()))
                .healthNewsModule(new HealthNewsModule())
                .build().inject(this);
//                .testComponent(DaggerTestComponent.builder().build())
        mPresent.setUi(this);

    }

    @Override
    protected void loadData() {
        mPresent.loadData("1");
    }

    @Override
    public void setData(List<HealthNewsItem> lists) {
        if (lists != null) {
            HealthNewsAdapter adapter = new HealthNewsAdapter(lists
                    , getActivity());
            recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recycleView.setAdapter(adapter);
        }

    }
}
