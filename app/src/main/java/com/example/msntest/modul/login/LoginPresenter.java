package com.example.msntest.modul.login;

import com.example.msntest.core.model.UserEntity;
import com.example.msntest.core.repo.AppRepo;
import com.example.msntest.modul.login.contract.LoginPresenterContract;
import com.example.msntest.modul.login.contract.LoginViewContract;
import com.example.msntest.util.other.GetCallback;

public class LoginPresenter implements LoginPresenterContract {
    private final AppRepo mRepo;
    private final LoginViewContract mView;

    public LoginPresenter(AppRepo repo, LoginViewContract viewContract) {
        this.mRepo = repo;
        this.mView = viewContract;
        this.mView.setPresenter(this);
    }

    @Override
    public void doLogin(UserEntity user) {
        mView.showContentLoading();
        mRepo.doLogin(user, new GetCallback<UserEntity>() {
            @Override
            public void onEntityLoaded(UserEntity userEntity) {
                mView.hideContentLoading();
                mView.doLoginSuccess();
            }

            @Override
            public void onDataNotAvailable() {
                mView.hideContentLoading();
                mView.doLoginFailed();
            }
        });
    }

}
