package com.smartpan.smartpaninterviewtask.modules.login;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.smartpan.smartpaninterviewtask.models.User;
import com.smartpan.smartpaninterviewtask.modules.login.models.ErrorResponse;
import com.smartpan.smartpaninterviewtask.modules.login.models.LoginResponse;
import com.smartpan.smartpaninterviewtask.repositories.UserRepository;

public class LoginViewModel extends AndroidViewModel {

    //    LoginRequest loginRequest;
    private LiveData<LoginResponse> loginResponseLiveData = new MutableLiveData<>();
    private LiveData<ErrorResponse> errorResponseLiveData = new MutableLiveData<>();

    private Application application;
    private UserRepository userRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        this.application = application;
        userRepository = new UserRepository(application);
    }

    void performLogin(User user) {
        Log.i("username", user.getUsername());
        Log.i("password", user.getPassword());
        if (validateInput(user)) {

            loginResponseLiveData = userRepository.loginUser(user);
            if (loginResponseLiveData.getValue() != null) {
                if (loginResponseLiveData.getValue().getSuccess().equals("ok")) {
                    user.setID(String.valueOf(loginResponseLiveData.getValue().getId()));
                    user.setImageURL(loginResponseLiveData.getValue().getImage());
                    user.setUsername(loginResponseLiveData.getValue().getUserName());
                    userRepository.saveUserDataOffline(user);
                } else {
                    Toast.makeText(application, loginResponseLiveData.getValue().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    LiveData<LoginResponse> getLoginResponse() {
        return loginResponseLiveData;
    }

    LiveData<ErrorResponse> getErrorResponseLiveData() {
        return errorResponseLiveData;
    }

    private boolean validateInput(User user) {
        ErrorResponse errorResponse;
        if (user.getUsername().isEmpty()) {
            errorResponse = new ErrorResponse("username", "Username is empty!");
            ((MutableLiveData<ErrorResponse>) errorResponseLiveData).setValue(errorResponse);
            return false;
        }
        if (user.getPassword().isEmpty()) {
            errorResponse = new ErrorResponse("password", "Password is empty!");
            ((MutableLiveData<ErrorResponse>) errorResponseLiveData).setValue(errorResponse);
            return false;
        }
        if (user.getPassword().length() <= 4) {
            errorResponse = new ErrorResponse("password", "Password is too short!");
            ((MutableLiveData<ErrorResponse>) errorResponseLiveData).setValue(errorResponse);
            return false;
        }
        return true;
    }

}
