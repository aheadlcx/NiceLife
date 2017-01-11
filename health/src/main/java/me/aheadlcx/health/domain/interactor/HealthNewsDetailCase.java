package me.aheadlcx.health.domain.interactor;

import javax.inject.Inject;

import me.aheadlcx.health.constant.HealthType;
import me.aheadlcx.health.di.test.TestDi;
import me.aheadlcx.health.domain.executor.PostExecutionThread;
import me.aheadlcx.health.domain.executor.ThreadExecutor;
import me.aheadlcx.health.domain.repository.HealthNewsRepository;
import rx.Observable;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/29 下午8:23
 */

public class HealthNewsDetailCase extends Case {

    private final HealthNewsRepository mRepository;

    @Inject
    public HealthNewsDetailCase(HealthNewsRepository repository, PostExecutionThread
            postExecutionThread, ThreadExecutor threadExecutor) {
        super(postExecutionThread, threadExecutor);
        mRepository = repository;
    }

    @Override
    public Observable caseDetailObservable(long id, @HealthType final int healthType) {
        return mRepository.healthNewsDetailObservabler(id, getSubscribeOnScheduler(),
                getObserveOnScheduler(), healthType);
    }
}
