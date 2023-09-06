package com.example.msntest.util.other;

public interface GetCallback<T> {
    void onEntityLoaded(T var1);

    void onDataNotAvailable();
}