package me.aheadlcx.health.data.repository;

import javax.inject.Inject;

import me.aheadlcx.health.data.datasource.HealthNewsLocalRepo;
import me.aheadlcx.health.data.datasource.HealthNewsNetRepo;
import me.aheadlcx.health.domain.repository.HealthNewsRepository;
import rx.Observable;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午9:56
 */

public class HealthNewsDataRepository implements HealthNewsRepository {
    private HealthNewsRepository mLocalRepo;
    private HealthNewsRepository mNetRepo;

    @Inject
    public HealthNewsDataRepository() {
        mLocalRepo = new HealthNewsLocalRepo();
        mNetRepo = new HealthNewsNetRepo();
    }

    @Override
    public Observable buildHealthNewsObservabler(String page) {
        return mNetRepo.buildHealthNewsObservabler(page);
    }
}
