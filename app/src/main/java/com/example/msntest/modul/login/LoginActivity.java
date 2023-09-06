package com.example.msntest.modul.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.msntest.R;
import com.example.msntest.core.model.UserEntity;
import com.example.msntest.databinding.ActivityLoginBinding;
import com.example.msntest.modul.home.HomeActivity;
import com.example.msntest.modul.login.contract.LoginPresenterContract;
import com.example.msntest.modul.login.contract.LoginViewContract;
import com.example.msntest.modul.register.RegisterActivity;
import com.example.msntest.modul.viewdata.ViewDataUserRegisteredActivity;
import com.example.msntest.util.injection.Injection;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity implements LoginViewContract {
    private ActivityLoginBinding binding;
    public LoginPresenterContract mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new LoginPresenter(Injection.provideAppRepo(getApplicationContext()), this);

        actionButton();
    }

    private void actionButton() {
        binding.buttonLogin.setOnClickListener(view -> {
            UserEntity user = new UserEntity();
            user.setUsername(binding.userName.getText().toString());
            user.setPassword(binding.password.getText().toString());

            mPresenter.doLogin(user);
        });

        binding.buttonRegister.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        binding.viewData.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, ViewDataUserRegisteredActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void setPresenter(LoginPresenterContract var1) {
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
    public void doLoginSuccess() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void doLoginFailed() {
        Snackbar changeName = Snackbar.make(binding.getRoot(), getString(R.string.loginFailed), BaseTransientBottomBar.LENGTH_LONG);
        changeName.setAction(getString(R.string.ok), view1 -> changeName.dismiss());
        changeName.show();
    }
}