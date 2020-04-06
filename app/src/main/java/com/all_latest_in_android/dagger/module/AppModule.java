package com.all_latest_in_android.dagger.module;

import android.app.Application;
import androidx.room.Room;

import com.all_latest_in_android.BuildConfig;
import com.all_latest_in_android.data.local.HeadlineDatabase;
import com.all_latest_in_android.data.local.dao.HeadlineDao;
import com.all_latest_in_android.data.remote.ApiConstants;
import com.all_latest_in_android.data.remote.RequestInterceptor;
import com.all_latest_in_android.data.remote.RestApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module(includes = ViewModelModule.class)
public class AppModule {
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.addInterceptor(new RequestInterceptor());
        okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    RestApi provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(RestApi.class);
    }

    @Provides
    @Singleton
    HeadlineDatabase provideHeadlineDatabase(Application application) {
        return Room.databaseBuilder(application, HeadlineDatabase.class, "headlines.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    HeadlineDao provideHeadlineDao(HeadlineDatabase headlineDatabase) {
        return headlineDatabase.headlineDao();
    }
}
