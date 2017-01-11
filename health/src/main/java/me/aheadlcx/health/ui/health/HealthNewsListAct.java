package me.aheadlcx.health.ui.health;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.aheadlcx.health.R;
import me.aheadlcx.health.base.BaseActivity;
import me.aheadlcx.health.constant.IntentKey;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/27 下午7:27
 */

public class HealthNewsListAct extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HealthNewsListFragment fragment = new HealthNewsListFragment();
        int type = getIntent().getIntExtra(IntentKey.HEALTH_TYPE, -1);
        if (type > 0){
//            fragment.getArguments().putInt(IntentKey.HEALTH_TYPE, type);
            fragment.setHealthType(type);
        }
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();
    }
}
