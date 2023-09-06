package com.example.msntest.modul.home.subclass.averagescore;

import com.example.msntest.core.repo.AppRepo;
import com.example.msntest.modul.home.subclass.averagescore.contract.AverageScorePresenterContract;
import com.example.msntest.modul.home.subclass.averagescore.contract.AverageScoreViewContract;

public class AverageScorePresenter implements AverageScorePresenterContract {
    private final AppRepo mRepo;
    private final AverageScoreViewContract mView;

    public AverageScorePresenter(AppRepo repo, AverageScoreViewContract viewContract) {
        this.mRepo = repo;
        this.mView = viewContract;
        this.mView.setPresenter(this);
    }

    @Override
    public void doGetAverageScore() {
        mView.showContentLoading();
        mRepo.doGetAverageScore(var11 -> {
            mView.setAverageScoreAdapter(var11);
            mView.hideContentLoading();
        });
    }
}
