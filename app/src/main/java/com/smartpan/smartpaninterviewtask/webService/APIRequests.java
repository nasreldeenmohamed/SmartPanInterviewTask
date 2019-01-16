package com.smartpan.smartpaninterviewtask.webService;

import com.smartpan.smartpaninterviewtask.models.Country;
import com.smartpan.smartpaninterviewtask.modules.login.models.LoginRequest;
import com.smartpan.smartpaninterviewtask.modules.login.models.LoginResponse;
import com.smartpan.smartpaninterviewtask.modules.register.models.RegisterRequest;
import com.smartpan.smartpaninterviewtask.modules.register.models.RegisterResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequests {

    @POST("Login")
    Call<LoginResponse> performLogin(@Body LoginRequest loginRequest);

    @POST("register")
    Call<RegisterResponse> registerNewUser(@Body RegisterRequest registerRequest);

    @GET("all")
    Call<List<Country>> getAllCountries();
}
