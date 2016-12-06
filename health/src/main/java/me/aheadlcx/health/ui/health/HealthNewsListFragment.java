package me.aheadlcx.health.ui.health;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

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
import me.aheadlcx.health.test.TestListAct;
import me.aheadlcx.health.util.DensityUtil;
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
        initView(view);
        return view;
    }

    private void initView(View view) {
        this.recycleView = (RecyclerView) view.findViewById(R.id.recycleView);
        recycleView.addItemDecoration(new RecyclerView.ItemDecoration() {
            Paint mPaint = new Paint();
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(0,0,0, DensityUtil.dip2px(8));
            }

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                mPaint.setColor(getResources().getColor(R.color.app_color_gray_light));
                int paddingLeft = parent.getPaddingLeft();
                int right = parent.getWidth() - parent.getPaddingRight();
                c.drawRect(paddingLeft, 0, right, DensityUtil.dip2px(8), mPaint);
            }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
            }
        });
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
        if (lists != null && lists.size() != 0) {
            HealthNewsAdapter adapter = new HealthNewsAdapter(lists
                    , getActivity());
            recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recycleView.setAdapter(adapter);
        }

    }
}
