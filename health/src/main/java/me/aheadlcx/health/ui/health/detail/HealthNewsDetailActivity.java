package me.aheadlcx.health.ui.health.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import me.aheadlcx.health.base.BaseActivity;
import me.aheadlcx.health.constant.HealthType;
import me.aheadlcx.health.constant.IntentKey;
import me.aheadlcx.health.model.HealthNewsItem;
import me.aheadlcx.health.test.TestModel;
import me.aheadlcx.health.ui.health.HealthNewsListFragment;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/29 下午8:05
 */

public class HealthNewsDetailActivity extends BaseActivity {
    private static final String TAG = "HealthNewsDetailActivit";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HealthNewsDetailFragment fragment = new
                HealthNewsDetailFragment();
        int healthType = getIntent().getIntExtra(IntentKey.HEALTH_TYPE, HealthType.TYPE_INFO);
        fragment.setHealthType(healthType);
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();
    }
}
