package com.all_latest_in_android.ui;

import com.all_latest_in_android.ui.headline.HeadlineDetailFragment;
import com.all_latest_in_android.ui.headline.HeadlineFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Vineet Verma on 02/11/19.
 */
@Module
public abstract class MainActivityProvider {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract HeadlineFragment contributeArticleListFragment();

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract HeadlineDetailFragment contributeArticleDetailFragment();

}
