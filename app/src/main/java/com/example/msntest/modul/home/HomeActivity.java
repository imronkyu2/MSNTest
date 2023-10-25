package com.example.msntest.modul.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.msntest.core.model.UserEmotion;
import com.example.msntest.databinding.ActivityHomeBinding;
import com.example.msntest.modul.home.adapter.HomeAdapter;
import com.example.msntest.modul.home.contract.HomePresenterContract;
import com.example.msntest.modul.home.contract.HomeViewContract;
import com.example.msntest.modul.home.subclass.averagescore.AverageScoreActivity;
import com.example.msntest.modul.home.subclass.averagescoreandmodusemotion.AverageScoreAndModusEmotionActivity;
import com.example.msntest.modul.home.subclass.modusemotion.ModusEmotionActivity;
import com.example.msntest.modul.login.LoginActivity;
import com.example.msntest.util.SharedPreferencesManager;
import com.example.msntest.util.injection.Injection;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeViewContract {
    private static final String PREF_NAME = "MyPrefs";
    private static final String KEY_DATA_INSERTED = "isDataInserted";
    private HomePresenterContract mPresenter;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new HomePresenter(Injection.provideAppRepo(getApplicationContext()), this);

        actionButton();
    }

    private void actionButton() {
        binding.buttonAverageScore.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, AverageScoreActivity.class);
            startActivity(intent);
        });

        binding.buttonModusEmotion.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, ModusEmotionActivity.class);
            startActivity(intent);
        });

        binding.buttonAverageScoreAndModusEmotion.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, AverageScoreAndModusEmotionActivity.class);
            startActivity(intent);
        });

        binding.logout.setOnClickListener(view -> {
            final SharedPreferencesManager preferencesManager = SharedPreferencesManager.getInstance(this);
            preferencesManager.clearLoginStatus();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            finish();
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        boolean isDataInserted = sharedPreferences.getBoolean(KEY_DATA_INSERTED, false);
        if (!isDataInserted) {
            mPresenter.doSetDataUser();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(KEY_DATA_INSERTED, true);
            editor.apply();
        } else {
            mPresenter.doGetAllDataUserEmotion();
        }
    }

    @Override
    public void setPresenter(HomePresenterContract var1) {
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
    public void setHomeAdapter(List<UserEmotion> var1) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        binding.recyclerView.setLayoutManager(layoutManager);
        HomeAdapter adapter = new HomeAdapter(var1);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void onBackPressed() {
    }
}