package com.all_latest_in_android.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.all_latest_in_android.data.local.entity.HeadlineEntity;
import com.all_latest_in_android.data.remote.repository.HeadlineRepository;
import com.all_latest_in_android.ui.callbacks.ResponseListener;
import com.all_latest_in_android.utils.SingleLiveEvent;

import javax.inject.Inject;

public class HeadlineDetailsViewModel extends ViewModel {

    private String url;

    private HeadlineRepository headlineRepository;

    private MutableLiveData<HeadlineEntity> headlineEntityMutableLiveData = new MutableLiveData<>();

    private SingleLiveEvent<Void> errorMessageRecieved = new SingleLiveEvent<>();

    public MutableLiveData<HeadlineEntity> getHeadlineEntityMutableLiveData() {
        return headlineEntityMutableLiveData;
    }

    public void setHeadlineEntityMutableLiveData(MutableLiveData<HeadlineEntity> headlineEntityMutableLiveData) {
        this.headlineEntityMutableLiveData = headlineEntityMutableLiveData;
    }

    public SingleLiveEvent<Void> getErrorMessageRecieved() {
        return errorMessageRecieved;
    }

    public void setErrorMessageRecieved(SingleLiveEvent<Void> errorMessageRecieved) {
        this.errorMessageRecieved = errorMessageRecieved;
    }

    @Inject
    HeadlineDetailsViewModel(HeadlineRepository artRepository) {
        this.headlineRepository = artRepository;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void loadHeadlineDetails(){
        if(headlineRepository != null) {
            headlineRepository.loadHeadlineDetails(url, new ResponseListener() {
                @Override
                public void onSuccess(HeadlineEntity data) {
                    headlineEntityMutableLiveData.setValue(data);
                }

                @Override
                public void onFailure(String message) {
                    // Send event to UI to show thw error
                    errorMessageRecieved.call();
                }
            });
        }
    }
}
