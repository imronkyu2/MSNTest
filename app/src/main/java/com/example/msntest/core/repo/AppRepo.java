package com.example.msntest.core.repo;

import com.example.msntest.core.model.AverageScoreResult;
import com.example.msntest.core.model.AvgScoreAndModusEmotionResult;
import com.example.msntest.core.model.ModusEmotionResult;
import com.example.msntest.core.model.UserEmotion;
import com.example.msntest.core.model.UserEntity;
import com.example.msntest.util.other.GetCallback;
import com.example.msntest.util.other.GetListCallback;
import com.example.msntest.util.other.PostCallback;

public interface AppRepo {
    void doRegister(UserEntity user, PostCallback callback);

    void doLogin(UserEntity user, GetCallback<UserEntity> callback);

    void doGetAllDataRegister(GetListCallback<UserEntity> callback);

    void doSetDataUser(PostCallback callback);

    void doGetAllDataUserEmotion(GetListCallback<UserEmotion> callback);

    void doGetAverageScore(GetListCallback<AverageScoreResult> callback);

    void doGetModusEmotion(GetListCallback<ModusEmotionResult> callback);

    void doGetAvgScoreAndModusEmotion(GetListCallback<AvgScoreAndModusEmotionResult> callback);
}
