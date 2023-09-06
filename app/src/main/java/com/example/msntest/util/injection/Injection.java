package com.example.msntest.util.injection;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.msntest.core.base.AppDatabase;
import com.example.msntest.core.base.AppExecutors;
import com.example.msntest.core.repo.AppRepo;
import com.example.msntest.core.repo.AppRepoImpl;

public class Injection {
    public static AppRepo provideAppRepo(@NonNull Context mContext) {
        AppExecutors appExecutors = new AppExecutors();
        return new AppRepoImpl(mContext, appExecutors, AppDatabase.getInstance(mContext).userDao(),
                AppDatabase.getInstance(mContext).userEmotionDao());
    }

}
