package com.smartpan.smartpaninterviewtask.modules.login;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.smartpan.smartpaninterviewtask.R;
import com.smartpan.smartpaninterviewtask.databinding.ActivityLoginBinding;
import com.smartpan.smartpaninterviewtask.models.User;
import com.smartpan.smartpaninterviewtask.modules.login.models.ErrorResponse;
import com.smartpan.smartpaninterviewtask.modules.login.models.LoginResponse;
import com.smartpan.smartpaninterviewtask.modules.main.view.MainActivity;
import com.smartpan.smartpaninterviewtask.modules.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    LoginViewModel loginViewModel;
    ActivityLoginBinding binding;
    //    LoginRequest loginRequest;
    User user;

    Button login_btn;
    Button register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLifecycleOwner(this);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

//        loginRequest = new LoginRequest();
//        user = new User();
//        binding.setLoginRequest(loginRequest);
//
//        binding.buttonLoginLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loginRequest = binding.getLoginRequest();
//                loginViewModel.performLogin(loginRequest);
//            }
//        });
        login_btn = binding.buttonLoginLogin;

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progressBarLoading.setVisibility(View.VISIBLE);

//                loginRequest = new LoginRequest(binding.editTextLoginUsername.getText().toString().trim(),
//                        binding.editTextLoginPassword.getText().toString().trim());
                user = new User();
                user.setUsername(binding.editTextLoginUsername.getText().toString().trim());
                user.setPassword(binding.editTextLoginPassword.getText().toString().trim());

                loginViewModel.getErrorResponseLiveData().observe(LoginActivity.this, new Observer<ErrorResponse>() {
                    @Override
                    public void onChanged(@Nullable ErrorResponse errorResponse) {
                        binding.progressBarLoading.setVisibility(View.INVISIBLE);
                        if (errorResponse.getSuccess().equals("username")) {
                            binding.editTextLoginUsername.setError(errorResponse.getMessage());
                        } else if (errorResponse.getSuccess().equals("password")) {
                            binding.editTextLoginPassword.setError(errorResponse.getMessage());
                        }
                    }
                });

                loginViewModel.performLogin(user);
                loginViewModel.getLoginResponse().observe(LoginActivity.this, new Observer<LoginResponse>() {
                    @Override
                    public void onChanged(@Nullable LoginResponse loginResponse) {
                        binding.progressBarLoading.setVisibility(View.INVISIBLE);

                        if (loginResponse.getSuccess().equals("ok")) {
                            navigateToMainActivity();
                        } else {
                            Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        register_btn = binding.buttonLoginRegister;
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToRegisterActivity();
            }
        });
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToRegisterActivity() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }


//    public void OnClickLoginButton(LoginRequest loginRequest) {
//        loginViewModel.performLogin(loginRequest);
//    }
}
