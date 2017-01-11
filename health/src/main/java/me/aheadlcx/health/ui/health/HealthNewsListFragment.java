package me.aheadlcx.health.ui.health;

import android.app.ActivityOptions;
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
import android.widget.ImageView;

import java.util.List;

import javax.inject.Inject;

import me.aheadlcx.health.MyApplication;
import me.aheadlcx.health.R;
import me.aheadlcx.health.constant.IntentKey;
import me.aheadlcx.health.di.modules.ActivityModule;
import me.aheadlcx.health.di.modules.HealthNewsModule;
import me.aheadlcx.health.di.modules.HealthNewslistModule;
import me.aheadlcx.health.model.HealthNewsItem;
import me.aheadlcx.health.ui.health.detail.HealthNewsDetailActivity;
import me.aheadlcx.health.util.DensityUtil;
import me.aheadlcx.uilib.uikit.loadmore.OnLoadMoreListener;
import me.aheadlcx.uilib.uikit.loadmore.RecyclerViewWithFooter;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/27 下午8:20
 */

public class HealthNewsListFragment extends HealthBaseFragment implements HealthNewslistContract.View {
    private static final String TAG = "HealthNewsListFragment";
    private RecyclerViewWithFooter recycleView;

    @Inject
    HealthNewslistContract.Present mPresent;
    int originPage = 1;
    int curPage = originPage;
    HealthNewsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_health_newslist, container, false);
        init();
        initView(view);
        initListener();
        return view;
    }

    private void init() {
        adapter = new HealthNewsAdapter(null
                , getActivity());
    }

    private void initListener() {
        recycleView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadNextPage();
            }
        });

        adapter.setCallBack(new HealthNewsAdapter.HealthCallBack() {
            @Override
            public void onClick(HealthNewsItem item, ImageView itemImage) {
                Intent intent = new Intent(getActivity(), HealthNewsDetailActivity.class);
                intent.putExtra("id", ((long) (item.getId())));
                intent.putExtra(IntentKey.HEALTH_TYPE, getHealthType());

                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation(getActivity(), itemImage, itemImage.getTransitionName());
                getActivity().startActivity(intent, options.toBundle());
            }
        });
        recycleView.setAdapter(adapter);
    }

    private void initView(View view) {
        this.recycleView = (RecyclerViewWithFooter) view.findViewById(R.id.recycleView);
        recycleView.addItemDecoration(new RecyclerView.ItemDecoration() {
            Paint mPaint = new Paint();

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(0, 0, 0, DensityUtil.dip2px(8));
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
        MyApplication.getInstance().getApplicationComponent().plus(new ActivityModule(getActivity()))
                .plus(new HealthNewsModule())
                .plus(new HealthNewslistModule())
                .inject(this);
//        DaggerHealthNewsComponents.builder()
//                .applicationComponent(MyApplication.getInstance().getApplicationComponent())
//                .activityModule(new ActivityModule(getActivity()))
//                .healthNewsModule(new HealthNewsModule())
//                .build().inject(this);
        mPresent.setUi(this);
//        int healthType = getArguments().getInt(IntentKey.HEALTH_TYPE, HealthType.TYPE_INFO);
        mPresent.setHealthType(getHealthType());


    }

    @Override
    protected void loadData() {
        mPresent.loadFirst("1");
    }

    private void loadNextPage() {
        Log.i(TAG, "loadNextPage: ");
//        recycleView.setLoading();
        curPage++;
        mPresent.loadMoreData(curPage + "");
    }


    @Override
    public void setData(List<HealthNewsItem> lists) {
        Log.i(TAG, "setData: ");
        if (lists != null && lists.size() != 0) {
            adapter.setList(lists);
            recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recycleView.setPullToLoad();
        }
    }

    @Override
    public void addData(List<HealthNewsItem> lists) {
        Log.i(TAG, "addData: ");
        if (lists != null && lists.size() != 0) {
            recycleView.setPullToLoad();
            adapter.addData(lists);
        }
    }

}
