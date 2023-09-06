package com.example.msntest.util.other;

import java.util.List;

public interface GetListCallback<T> {
    void onEntityLoaded(List<T> var1);
}