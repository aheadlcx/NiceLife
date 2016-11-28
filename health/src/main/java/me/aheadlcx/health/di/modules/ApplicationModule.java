package me.aheadlcx.health.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.aheadlcx.health.MyApplication;
import me.aheadlcx.health.di.JobExecutor;
import me.aheadlcx.health.di.PerActivity;
import me.aheadlcx.health.di.UiThread;
import me.aheadlcx.health.domain.executor.PostExecutionThread;
import me.aheadlcx.health.domain.executor.ThreadExecutor;

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
//    @PerActivity
    public Context provideApplicationContext(){
        return mApplication;
    }

    @Provides
    @Singleton
//    @PerActivity
    public PostExecutionThread providePostExecutionThread(UiThread uiThread){
        return uiThread;
    }

    @Provides
    @Singleton
//    @PerActivity
    public ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor){
        return jobExecutor;
    }
}
