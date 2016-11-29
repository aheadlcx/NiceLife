package me.aheadlcx.health.ui.health.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import me.aheadlcx.health.MyApplication;
import me.aheadlcx.health.R;
import me.aheadlcx.health.api.ApiUtils;
import me.aheadlcx.health.api.ImageUtils;
import me.aheadlcx.health.base.BaseFragment;
import me.aheadlcx.health.di.components.DaggerHealthNewsComponents;
import me.aheadlcx.health.di.modules.ActivityModule;
import me.aheadlcx.health.di.modules.HealthNewsModule;
import me.aheadlcx.health.model.HealthNewsDetailResponse;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/29 下午8:05
 */

public class HealthNewsDetailFragment extends BaseFragment implements HealthNewsDetailContract.UI{

    private TextView txtTitle;
    private TextView txtDes;
    private ImageView imgDetail;
    private TextView txtContent;

    @Inject
    HealthNewsDetailPresent mPresent;
    long id;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_health_news_detail, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        this.txtContent = (TextView) view.findViewById(R.id.txtContent);
        this.imgDetail = (ImageView) view.findViewById(R.id.imgDetail);
        this.txtDes = (TextView) view.findViewById(R.id.txtDes);
        this.txtTitle = (TextView) view.findViewById(R.id.txtTitle);
    }

    @Override
    protected void inject() {
        DaggerHealthNewsComponents.builder().activityModule(new ActivityModule(getActivity()))
                .healthNewsModule(new HealthNewsModule())
                .applicationComponent(MyApplication.getInstance().getApplicationComponent())
                .build().inject(this);
    }

    @Override
    protected void loadData() {
        mPresent.setUi(this);
        mPresent.loadData(id);
    }

    @Override
    public void initIntent() {
         id = getActivity().getIntent().getLongExtra("id", -1l);
    }

    @Override
    public void setData(HealthNewsDetailResponse response) {
        txtTitle.setText(response.getTitle());
        txtDes.setText(response.getDescription());
        txtContent.setText(Html.fromHtml(response.getMessage()));
        Glide.with(this).load(ImageUtils.getImageUrl(response.getImg())).into(imgDetail);
    }
}
