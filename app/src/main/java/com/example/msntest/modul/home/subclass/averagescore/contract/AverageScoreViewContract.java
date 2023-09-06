package com.example.msntest.modul.home.subclass.averagescore.contract;

import com.example.msntest.core.model.AverageScoreResult;
import com.example.msntest.util.other.BaseView;

import java.util.List;

public interface AverageScoreViewContract extends BaseView<AverageScorePresenterContract> {
    void setAverageScoreAdapter(List<AverageScoreResult> var11);
}
