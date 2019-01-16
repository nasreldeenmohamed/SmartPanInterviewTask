package com.smartpan.smartpaninterviewtask.modules.register;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.smartpan.smartpaninterviewtask.modules.register.models.RegisterResponse;
import com.smartpan.smartpaninterviewtask.modules.register.models.RegisterRequest;
import com.smartpan.smartpaninterviewtask.modules.register.models.RegisterResponse;
import com.smartpan.smartpaninterviewtask.webService.RetrofitRepository;

public class RegisterViewModel extends AndroidViewModel {

    LiveData<RegisterResponse> registerResponseLiveData = new MutableLiveData<>();
    RegisterResponse registerResponse;
    private RegisterRequest registerRequest;

    private Application application;
    private RetrofitRepository retrofitRepository;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        this.application = application;

        registerRequest = new RegisterRequest();
        retrofitRepository = RetrofitRepository.getInstance();
    }

    public void performRegister(RegisterRequest registerRequest){
        if (validateInput(registerRequest)) {

        }
    }

    private boolean validateInput(RegisterRequest registerRequest) {
//        if (registerRequest.getUsername().isEmpty()) {
//            registerResponse = new RegisterResponse("username", "Username is empty!");
//            ((MutableLiveData<RegisterResponse>) registerResponseLiveData).setValue(registerResponse);
//            return false;
//        }
//        if (registerRequest.getUsername().isEmpty()) {
//            registerResponse = new RegisterResponse("password", "Username is empty!");
//            ((MutableLiveData<RegisterResponse>) registerResponseLiveData).setValue(registerResponse);
//            return false;
//        } else if (registerRequest.getPassword().length() <= 4) {
//            registerResponse = new RegisterResponse("password", "Password is too short!");
//            ((MutableLiveData<RegisterResponse>) registerResponseLiveData).setValue(registerResponse);
//        }
        return true;
    }

}
