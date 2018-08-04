package com.praszapps.retrofitlivedata.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.praszapps.retrofitlivedata.model.github.Organization;
import com.praszapps.retrofitlivedata.model.news.NewsResponse;
import com.praszapps.retrofitlivedata.model.retrofit.NewAPIRequest;
import com.praszapps.retrofitlivedata.model.retrofit.OrganizationsService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public enum DataRepository {

    INSTANCE;

    public LiveData<NewsResponse> getHeadlinesData(String country, String apiKey) {
        final MutableLiveData<NewsResponse> data = new MutableLiveData<>();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(new HttpLoggingInterceptor())
                .retryOnConnectionFailure(true)
                .build();

        NewAPIRequest request = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(NewAPIRequest.class);

        request.getHeadlines(country, apiKey).enqueue(new Callback<NewsResponse>() {
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

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        OkHttpClient client = new OkHttpClient.Builder()
                                .connectTimeout(2, TimeUnit.MINUTES)
                                .readTimeout(2, TimeUnit.MINUTES)
                                .writeTimeout(2, TimeUnit.MINUTES)
                                .retryOnConnectionFailure(true)
                                .build();

        OrganizationsService service = new Retrofit.Builder()
                                        .baseUrl("https://api.github.com")
                                        .addConverterFactory(GsonConverterFactory.create(gson))
                                        .client(client)
                                        .build()
                                        .create(OrganizationsService.class);

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

            }
        });
        return organizationList;
    }
}
