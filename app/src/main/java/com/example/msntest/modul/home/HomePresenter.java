package com.example.msntest.modul.home;

import com.example.msntest.core.repo.AppRepo;
import com.example.msntest.modul.home.contract.HomePresenterContract;
import com.example.msntest.modul.home.contract.HomeViewContract;
import com.example.msntest.util.other.PostCallback;

public class HomePresenter implements HomePresenterContract {
    private final AppRepo mRepo;
    private final HomeViewContract mView;

    public HomePresenter(AppRepo repo, HomeViewContract viewContract) {
        this.mRepo = repo;
        this.mView = viewContract;
        this.mView.setPresenter(this);
    }

    @Override
    public void doSetDataUser() {
        mView.showContentLoading();
        mRepo.doSetDataUser(new PostCallback() {
            @Override
            public void onEntityPosted(Object var1) {
                mRepo.doGetAllDataUserEmotion(var11 -> {
                    mView.setHomeAdapter(var11);
                    mView.hideContentLoading();
                });
            }

            @Override
            public void onErrorRequest(Throwable var1) {
                mView.hideContentLoading();
            }
        });
    }

    @Override
    public void doGetAllDataUserEmotion() {
        mView.showContentLoading();
        mRepo.doGetAllDataUserEmotion(var11 -> {
            mView.setHomeAdapter(var11);
            mView.hideContentLoading();
        });
    }
}
