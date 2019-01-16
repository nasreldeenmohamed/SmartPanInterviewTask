package com.smartpan.smartpaninterviewtask.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.smartpan.smartpaninterviewtask.R;
import com.smartpan.smartpaninterviewtask.models.User;
import com.smartpan.smartpaninterviewtask.modules.login.models.LoginRequest;
import com.smartpan.smartpaninterviewtask.modules.login.models.LoginResponse;
import com.smartpan.smartpaninterviewtask.utils.ConnectionDetector;
import com.smartpan.smartpaninterviewtask.utils.SharedPref;
import com.smartpan.smartpaninterviewtask.webService.RetrofitRepository;

public class UserRepository {
    SharedPref sharedPref;
    Gson gson;
    RetrofitRepository retrofitRepository;
    Application application;

    public UserRepository(Application application) {
        this.application = application;
        gson = new Gson();
        sharedPref = new SharedPref(application);
        retrofitRepository = RetrofitRepository.getInstance();
    }

    public void saveUserDataOffline(User user) {
        sharedPref.setUserInfo(gson.toJson(user));
        Log.e("userData", "saved");
    }

    public User loadUserData() {
        User user = new User();

        return user;
    }

    public LiveData<LoginResponse> loginUser(User user) {
        if (ConnectionDetector.isConnectingToInternet(application.getApplicationContext())) {
            LoginRequest loginRequest = new LoginRequest(user.getUsername(), user.getPassword());
            return retrofitRepository.loginUser(loginRequest);
        } else {
            Toast.makeText(application.getApplicationContext(), application.getApplicationContext()
                    .getString(R.string.no_internet_con), Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
