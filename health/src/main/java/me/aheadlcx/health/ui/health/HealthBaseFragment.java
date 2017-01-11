package me.aheadlcx.health.ui.health;

import me.aheadlcx.health.base.BaseFragment;
import me.aheadlcx.health.constant.HealthType;

/**
 * Description:
 * author: aheadlcx
 * Date:2017/1/10 下午8:00
 */

public abstract class HealthBaseFragment extends BaseFragment {
    private int healthType = HealthType.TYPE_INFO;

    public void setHealthType(int healthType) {
        this.healthType = healthType;
    }

    public int getHealthType() {
        return healthType;
    }
}
