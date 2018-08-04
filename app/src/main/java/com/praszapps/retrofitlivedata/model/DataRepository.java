package com.praszapps.retrofitlivedata.model;

import android.arch.lifecycle.MutableLiveData;

import com.praszapps.retrofitlivedata.model.di.DaggerAppComponent;
import com.praszapps.retrofitlivedata.model.di.DataRepoModule;
import com.praszapps.retrofitlivedata.model.pojo.github.Organization;
import com.praszapps.retrofitlivedata.model.pojo.news.NewsResponse;
import com.praszapps.retrofitlivedata.model.provider.NetworkProvider;
import com.praszapps.retrofitlivedata.model.retrofit.HeadlinesService;
import com.praszapps.retrofitlivedata.model.retrofit.OrganizationsService;

import java.util.List;

import javax.inject.Inject;

public enum DataRepository {

    INSTANCE;

    @Inject
    HeadlinesService request;
    @Inject
    OrganizationsService service;

    private boolean isInit = false;

    private void init() {
        if (!isInit) {
            DaggerAppComponent.builder().dataRepoModule(new DataRepoModule()).build().inject(this);
            isInit = true;
        }
    }

    public MutableLiveData<List<Organization>> getOrganisation(int value) {
        init();
        return NetworkProvider.INSTANCE.getOrganizationsListData(service, value);
    }

    public MutableLiveData<NewsResponse> getHeadlines(String country, String apiKey) {
        init();
        return NetworkProvider.INSTANCE.getHeadlinesData(request, country, apiKey);
    }


}
