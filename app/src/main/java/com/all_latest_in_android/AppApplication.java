package com.all_latest_in_android;

import android.app.Activity;
import android.app.Application;
import com.all_latest_in_android.dagger.DaggerAppComponent;
import javax.inject.Inject;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

public class AppApplication extends Application implements HasActivityInjector {
    private static AppApplication sInstance;
    public static AppApplication getAppContext() {
        return sInstance;
    }

    private static synchronized void setInstance(AppApplication app) {
        sInstance = app;
    }


    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        initializeAppComponent();
        setInstance(this);
    }

    protected void initializeAppComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }

}
