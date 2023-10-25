package com.example.msntest.modul.home.subclass.modusemotion;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.msntest.core.model.ModusEmotionResult;
import com.example.msntest.databinding.ActivityModusEmotionBinding;
import com.example.msntest.modul.home.subclass.modusemotion.adapter.ModusEmotionAdapter;
import com.example.msntest.modul.home.subclass.modusemotion.contract.ModusEmotionPresenterContract;
import com.example.msntest.modul.home.subclass.modusemotion.contract.ModusEmotionViewContract;
import com.example.msntest.util.injection.Injection;

import java.util.List;

public class ModusEmotionActivity extends AppCompatActivity implements ModusEmotionViewContract {
    private ActivityModusEmotionBinding binding;
    private ModusEmotionPresenterContract mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityModusEmotionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        new ModusEmotionPresenter(Injection.provideAppRepo(getApplicationContext()), this);

        binding.backButton.setOnClickListener(view -> finish());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.doGetModusEmotion();
    }

    @Override
    public void setPresenter(ModusEmotionPresenterContract var1) {
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
    public void setModusEmotionAdapter(List<ModusEmotionResult> var11) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        binding.recyclerView.setLayoutManager(layoutManager);
        ModusEmotionAdapter adapter = new ModusEmotionAdapter(var11);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.scheduleLayoutAnimation();
    }
}