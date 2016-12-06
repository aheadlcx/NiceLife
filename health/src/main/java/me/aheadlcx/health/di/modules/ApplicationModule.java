package me.aheadlcx.health.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.aheadlcx.health.MyApplication;
import me.aheadlcx.health.data.repository.HealthNewsDataRepository;
import me.aheadlcx.health.di.JobExecutor;
import me.aheadlcx.health.di.PerActivity;
import me.aheadlcx.health.di.UiThread;
import me.aheadlcx.health.domain.executor.PostExecutionThread;
import me.aheadlcx.health.domain.executor.ThreadExecutor;
import me.aheadlcx.health.domain.repository.HealthNewsRepository;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午10:26
 */

@Module
public class ApplicationModule {
    private MyApplication mApplication;

    public ApplicationModule(MyApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext(){
        return mApplication;
    }

    @Provides
    @Singleton
    public PostExecutionThread providePostExecutionThread(UiThread uiThread){
        return uiThread;
    }

    @Provides
    @Singleton
    public ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor){
        return jobExecutor;
    }

    @Provides
    @Singleton
    public HealthNewsRepository provideHealthNewsRepo(HealthNewsDataRepository repository){
        return repository;
    }

}
