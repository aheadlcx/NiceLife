package me.aheadlcx.health.ui.health.detail;

import me.aheadlcx.health.constant.HealthType;
import me.aheadlcx.health.model.HealthNewsDetailResponse;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/29 下午8:36
 */

public interface HealthNewsDetailContract {
    interface UI{
        void setData(HealthNewsDetailResponse response);
    }

    interface Present{
        void setUi(UI ui);
        void loadData(long id, int healthType);
        void setHealthType( int healthType);
    }
}
