package com.praszapps.retrofitlivedata.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.praszapps.retrofitlivedata.model.DataRepository;
import com.praszapps.retrofitlivedata.model.data.github.Organization;

import java.util.List;


public class OrganizationsViewModel extends AndroidViewModel {

    public OrganizationsViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Organization>> getOrganizationListData() {
        return DataRepository.INSTANCE.getOrganizationsListData();
    }
}
