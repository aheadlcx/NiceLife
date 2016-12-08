package me.aheadlcx.uilib.uikit.loadmore;

import android.view.View;
import android.view.ViewGroup;

public abstract class EmptyItem {
    int mEmptyIconRes = -1;
    CharSequence mEmptyText;

    abstract void onBindData(View view);

    abstract View onCreateView(ViewGroup viewGroup);
}
