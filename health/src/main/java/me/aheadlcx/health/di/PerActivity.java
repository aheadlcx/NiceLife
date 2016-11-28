package me.aheadlcx.health.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午10:46
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
