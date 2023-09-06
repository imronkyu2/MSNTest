package com.example.msntest.util.other;

public interface BaseView<T> {
    void setPresenter(T var1);

    void showContentLoading();

    void hideContentLoading();
}