package com.example.msntest.modul.viewdata;

import com.example.msntest.core.repo.AppRepo;
import com.example.msntest.modul.viewdata.contract.ViewDataUserRegisteredPresenterContract;
import com.example.msntest.modul.viewdata.contract.ViewDataUserRegisteredViewContract;

public class ViewDataUserRegisteredPresenter implements ViewDataUserRegisteredPresenterContract {
    private final AppRepo mRepo;
    private final ViewDataUserRegisteredViewContract mView;

    public ViewDataUserRegisteredPresenter(AppRepo repo, ViewDataUserRegisteredViewContract viewContract) {
        this.mRepo = repo;
        this.mView = viewContract;
        this.mView.setPresenter(this);
    }


    @Override
    public void doGetAllDataRegister() {
        mView.showContentLoading();
        mRepo.doGetAllDataRegister(var1 -> {
            if (var1.size() > 0) {
                mView.doGetAllDataRegisterSuccess(var1);
            } else {
                mView.doGetAllDataRegisterFailed();
            }
            mView.hideContentLoading();
        });

    }
}
