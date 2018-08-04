package com.praszapps.retrofitlivedata.model.retrofit;

import com.praszapps.retrofitlivedata.model.news.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewAPIRequest {
    @GET("v2/top-headlines")
    Call<NewsResponse> getHeadlines(@Query("country") String country,
                                   @Query("apikey") String apiKey);
}