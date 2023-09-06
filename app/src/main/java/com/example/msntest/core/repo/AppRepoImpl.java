package com.example.msntest.core.repo;

import android.content.Context;

import com.example.msntest.core.base.AppExecutors;
import com.example.msntest.core.dao.UserDao;
import com.example.msntest.core.dao.UserEmotionDao;
import com.example.msntest.core.model.AverageScoreResult;
import com.example.msntest.core.model.AvgScoreAndModusEmotionResult;
import com.example.msntest.core.model.ModusEmotionResult;
import com.example.msntest.core.model.UserEmotion;
import com.example.msntest.core.model.UserEntity;
import com.example.msntest.util.SharedPreferencesManager;
import com.example.msntest.util.encrypsha256.PasswordEncryption;
import com.example.msntest.util.other.GetCallback;
import com.example.msntest.util.other.GetListCallback;
import com.example.msntest.util.other.PostCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AppRepoImpl implements AppRepo {
    private final Context mContext;
    private final AppExecutors mAppExecutors;
    private final UserDao mUserDao;
    private final UserEmotionDao mUserEmotionDao;

    public AppRepoImpl(Context context, AppExecutors appExecutors, UserDao userDao, UserEmotionDao userEmotionDao) {
        this.mContext = context;
        this.mAppExecutors = appExecutors;
        this.mUserDao = userDao;
        this.mUserEmotionDao = userEmotionDao;

    }

    @Override
    public void doRegister(UserEntity user, PostCallback callback) {
        mAppExecutors.diskIO().execute(() -> {
            UserEntity existingUser = mUserDao.getUserByUsername(user.getUsername());
            if (existingUser != null) {
                mAppExecutors.mainThread().execute(() -> {
                    if (callback != null) {
                        callback.onErrorRequest(new Exception("Failed, Username/email sudah ada."));
                    }
                });
            } else {
                byte[] salt = PasswordEncryption.generateSalt();
                String encryptedPassword = PasswordEncryption.encryptPassword(user.getPassword(), salt);
                user.setPassword(encryptedPassword);
                user.setSalt(salt);

                mUserDao.insert(user);

                mAppExecutors.mainThread().execute(() -> {
                    if (callback != null) {
                        callback.onEntityPosted("Registration successful");
                    }
                });
            }
        });
    }

    @Override
    public void doLogin(UserEntity user, GetCallback<UserEntity> callback) {
        mAppExecutors.diskIO().execute(() -> {
            UserEntity storedUser = mUserDao.getUserByUsername(user.getUsername());
            if (storedUser != null) {
                byte[] salt = storedUser.getSalt();
                String enteredPassword = PasswordEncryption.encryptPassword(user.getPassword(), salt);
                if (Objects.equals(enteredPassword, storedUser.getPassword())) {
                    SharedPreferencesManager preferencesManager = SharedPreferencesManager.getInstance(mContext);
                    preferencesManager.saveLoginSuccess(true);
                    mAppExecutors.mainThread().execute(() -> callback.onEntityLoaded(storedUser));
                } else {
                    mAppExecutors.mainThread().execute(callback::onDataNotAvailable);
                }
            } else {
                mAppExecutors.mainThread().execute(callback::onDataNotAvailable);
            }
        });
    }

    @Override
    public void doGetAllDataRegister(GetListCallback<UserEntity> callback) {
        mAppExecutors.diskIO().execute(() -> {
            List<UserEntity> userList = mUserDao.getAllUsers();
            mAppExecutors.mainThread().execute(() -> {
                if (callback != null) {
                    callback.onEntityLoaded(userList);
                }
            });
        });
    }

    @Override
    public void doSetDataUser(PostCallback callback) {
        mAppExecutors.diskIO().execute(() -> {
            List<UserEmotion> userEmotionList = new ArrayList<>();
            userEmotionList.add(new UserEmotion("Kevin", 80, "Happy", "2020-02-20"));
            userEmotionList.add(new UserEmotion("Josh", 90, "Sad", "2020-02-20"));
            userEmotionList.add(new UserEmotion("Kevin", 85, "Happy", "2020-02-20"));
            userEmotionList.add(new UserEmotion("Kevin", 75, "Sad", "2020-02-20"));
            userEmotionList.add(new UserEmotion("Josh", 65, "Angry", "2020-02-20"));
            userEmotionList.add(new UserEmotion("David", 85, "Happy", "2020-02-21"));
            userEmotionList.add(new UserEmotion("Josh", 90, "Sad", "2020-02-21"));
            userEmotionList.add(new UserEmotion("David", 75, "Sad", "2020-02-21"));
            userEmotionList.add(new UserEmotion("Josh", 85, "Sad", "2020-02-21"));
            userEmotionList.add(new UserEmotion("Josh", 70, "Happy", "2020-02-21"));
            userEmotionList.add(new UserEmotion("Kevin", 80, "Sad", "2020-02-21"));
            userEmotionList.add(new UserEmotion("Kevin", 73, "Sad", "2020-02-22"));
            userEmotionList.add(new UserEmotion("Kevin", 75, "Angry", "2020-02-22"));
            userEmotionList.add(new UserEmotion("David", 82, "Sad", "2020-02-22"));
            userEmotionList.add(new UserEmotion("David", 65, "Sad", "2020-02-22"));

            for (UserEmotion userEmotion : userEmotionList) {
                mUserEmotionDao.insert(userEmotion);
            }
            mAppExecutors.mainThread().execute(() -> {
                if (callback != null) {
                    callback.onEntityPosted("Registration successful");
                }
            });
        });

    }

    @Override
    public void doGetAllDataUserEmotion(GetListCallback<UserEmotion> callback) {
        mAppExecutors.diskIO().execute(() -> {
            List<UserEmotion> userList = mUserEmotionDao.getAllUserEmotions();
            mAppExecutors.mainThread().execute(() -> {
                if (callback != null) {
                    callback.onEntityLoaded(userList);
                }
            });
        });
    }

    @Override
    public void doGetAverageScore(GetListCallback<AverageScoreResult> callback) {
        mAppExecutors.diskIO().execute(() -> {
            List<AverageScoreResult> userList = mUserEmotionDao.getAverageScore();
            mAppExecutors.mainThread().execute(() -> {
                if (callback != null) {
                    callback.onEntityLoaded(userList);
                }
            });
        });
    }

    @Override
    public void doGetModusEmotion(GetListCallback<ModusEmotionResult> callback) {
        mAppExecutors.diskIO().execute(() -> {
            List<ModusEmotionResult> userList = mUserEmotionDao.getModusEmotion();
            mAppExecutors.mainThread().execute(() -> {
                if (callback != null) {
                    callback.onEntityLoaded(userList);
                }
            });
        });
    }

    @Override
    public void doGetAvgScoreAndModusEmotion(GetListCallback<AvgScoreAndModusEmotionResult> callback) {
        mAppExecutors.diskIO().execute(() -> {
            List<AvgScoreAndModusEmotionResult> userList = mUserEmotionDao.getAvgScoreAndModusEmotion();
            mAppExecutors.mainThread().execute(() -> {
                if (callback != null) {
                    callback.onEntityLoaded(userList);
                }
            });
        });
    }


}
