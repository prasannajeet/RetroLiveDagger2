package com.praszapps.retrofitlivedata.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.praszapps.retrofitlivedata.R;
import com.praszapps.retrofitlivedata.model.pojo.github.Organization;
import com.praszapps.retrofitlivedata.model.pojo.news.NewsResponse;
import com.praszapps.retrofitlivedata.viewmodel.NewsViewModel;
import com.praszapps.retrofitlivedata.viewmodel.OrganizationsViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NewsViewModel mViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        OrganizationsViewModel nViewModel = ViewModelProviders.of(this).get(OrganizationsViewModel.class);

        mViewModel.getResponse().observe(this, new Observer<NewsResponse>() {
            @Override
            public void onChanged(@Nullable NewsResponse newsResponse) {
                if (newsResponse != null) {
                    Log.d("YOYOYO", newsResponse.toString());
                } else {
                    Log.d("YOYOYO", "News view model is null");
                }
            }
        });

        nViewModel.getOrganizationListData().observe(this, new Observer<List<Organization>>() {
            @Override
            public void onChanged(@Nullable List<Organization> organizations) {
                if (organizations != null) {
                    if (organizations.size() > 0) {
                        Log.d("YOYOYO", organizations.toString());
                    } else {
                        Log.d("YOYOYO", "Org view model is empty");
                    }
                } else {
                    Log.d("YOYOYO", "Org view model is null");
                }
            }
        });
    }
}
