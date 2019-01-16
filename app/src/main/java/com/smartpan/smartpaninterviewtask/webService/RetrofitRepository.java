package com.smartpan.smartpaninterviewtask.webService;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smartpan.smartpaninterviewtask.models.Country;
import com.smartpan.smartpaninterviewtask.modules.login.models.LoginRequest;
import com.smartpan.smartpaninterviewtask.modules.login.models.LoginResponse;
import com.smartpan.smartpaninterviewtask.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRepository {

    private APIRequests APIRequests;
    private static RetrofitRepository retrofitRepository;

    private RetrofitRepository() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIRequests = retrofit.create(APIRequests.class);
    }

    public synchronized static RetrofitRepository getInstance() {

        if (retrofitRepository == null) {
            retrofitRepository = new RetrofitRepository();
        }

        return retrofitRepository;
    }

    public LiveData<LoginResponse> loginUser(LoginRequest loginRequest) {
        final LiveData<LoginResponse> loginResponseLiveData = new MutableLiveData<>();

        APIRequests.performLogin(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (response.isSuccessful()) {
                    ((MutableLiveData<LoginResponse>) loginResponseLiveData).setValue(loginResponse);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                ((MutableLiveData<LoginResponse>) loginResponseLiveData).setValue(new LoginResponse(t.getMessage()));
            }
        });

        return loginResponseLiveData;
    }

}
