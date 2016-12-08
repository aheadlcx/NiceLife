package me.aheadlcx.uilib.uikit.loadmore;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import me.aheadlcx.uilib.R;

public class DefaultFootItem extends FootItem {
    private static final String TAG = "DefaultFootItem";
    private TextView mEndTextView;
    private TextView mLoadingText;
    private ProgressBar mProgressBar;
    private TextView mPullToLoadText;

    public View onCreateView(ViewGroup parent) {
//        "layout_inflater"
        View view = ((LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R
                .layout
                .rv_with_footer_loading, parent, false);
        this.mProgressBar = (ProgressBar) view.findViewById(R.id.rv_with_footer_loading_progress);
        this.mEndTextView = (TextView) view.findViewById(R.id.rv_with_footer_loading_end);
        this.mLoadingText = (TextView) view.findViewById(R.id.rv_with_footer_loading_load);
        this.mPullToLoadText = (TextView) view.findViewById(R.id.rv_with_footer_loading_pull_to_load);
        return view;
    }

    public void onBindData(View view, int state) {
        if (state == 1) {
            if (TextUtils.isEmpty(this.loadingText)) {
                showProgressBar(view.getContext().getResources().getString(R.string.rv_with_footer_loading));
            } else {
                showProgressBar(this.loadingText);
            }
        } else if (state == 0) {
            if (TextUtils.isEmpty(this.endText)) {
                showPullToLoad(view.getContext().getResources().getString(R.string.rv_with_footer_empty));
            } else {
                showPullToLoad(this.endText);
            }
        } else if (state != 4) {
        } else {
            if (TextUtils.isEmpty(this.pullToLoadText)) {
                showPullToLoad(view.getContext().getResources().getString(R.string.rv_with_footer_pull_load_more));
            } else {
                showPullToLoad(this.pullToLoadText);
            }
        }
    }

    public void showPullToLoad(CharSequence message) {
        this.mPullToLoadText.setVisibility(View.VISIBLE);
        this.mEndTextView.setVisibility(View.GONE);
        this.mProgressBar.setVisibility(View.GONE);
        this.mLoadingText.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(message)) {
            this.mPullToLoadText.setText(message);
        }
    }

    public void showProgressBar(CharSequence load) {
        this.mEndTextView.setVisibility(View.GONE);
        this.mProgressBar.setVisibility(View.VISIBLE);
        this.mPullToLoadText.setVisibility(View.GONE);
        if (TextUtils.isEmpty(load)) {
            this.mLoadingText.setVisibility(View.GONE);
            return;
        }
        this.mLoadingText.setVisibility(View.VISIBLE);
        this.mLoadingText.setText(load);
    }

    public void showEnd(CharSequence end) {
        this.mEndTextView.setVisibility(View.VISIBLE);
        this.mPullToLoadText.setVisibility(View.GONE);
        this.mProgressBar.setVisibility(View.GONE);
        this.mLoadingText.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(end)) {
            this.mEndTextView.setText(end);
        }
    }
}
