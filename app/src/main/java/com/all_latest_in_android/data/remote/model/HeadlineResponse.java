package com.all_latest_in_android.data.remote.model;

import com.all_latest_in_android.data.local.entity.HeadlineEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HeadlineResponse {

    @SerializedName("articles")
    private List<HeadlineEntity> headlineEntitiesResp;

    public List<HeadlineEntity> getHeadlinesData() {
        return headlineEntitiesResp;
    }

    @SuppressWarnings("unused")
    public void setHeadlineRespData(List<HeadlineEntity> headlineEntitiesResp) {
        this.headlineEntitiesResp = headlineEntitiesResp;
    }
}
