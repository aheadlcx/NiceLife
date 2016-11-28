package me.aheadlcx.health.model;

import java.util.List;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/27 下午6:56
 */

public class HealthNewsListResponse {

    private boolean status;
    private int total;
    private List<HealthNewsItem> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<HealthNewsItem> getTngou() {
        return tngou;
    }

    public void setTngou(List<HealthNewsItem> tngou) {
        this.tngou = tngou;
    }

}
