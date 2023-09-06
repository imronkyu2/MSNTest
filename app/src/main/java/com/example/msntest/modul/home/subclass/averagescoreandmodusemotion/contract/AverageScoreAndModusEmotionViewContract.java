package com.example.msntest.modul.home.subclass.averagescoreandmodusemotion.contract;

import com.example.msntest.core.model.AvgScoreAndModusEmotionResult;
import com.example.msntest.util.other.BaseView;

import java.util.List;

public interface AverageScoreAndModusEmotionViewContract extends BaseView<AverageScoreAndModusEmotionPresenterContract> {
    void setAvgScoreAndModusEmotionAdapter(List<AvgScoreAndModusEmotionResult> var11);
}
