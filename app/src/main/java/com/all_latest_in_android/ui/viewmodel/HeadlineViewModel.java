package com.all_latest_in_android.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.all_latest_in_android.data.local.entity.HeadlineEntity;
import com.all_latest_in_android.data.remote.Resource;
import com.all_latest_in_android.data.remote.repository.HeadlineRepository;
import com.all_latest_in_android.utils.CommonUtils;
import com.all_latest_in_android.utils.Constants;
import java.util.List;
import javax.inject.Inject;

public class HeadlineViewModel extends ViewModel {
    private final LiveData<Resource<List<HeadlineEntity>>> headlineResourceData;

    @Inject
    public HeadlineViewModel(HeadlineRepository headlineEntity) {
        headlineResourceData = headlineEntity.loadHeadlineData(Constants.BITCOIN_ID,CommonUtils.getCurrentDate(),
                Constants.PUBLISHED_AT_ID, Constants.APP_ID);
    }

    public LiveData<Resource<List<HeadlineEntity>>> getHeadlineResourceData() {
        return headlineResourceData;
    }
}
