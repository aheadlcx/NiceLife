package me.aheadlcx.health.ui.health;

import java.util.List;

import me.aheadlcx.health.model.HealthNewsItem;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/27 下午8:26
 */

public interface HealthNewslistContract {
    interface View {
        void setData(List<HealthNewsItem> lists);
    }

    interface Present {
        void loadData(String page);

        public HealthNewsListPresent setUi(HealthNewslistContract.View ui);

    }

}