package me.aheadlcx.health.constant;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static me.aheadlcx.health.constant.HealthType.TYPE_INFO;
import static me.aheadlcx.health.constant.HealthType.TYPE_LORE;

/**
 * Description:
 * author: aheadlcx
 * Date:2017/1/10 下午4:48
 */
@IntDef({TYPE_INFO, TYPE_LORE})
@Retention(RetentionPolicy.SOURCE)
public @interface HealthType {
    //资讯
    int TYPE_INFO = 0x00000000;
    //知识
    int TYPE_LORE = 0x00000004;
}
