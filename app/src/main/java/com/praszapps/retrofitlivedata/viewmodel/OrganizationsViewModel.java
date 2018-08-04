package com.praszapps.retrofitlivedata.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.praszapps.retrofitlivedata.model.DataRepository;
import com.praszapps.retrofitlivedata.model.github.Organization;

import java.util.List;


public class OrganizationsViewModel extends AndroidViewModel {

    private MutableLiveData<List<OrganizationsViewModel>> mViewModel;
    private String error;

    public OrganizationsViewModel(@NonNull Application application) {
        super(application);
    }

    public String getErrorMessage() {
        return error;
    }

    public MutableLiveData<List<Organization>> getOrganizationListData() {
        return DataRepository.INSTANCE.getOrganizationsListData();
    }
}
