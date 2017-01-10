package me.aheadlcx.health.domain.interactor;

import javax.inject.Inject;

import me.aheadlcx.health.constant.HealthType;
import me.aheadlcx.health.data.repository.HealthNewsDataRepository;
import me.aheadlcx.health.di.test.TestDi;
import me.aheadlcx.health.domain.executor.PostExecutionThread;
import me.aheadlcx.health.domain.executor.ThreadExecutor;
import me.aheadlcx.health.domain.repository.HealthNewsRepository;
import rx.Observable;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午9:16
 */

public class HealthNewsListCase extends Case {
    private final HealthNewsRepository mRepository;

    @Inject
    public HealthNewsListCase(HealthNewsRepository repository, PostExecutionThread
            postExecutionThread, ThreadExecutor threadExecutor) {
        super(postExecutionThread, threadExecutor);
        mRepository = repository;
    }

    @Override
    public Observable caseListObservable(String page, @HealthType final int healthType, boolean isLoadMore) {
        return mRepository.healthNewsListObservabler(page, getSubscribeOnScheduler(),
                getObserveOnScheduler(), healthType, isLoadMore);
    }

    @Override
    public void connect() {
        super.connect();
        if (mRepository instanceof HealthNewsDataRepository){
            ((HealthNewsDataRepository)mRepository).connect();
        }
    }
}
