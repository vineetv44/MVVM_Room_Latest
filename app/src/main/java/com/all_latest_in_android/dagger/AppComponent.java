package com.all_latest_in_android.dagger;

import android.app.Application;
import com.all_latest_in_android.AppApplication;
import com.all_latest_in_android.dagger.module.AppModule;
import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class})

public interface AppComponent {
    void inject(AppApplication aaApp);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}