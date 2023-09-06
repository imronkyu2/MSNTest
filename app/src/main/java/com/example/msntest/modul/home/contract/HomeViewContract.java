package com.example.msntest.modul.home.contract;

import com.example.msntest.core.model.UserEmotion;
import com.example.msntest.util.other.BaseView;

import java.util.List;

public interface HomeViewContract extends BaseView<HomePresenterContract> {
    void setHomeAdapter(List<UserEmotion> var1);
}
