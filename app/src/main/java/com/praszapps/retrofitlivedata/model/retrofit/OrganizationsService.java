package com.praszapps.retrofitlivedata.model.retrofit;

import com.praszapps.retrofitlivedata.model.data.github.Organization;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OrganizationsService {

    @GET("organizations")
    Call<List<Organization>> getOrganizations(@Query("since") int since);
}
