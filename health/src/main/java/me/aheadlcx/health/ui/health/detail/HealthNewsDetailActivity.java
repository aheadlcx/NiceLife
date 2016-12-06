package me.aheadlcx.health.ui.health.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import me.aheadlcx.health.base.BaseActivity;
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
        TestModel model = (TestModel) getIntent().getSerializableExtra("test");
        if (model == null) {
            Log.i(TAG, "onCreate: model == null");
        }else {
            Log.i(TAG, "onCreate: " + model.name + ((HealthNewsItem) (model.mList.get(0))).getTitle());
        }
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, new
                HealthNewsDetailFragment()).commit();
    }
}
