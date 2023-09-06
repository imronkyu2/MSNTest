package com.example.msntest.modul.home.subclass.modusemotion;

import com.example.msntest.core.repo.AppRepo;
import com.example.msntest.modul.home.subclass.modusemotion.contract.ModusEmotionPresenterContract;
import com.example.msntest.modul.home.subclass.modusemotion.contract.ModusEmotionViewContract;

public class ModusEmotionPresenter implements ModusEmotionPresenterContract {
    private final AppRepo mRepo;
    private final ModusEmotionViewContract mView;

    public ModusEmotionPresenter(AppRepo repo, ModusEmotionViewContract viewContract) {
        this.mRepo = repo;
        this.mView = viewContract;
        this.mView.setPresenter(this);
    }

    @Override
    public void doGetModusEmotion() {
        mView.showContentLoading();
        mRepo.doGetModusEmotion(var11 -> {
            mView.setModusEmotionAdapter(var11);
            mView.hideContentLoading();
        });
    }
}
