package me.aheadlcx.health.ui.health.detail;

import javax.inject.Inject;

import me.aheadlcx.health.constant.HealthType;
import me.aheadlcx.health.di.Type;
import me.aheadlcx.health.domain.interactor.Case;
import me.aheadlcx.health.domain.interactor.DefaultSubscriber;
import me.aheadlcx.health.model.HealthNewsDetailResponse;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/29 下午8:36
 */

public class HealthNewsDetailPresent implements HealthNewsDetailContract.Present {
    private HealthNewsDetailContract.UI mUI;
    private Case mCase;
    private int healthType = HealthType.TYPE_INFO;
    @Inject
    public HealthNewsDetailPresent(@Type("detail") Case detailCase) {
        mCase = detailCase;
    }

    @Override
    public void setUi(HealthNewsDetailContract.UI ui) {
        mUI = ui;
    }
    public void setHealthType(int healthType){
        this.healthType = healthType;
    }

    private int getHealthType(){
        return healthType;
    }
    @Override
    public void loadData(long id, int healthType) {
        mCase.execute(id, mSubscriber, healthType);

    }

    private DefaultSubscriber<HealthNewsDetailResponse> mSubscriber = new
            DefaultSubscriber<HealthNewsDetailResponse>(){
                @Override
                public void onNext(HealthNewsDetailResponse healthNewsDetailResponse) {
                    super.onNext(healthNewsDetailResponse);
                    mUI.setData(healthNewsDetailResponse);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                }
            };
}
