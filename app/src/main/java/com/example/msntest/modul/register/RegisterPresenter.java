package com.example.msntest.modul.register;

import com.example.msntest.core.model.UserEntity;
import com.example.msntest.core.repo.AppRepo;
import com.example.msntest.modul.register.contact.RegisterPresenterContract;
import com.example.msntest.modul.register.contact.RegisterViewContract;
import com.example.msntest.util.other.PostCallback;

public class RegisterPresenter implements RegisterPresenterContract {
    private final AppRepo mRepo;
    private final RegisterViewContract mView;

    public RegisterPresenter(AppRepo repo, RegisterViewContract viewContract) {
        this.mRepo = repo;
        this.mView = viewContract;
        this.mView.setPresenter(this);
    }

    @Override
    public void doRegister(UserEntity user) {
        mView.showContentLoading();
        mRepo.doRegister(user, new PostCallback() {
            @Override
            public void onEntityPosted(Object var1) {
                mView.doResigterSuccess();
                mView.hideContentLoading();
            }

            @Override
            public void onErrorRequest(Throwable var1) {
                String string = var1.getMessage();
                mView.doResigterFailed(string);
                mView.hideContentLoading();
            }
        });
    }
}
