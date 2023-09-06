package com.example.msntest.modul.viewdata.contract;

import com.example.msntest.core.model.UserEntity;
import com.example.msntest.util.other.BaseView;

import java.util.List;

public interface ViewDataUserRegisteredViewContract extends BaseView<ViewDataUserRegisteredPresenterContract> {
    void doGetAllDataRegisterSuccess(List<UserEntity> var1);

    void doGetAllDataRegisterFailed();
}
