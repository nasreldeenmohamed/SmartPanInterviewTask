package com.smartpan.smartpaninterviewtask.modules.register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.smartpan.smartpaninterviewtask.R;
import com.smartpan.smartpaninterviewtask.modules.register.models.RegisterRequest;

public class RegisterActivity extends AppCompatActivity {

//    ActivityRegisterBinding binding;
    RegisterViewModel registerViewModel;
    RegisterRequest registerRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
//        binding.setLifecycleOwner(this);
//
//        registerRequest = new RegisterRequest();
//        binding.setRegisterRequest(registerRequest);

//        registerViewModel = new ViewModelProviders.of(this).get(RegisterViewModel.class);
//        registerViewModel.getRegisterRespons


    }
}
