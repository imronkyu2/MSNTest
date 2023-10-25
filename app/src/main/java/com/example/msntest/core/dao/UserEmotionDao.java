package com.example.msntest.core.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.msntest.core.model.AverageScoreResult;
import com.example.msntest.core.model.AvgScoreAndModusEmotionResult;
import com.example.msntest.core.model.ModusEmotionResult;
import com.example.msntest.core.model.UserEmotion;

import java.util.List;

@Dao
public interface UserEmotionDao {

    @Insert
    void insert(UserEmotion userEmotion);

    @Query("SELECT * FROM UserEmotion")
    List<UserEmotion> getAllUserEmotions();

    @Query("SELECT * FROM UserEmotion WHERE name = :name")
    List<UserEmotion> getDataByName(String name);

    @Query("SELECT name, AVG(score) AS averageScore FROM UserEmotion GROUP BY name")
    List<AverageScoreResult> getAverageScore();

    @Query("SELECT name, emotion, COUNT(emotion) AS frequency " +
            "FROM UserEmotion " +
            "GROUP BY name, emotion " +
            "HAVING frequency = (SELECT MAX(freq) " +
            "FROM (SELECT COUNT(emotion) AS freq " +
            "FROM UserEmotion " +
            "GROUP BY name, emotion))")
    List<ModusEmotionResult> getModusEmotion();

    @Query("SELECT name, created, AVG(score) AS averageScore, " +
            "(SELECT emotion FROM UserEmotion AS u2 " +
            " WHERE u2.name = u1.name AND u2.created = u1.created " +
            " GROUP BY emotion " +
            " ORDER BY COUNT(*) DESC " +
            " LIMIT 1) AS modusEmotion " +
            " FROM UserEmotion AS u1 " +
            " GROUP BY name, created")
    List<AvgScoreAndModusEmotionResult> getAvgScoreAndModusEmotion();



}
