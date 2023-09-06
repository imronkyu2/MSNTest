package com.example.msntest.modul.home.subclass.averagescoreandmodusemotion;

import com.example.msntest.core.repo.AppRepo;
import com.example.msntest.modul.home.subclass.averagescore.contract.AverageScoreViewContract;
import com.example.msntest.modul.home.subclass.averagescoreandmodusemotion.contract.AverageScoreAndModusEmotionPresenterContract;
import com.example.msntest.modul.home.subclass.averagescoreandmodusemotion.contract.AverageScoreAndModusEmotionViewContract;

public class AverageScoreAndModusEmotionPresenter implements AverageScoreAndModusEmotionPresenterContract {
    private final AppRepo mRepo;
    private final AverageScoreAndModusEmotionViewContract mView;

    public AverageScoreAndModusEmotionPresenter(AppRepo repo, AverageScoreAndModusEmotionViewContract viewContract) {
        this.mRepo = repo;
        this.mView = viewContract;
        this.mView.setPresenter(this);
    }

    @Override
    public void doGetAvgScoreAndModusEmotion() {
        mView.showContentLoading();
        mRepo.doGetAvgScoreAndModusEmotion(var11 -> {
            mView.setAvgScoreAndModusEmotionAdapter(var11);
            mView.hideContentLoading();
        });
    }
}
