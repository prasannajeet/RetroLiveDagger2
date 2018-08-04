package com.praszapps.retrofitlivedata.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.praszapps.retrofitlivedata.model.DataRepository;
import com.praszapps.retrofitlivedata.model.pojo.news.NewsResponse;

public class NewsViewModel extends AndroidViewModel {

    private LiveData<NewsResponse> response;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        response = DataRepository.INSTANCE.getHeadlines("in", "b5a76be430304e1fbe4b1db2004b2ad2");
    }

    public LiveData<NewsResponse> getResponse() {
        return response;
    }
}
