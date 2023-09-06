package com.example.msntest.modul.register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.msntest.R;
import com.example.msntest.core.model.UserEntity;
import com.example.msntest.databinding.ActivityRegisterBinding;
import com.example.msntest.modul.login.LoginActivity;
import com.example.msntest.modul.register.contact.RegisterPresenterContract;
import com.example.msntest.modul.register.contact.RegisterViewContract;
import com.example.msntest.util.UtilsRegex;
import com.example.msntest.util.injection.Injection;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity implements RegisterViewContract {
    private ActivityRegisterBinding binding;
    private RegisterPresenterContract mPresenter;
    private boolean userName = false;
    private boolean password = false;
    private boolean confirmPassword = false;
    private boolean age = false;
    private boolean dataComplete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new RegisterPresenter(Injection.provideAppRepo(getApplicationContext()), this);

        setUIActivity();
        actionButton();
    }


    private void setUIActivity() {
        binding.userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Empty Method
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable edt) {
                String result = edt.toString();
                userName = UtilsRegex.validateUserName(result);
                if (userName && !edt.toString().isEmpty()) {
                    binding.errorUsername.setVisibility(View.GONE);
                } else if (edt.toString().isEmpty()) {
                    userName = false;
                    binding.errorUsername.setVisibility(View.VISIBLE);
                } else {
                    userName = false;
                    binding.errorUsername.setVisibility(View.VISIBLE);
                }
                checkComplete();
            }
        });

        binding.password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Empty Method
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable edt) {
                String result = edt.toString();
                password = UtilsRegex.validatePassword(result);
                if (password && !edt.toString().isEmpty()) {
                    binding.errorPassword.setVisibility(View.GONE);
                } else if (edt.toString().isEmpty()) {
                    password = false;
                    binding.errorPassword.setVisibility(View.VISIBLE);
                } else {
                    password = false;
                    binding.errorPassword.setVisibility(View.VISIBLE);
                }
                checkComplete();
            }
        });

        binding.confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Empty Method
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable edt) {
                if (edt.toString().equals(binding.password.getText().toString()) && !edt.toString().isEmpty()) {
                    confirmPassword = true;
                    binding.errorConfirmPassword.setVisibility(View.GONE);
                } else if (edt.toString().isEmpty()) {
                    confirmPassword = false;
                    binding.errorConfirmPassword.setVisibility(View.VISIBLE);
                } else {
                    confirmPassword = false;
                    binding.errorConfirmPassword.setVisibility(View.VISIBLE);
                }
                checkComplete();
            }
        });

        binding.age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Empty Method
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable edt) {
                String result = edt.toString();
                int value = 0;
                if (!edt.toString().isEmpty()){
                    value = Integer.parseInt(result);
                }
                if (value >= 18 && !edt.toString().isEmpty()) {
                    age = true;
                    binding.errorAge.setVisibility(View.GONE);
                } else if (edt.toString().isEmpty()) {
                    age = false;
                    binding.errorAge.setVisibility(View.VISIBLE);
                } else {
                    age = false;
                    binding.errorAge.setVisibility(View.VISIBLE);
                }
                checkComplete();
            }
        });
    }

    private void checkComplete() {
        dataComplete = userName && password && confirmPassword && age;
    }

    private void actionButton() {
        binding.buttonRegister.setOnClickListener(view -> {
            if (dataComplete) {
                UserEntity user = new UserEntity();
                user.setUsername(binding.userName.getText().toString());
                user.setPassword(binding.password.getText().toString());
                user.setAge(Integer.parseInt(binding.age.getText().toString()));
                mPresenter.doRegister(user);
            } else {
                if (!userName) {
                    binding.errorUsername.setVisibility(View.VISIBLE);
                }
                if (!password) {
                    binding.errorPassword.setVisibility(View.VISIBLE);
                }
                if (!confirmPassword) {
                    binding.errorConfirmPassword.setVisibility(View.VISIBLE);
                }
                if (!age) {
                    binding.errorAge.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.backButton.setOnClickListener(view -> finish());
    }

    @Override
    public void setPresenter(RegisterPresenterContract presenter) {
        this.mPresenter = presenter;
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
    public void doResigterSuccess() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void doResigterFailed(String string) {
        Snackbar changeName = Snackbar.make(binding.getRoot(), string , BaseTransientBottomBar.LENGTH_LONG);
        changeName.setAction(getString(R.string.ok), view1 -> changeName.dismiss());
        changeName.show();
    }

}