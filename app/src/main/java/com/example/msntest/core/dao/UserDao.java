package com.example.msntest.core.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.msntest.core.model.UserEntity;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(UserEntity user);

    @Query("SELECT * FROM users WHERE username = :username")
    UserEntity getUserByUsername(String username);

    @Query("SELECT * FROM users")
    List<UserEntity> getAllUsers();
}
