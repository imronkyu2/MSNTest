package com.example.msntest.modul.viewdata;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.msntest.core.model.UserEntity;
import com.example.msntest.databinding.ActivityViewDataUserRegisteredBinding;
import com.example.msntest.modul.viewdata.adapter.ViewDataUserRegisteredAdapter;
import com.example.msntest.modul.viewdata.contract.ViewDataUserRegisteredPresenterContract;
import com.example.msntest.modul.viewdata.contract.ViewDataUserRegisteredViewContract;
import com.example.msntest.util.injection.Injection;

import java.util.List;

public class ViewDataUserRegisteredActivity extends AppCompatActivity implements ViewDataUserRegisteredViewContract {
    private ActivityViewDataUserRegisteredBinding binding;
    private ViewDataUserRegisteredPresenterContract mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewDataUserRegisteredBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new ViewDataUserRegisteredPresenter(Injection.provideAppRepo(getApplicationContext()), this);

        binding.backButton.setOnClickListener(view -> finish());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.doGetAllDataRegister();
    }

    @Override
    public void setPresenter(ViewDataUserRegisteredPresenterContract var1) {
        this.mPresenter = var1;
    }

    @Override
    public void showContentLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideContentLoading() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void doGetAllDataRegisterSuccess(List<UserEntity> var1) {
        binding.dataNotFound.setVisibility(View.GONE);
        binding.recyclerView.setVisibility(View.VISIBLE);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.recyclerView.setLayoutManager(layoutManager);
        ViewDataUserRegisteredAdapter adapter = new ViewDataUserRegisteredAdapter(var1);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void doGetAllDataRegisterFailed() {
        binding.dataNotFound.setVisibility(View.VISIBLE);
        binding.recyclerView.setVisibility(View.GONE);
    }
}