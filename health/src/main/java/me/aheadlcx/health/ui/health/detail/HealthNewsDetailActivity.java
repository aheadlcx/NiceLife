package me.aheadlcx.health.ui.health.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.aheadlcx.health.base.BaseActivity;
import me.aheadlcx.health.ui.health.HealthNewsListFragment;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/29 下午8:05
 */

public class HealthNewsDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, new
                HealthNewsDetailFragment()).commit();
    }
}
