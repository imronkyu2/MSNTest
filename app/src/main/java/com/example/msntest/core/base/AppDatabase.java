package com.example.msntest.core.base;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.msntest.core.dao.UserDao;
import com.example.msntest.core.dao.UserEmotionDao;
import com.example.msntest.core.model.UserEmotion;
import com.example.msntest.core.model.UserEntity;

@Database(entities = {UserEntity.class, UserEmotion.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract UserEmotionDao userEmotionDao();

    private static AppDatabase instance;

    public static final String DATABASE_NAME = "AppDatabase.gro";


    public static void destroyInstance() {
        instance = null;
    }

    public static synchronized AppDatabase getInstance(Context mContext) {
        if (instance == null) {
            instance = provideInstance(mContext);
        }

        return instance;
    }

    private static synchronized AppDatabase provideInstance(Context mContext) {
        AppDatabase mDatabase;
        mDatabase = Room.databaseBuilder(mContext, AppDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .enableMultiInstanceInvalidation()
                .build();

        return mDatabase;
    }
}
