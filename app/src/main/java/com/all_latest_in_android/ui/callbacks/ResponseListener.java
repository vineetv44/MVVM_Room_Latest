package com.all_latest_in_android.ui.callbacks;

import com.all_latest_in_android.data.local.entity.HeadlineEntity;

public interface ResponseListener {

    void onSuccess(HeadlineEntity data);
    void onFailure(String message);
}
