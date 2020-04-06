package com.all_latest_in_android.data.remote;

import com.all_latest_in_android.data.remote.model.HeadlineResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {

    @GET("/v2/everything")
    Call<HeadlineResponse> loadHeadlineData(@Query("q") String bitcoin, @Query("from") String publishedAt, @Query("sortBy") String sortBy, @Query("apiKey") String apiKey);

}
