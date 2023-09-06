package com.example.msntest.modul.register.contact;

import com.example.msntest.util.other.BaseView;

public interface RegisterViewContract extends BaseView<RegisterPresenterContract> {
    void doResigterSuccess();

    void doResigterFailed(String string);
}
