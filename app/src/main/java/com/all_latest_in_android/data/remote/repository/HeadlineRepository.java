package com.all_latest_in_android.data.remote.repository;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.all_latest_in_android.data.local.dao.HeadlineDao;
import com.all_latest_in_android.data.local.entity.HeadlineEntity;
import com.all_latest_in_android.data.remote.NetworkBoundResource;
import com.all_latest_in_android.data.remote.Resource;
import com.all_latest_in_android.data.remote.RestApi;
import com.all_latest_in_android.data.remote.model.HeadlineResponse;
import com.all_latest_in_android.ui.callbacks.ResponseListener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

public class HeadlineRepository {

    private final HeadlineDao headlineDao;
    private final RestApi apiService;

    @Inject
    HeadlineRepository(HeadlineDao dao, RestApi service) {
        this.headlineDao = dao;
        this.apiService = service;
    }

    public LiveData<Resource<List<HeadlineEntity>>> loadHeadlineData(String bitcoinValue, String currentDate, String publishedAtValue, String apiKey) {
        return new NetworkBoundResource<List<HeadlineEntity>, HeadlineResponse>() {

            @Override
            protected void saveCallResult(HeadlineResponse item) {
                if(null != item)
                    headlineDao.saveHeadlines(item.getHeadlinesData());
            }

            @NonNull
            @Override
            protected LiveData<List<HeadlineEntity>> loadFromDb() {
                return headlineDao.loadHeadlines();
            }

            @NonNull
            @Override
            protected Call<HeadlineResponse> createCall() {
                return apiService.loadHeadlineData(bitcoinValue, currentDate, publishedAtValue, apiKey);
            }
        }.getAsLiveData();
    }

    @SuppressLint("CheckResult")
    public void loadHeadlineDetails(String url, ResponseListener responseListener) {
        HeadlineEntity headlineEntityDetails = new HeadlineEntity();
        Observable.fromCallable(() -> {
            Document document = Jsoup.connect(url).get();
            headlineEntityDetails.setTitle(document.title());
            headlineEntityDetails.setContent(document.select("p").text());
            return false;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> responseListener.onSuccess(headlineEntityDetails),
                 (error -> responseListener.onFailure(error.getMessage())));

    }

}
