package com.all_latest_in_android.dagger;

import com.all_latest_in_android.ui.MainActivity;
import com.all_latest_in_android.ui.MainActivityProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = MainActivityProvider.class)
    abstract MainActivity mainActivity();

}
