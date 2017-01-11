package me.aheadlcx.health.ui.health;

import java.util.List;

import me.aheadlcx.health.constant.HealthType;
import me.aheadlcx.health.model.HealthNewsItem;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/27 下午8:26
 */

public interface HealthNewslistContract {
    interface View {
        void setData(List<HealthNewsItem> lists);

        void addData(List<HealthNewsItem> lists);
    }

    interface Present {
        void loadFirst(String page);

        void loadData(String page, final int healthType);

        void loadMoreData(String page);

        void setHealthType( int healthType);

        public HealthNewsListPresent setUi(HealthNewslistContract.View ui);

    }

}