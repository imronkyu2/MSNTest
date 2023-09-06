package com.example.msntest.modul.home.subclass.modusemotion.contract;

import com.example.msntest.core.model.ModusEmotionResult;
import com.example.msntest.util.other.BaseView;

import java.util.List;

public interface ModusEmotionViewContract extends BaseView<ModusEmotionPresenterContract> {
    void setModusEmotionAdapter(List<ModusEmotionResult> var11);
}
