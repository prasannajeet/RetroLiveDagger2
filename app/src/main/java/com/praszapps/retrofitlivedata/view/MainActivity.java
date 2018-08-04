package com.praszapps.retrofitlivedata.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.praszapps.retrofitlivedata.R;
import com.praszapps.retrofitlivedata.viewmodel.NewsViewModel;
import com.praszapps.retrofitlivedata.model.news.NewsResponse;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NewsViewModel mViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        mViewModel.getResponse().observe(this, new Observer<NewsResponse>() {
            @Override
            public void onChanged(@Nullable NewsResponse newsResponse) {
                if(newsResponse != null)
                    Log.d("YOYOYO", newsResponse.getArticles().toString());
            }
        });
    }
}
