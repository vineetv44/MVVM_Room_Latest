package com.all_latest_in_android.api;

import com.all_latest_in_android.BuildConfig;
import com.all_latest_in_android.data.remote.ApiConstants;
import com.all_latest_in_android.data.remote.RequestInterceptor;
import com.all_latest_in_android.data.remote.RestApi;
import com.all_latest_in_android.utils.CommonUtils;
import com.all_latest_in_android.utils.Constants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static junit.framework.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ApiServiceUnitTest {

    private RestApi restApiService;

    @Before
    public void createService() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.addInterceptor(new RequestInterceptor());
        okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

        restApiService = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build()
                .create(RestApi.class);
    }


    @Test
    public void getHeadlineResponseData() {
        try {
            Response response = restApiService.loadHeadlineData(Constants.BITCOIN_ID, CommonUtils.getCurrentDate(),
                    Constants.PUBLISHED_AT_ID, Constants.APP_ID).execute();
            assertEquals(response.code(), 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
