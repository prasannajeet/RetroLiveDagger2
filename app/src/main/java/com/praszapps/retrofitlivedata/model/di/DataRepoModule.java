package com.praszapps.retrofitlivedata.model.di;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.praszapps.retrofitlivedata.model.retrofit.NewAPIRequest;
import com.praszapps.retrofitlivedata.model.retrofit.OrganizationsService;
import com.praszapps.retrofitlivedata.util.AppConstants;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataRepoModule {

    @Provides
    public OkHttpClient provideOkHttp() {
        return new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(new HttpLoggingInterceptor())
                .retryOnConnectionFailure(true)
                .build();
    }

    @Provides
    public Gson provideGson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    @Provides
    public Retrofit.Builder provideRetrofitBuilder(OkHttpClient client, Gson gson) {
        return new Retrofit
                .Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson));
    }

    @Provides
    @BaseUrlType("news")
    public Retrofit provideNewsRetrofit(Retrofit.Builder builder) {
        builder.baseUrl(AppConstants.INSTANCE.getNewsBaseUrl());
        return builder.build();
    }

    @Provides
    @BaseUrlType("github")
    public Retrofit provideGithubRetrofit(Retrofit.Builder builder) {
        builder.baseUrl(AppConstants.INSTANCE.getGithubBaseUrl());
        return builder.build();
    }

    @Provides
    public OrganizationsService provideOrgService(@BaseUrlType("github") Retrofit retrofit) {
        return retrofit.create(OrganizationsService.class);
    }

    @Provides
    public NewAPIRequest provideNewsService(@BaseUrlType("news") Retrofit retrofit) {
        return retrofit.create(NewAPIRequest.class);
    }

    /*@Provides
    public Call<List<Organization>> provideOrgListCall(OrganizationsService service, int since) {
        return service.getOrganizations(since);
    }

    @Provides
    public Call<NewsResponse> provideNewsCall(NewAPIRequest newsService, String country, String apikey) {
        return newsService.getHeadlines(country, apikey);
    }*/

}
