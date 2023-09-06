package com.example.msntest.modul.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.msntest.R;
import com.example.msntest.modul.home.HomeActivity;
import com.example.msntest.modul.login.LoginActivity;
import com.example.msntest.util.SharedPreferencesManager;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    private static final long SPLASH_DELAY = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final SharedPreferencesManager preferencesManager = SharedPreferencesManager.getInstance(this);

        boolean isLoginSuccess = preferencesManager.isLoginSuccess();
        new Handler().postDelayed(() -> {
            if (!isLoginSuccess) {
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            } else {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            }
        }, SPLASH_DELAY);
    }
}