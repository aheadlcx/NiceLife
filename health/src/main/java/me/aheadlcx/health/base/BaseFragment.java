package me.aheadlcx.health.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/27 下午8:20
 */

public abstract class BaseFragment extends Fragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inject();
        loadData();
    }

    protected abstract void inject();

    protected abstract void loadData();
}
