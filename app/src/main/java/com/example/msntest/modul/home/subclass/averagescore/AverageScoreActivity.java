package com.example.msntest.modul.home.subclass.averagescore;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.msntest.core.model.AverageScoreResult;
import com.example.msntest.databinding.ActivityAverageScoreBinding;
import com.example.msntest.modul.home.subclass.averagescore.adapter.AverageScoreAdapter;
import com.example.msntest.modul.home.subclass.averagescore.contract.AverageScorePresenterContract;
import com.example.msntest.modul.home.subclass.averagescore.contract.AverageScoreViewContract;
import com.example.msntest.util.injection.Injection;

import java.util.List;

public class AverageScoreActivity extends AppCompatActivity implements AverageScoreViewContract {
    private ActivityAverageScoreBinding binding;
    private AverageScorePresenterContract mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAverageScoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new AverageScorePresenter(Injection.provideAppRepo(getApplicationContext()), this);

        binding.backButton.setOnClickListener(view -> finish());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.doGetAverageScore();
    }

    @Override
    public void setPresenter(AverageScorePresenterContract var1) {
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
    public void setAverageScoreAdapter(List<AverageScoreResult> var11) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        binding.recyclerView.setLayoutManager(layoutManager);
        AverageScoreAdapter adapter = new AverageScoreAdapter(var11);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.scheduleLayoutAnimation();
    }
}