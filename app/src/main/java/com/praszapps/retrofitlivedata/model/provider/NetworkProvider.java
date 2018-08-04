package com.praszapps.retrofitlivedata.model.provider;

import android.arch.lifecycle.MutableLiveData;

import com.praszapps.retrofitlivedata.model.pojo.github.Organization;
import com.praszapps.retrofitlivedata.model.pojo.news.NewsResponse;
import com.praszapps.retrofitlivedata.model.retrofit.HeadlinesService;
import com.praszapps.retrofitlivedata.model.retrofit.OrganizationsService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public enum NetworkProvider {

    INSTANCE;

    public MutableLiveData<List<Organization>> getOrganizationsListData(OrganizationsService service, int value) {
        final MutableLiveData<List<Organization>> organizationList = new MutableLiveData<>();

        Call<List<Organization>> organizationListCall = service.getOrganizations(value);

        organizationListCall.enqueue(new Callback<List<Organization>>() {
            @Override
            public void onResponse(Call<List<Organization>> call, Response<List<Organization>> response) {
                if (response.isSuccessful()) {
                    List<Organization> organizations = response.body();
                    if (organizations != null) {
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

    public MutableLiveData<NewsResponse> getHeadlinesData(HeadlinesService service, String country, String apiKey) {

        final MutableLiveData<NewsResponse> data = new MutableLiveData<>();

        Call<NewsResponse> responseCall = service.getHeadlines(country, apiKey);

        responseCall.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
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

}
