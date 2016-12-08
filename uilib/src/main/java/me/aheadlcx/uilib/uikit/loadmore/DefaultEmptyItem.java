package me.aheadlcx.uilib.uikit.loadmore;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import me.aheadlcx.uilib.R;

public class DefaultEmptyItem extends EmptyItem {
    private ImageView mEmptyImageView;
    private TextView mEmptyTextView;

    public View onCreateView(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_with_footer_empty_layout, parent, false);
        view.setLayoutParams(new LayoutParams(parent.getMeasuredWidth(), parent.getMeasuredHeight()));
        this.mEmptyTextView = (TextView) view.findViewById(R.id.rv_with_footer_empty_title);
        this.mEmptyImageView = (ImageView) view.findViewById(R.id.rv_with_footer_empty_icon);
        return view;
    }

    public void onBindData(View view) {
        if (TextUtils.isEmpty(this.mEmptyText)) {
            this.mEmptyTextView.setVisibility(View.GONE);
        } else {
            this.mEmptyTextView.setVisibility(View.VISIBLE);
            this.mEmptyTextView.setText(this.mEmptyText);
        }
        if (this.mEmptyIconRes != -1) {
            this.mEmptyImageView.setImageResource(this.mEmptyIconRes);
        }
    }
}
