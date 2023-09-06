package com.example.msntest.modul.login.contract;

import com.example.msntest.util.other.BaseView;

public interface LoginViewContract extends BaseView<LoginPresenterContract> {
    void doLoginSuccess();

    void doLoginFailed();
}
