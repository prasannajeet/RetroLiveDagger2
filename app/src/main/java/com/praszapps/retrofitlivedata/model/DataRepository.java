package com.praszapps.retrofitlivedata.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.praszapps.retrofitlivedata.model.data.github.Organization;
import com.praszapps.retrofitlivedata.model.data.news.NewsResponse;
import com.praszapps.retrofitlivedata.model.di.DaggerAppComponent;
import com.praszapps.retrofitlivedata.model.di.DataRepoModule;
import com.praszapps.retrofitlivedata.model.retrofit.NewAPIRequest;
import com.praszapps.retrofitlivedata.model.retrofit.OrganizationsService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public enum DataRepository {

    INSTANCE;

    @Inject
    NewAPIRequest request;
    @Inject
    OrganizationsService service;

    public LiveData<NewsResponse> getHeadlinesData(String country, String apiKey) {

        DaggerAppComponent.builder().dataRepoModule(new DataRepoModule()).build().inject(this);

        final MutableLiveData<NewsResponse> data = new MutableLiveData<>();

        Call<NewsResponse> responseCall = request.getHeadlines(country, apiKey);

        responseCall.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if(response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                    data.setValue(null);
            }
        });

        return data;
    }


    public MutableLiveData<List<Organization>> getOrganizationsListData() {
        final MutableLiveData<List<Organization>> organizationList = new MutableLiveData<>();

        Call<List<Organization>> organizationListCall = service.getOrganizations(44);

        organizationListCall.enqueue(new Callback<List<Organization>>() {
            @Override
            public void onResponse(Call<List<Organization>> call, Response<List<Organization>> response) {
                if(response.isSuccessful()) {
                    List<Organization> organizations = response.body();
                    if(organizations != null) {
                        organizationList.setValue(organizations);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Organization>> call, Throwable t) {
                organizationList.setValue(null);
            }
        });
        return organizationList;
    }
}
