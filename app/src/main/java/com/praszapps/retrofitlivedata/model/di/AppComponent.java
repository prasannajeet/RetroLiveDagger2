package com.praszapps.retrofitlivedata.model.di;

import com.praszapps.retrofitlivedata.model.DataRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DataRepoModule.class})
public interface AppComponent {
    void inject(DataRepository repository);
}