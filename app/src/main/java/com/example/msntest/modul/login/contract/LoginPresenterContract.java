package com.example.msntest.modul.login.contract;

import com.example.msntest.core.model.UserEntity;

public interface LoginPresenterContract {
    void doLogin(UserEntity user);
}
