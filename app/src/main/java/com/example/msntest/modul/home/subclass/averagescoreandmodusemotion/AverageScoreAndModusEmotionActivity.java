package com.example.msntest.modul.home.subclass.averagescoreandmodusemotion;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.msntest.R;
import com.example.msntest.core.model.AvgScoreAndModusEmotionResult;
import com.example.msntest.databinding.ActivityAverageScoreAndModusEmotionBinding;
import com.example.msntest.modul.home.subclass.averagescore.adapter.AverageScoreAdapter;
import com.example.msntest.modul.home.subclass.averagescoreandmodusemotion.adapter.AverageScoreAndModusEmotionAdapter;
import com.example.msntest.modul.home.subclass.averagescoreandmodusemotion.contract.AverageScoreAndModusEmotionPresenterContract;
import com.example.msntest.modul.home.subclass.averagescoreandmodusemotion.contract.AverageScoreAndModusEmotionViewContract;
import com.example.msntest.util.injection.Injection;

import java.util.List;

public class AverageScoreAndModusEmotionActivity extends AppCompatActivity implements AverageScoreAndModusEmotionViewContract {
    private ActivityAverageScoreAndModusEmotionBinding binding;
    private AverageScoreAndModusEmotionPresenterContract mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAverageScoreAndModusEmotionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new AverageScoreAndModusEmotionPresenter(Injection.provideAppRepo(getApplicationContext()), this);

        binding.backButton.setOnClickListener(view -> finish());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.doGetAvgScoreAndModusEmotion();
    }

    @Override
    public void setPresenter(AverageScoreAndModusEmotionPresenterContract var1) {
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
    public void setAvgScoreAndModusEmotionAdapter(List<AvgScoreAndModusEmotionResult> var11) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        binding.recyclerView.setLayoutManager(layoutManager);
        AverageScoreAndModusEmotionAdapter adapter = new AverageScoreAndModusEmotionAdapter(var11);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.scheduleLayoutAnimation();
    }
}