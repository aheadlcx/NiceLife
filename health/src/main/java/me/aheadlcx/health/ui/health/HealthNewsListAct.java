package me.aheadlcx.health.ui.health;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.aheadlcx.health.R;
import me.aheadlcx.health.base.BaseActivity;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/27 下午7:27
 */

public class HealthNewsListAct extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, new
                HealthNewsListFragment()).commit();
    }
}
