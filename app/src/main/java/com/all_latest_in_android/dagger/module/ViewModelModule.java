package com.all_latest_in_android.dagger.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.all_latest_in_android.ViewModelFactory;
import com.all_latest_in_android.dagger.ViewModelKey;
import com.all_latest_in_android.ui.viewmodel.HeadlineDetailsViewModel;
import com.all_latest_in_android.ui.viewmodel.HeadlineViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);

    @Binds
    @IntoMap
    @ViewModelKey(HeadlineViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsHeadlineViewModel(HeadlineViewModel headlineViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HeadlineDetailsViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsHeadlineDetailsViewModel(HeadlineDetailsViewModel headlineDetailsViewModel);


}
