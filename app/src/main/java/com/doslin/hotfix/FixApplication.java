package com.doslin.hotfix;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by doslin on 2018/1/25.
 */

public class FixApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        MultiDex.install(this);
        FixManager.loadDex(base);
        super.attachBaseContext(base);
    }
}
