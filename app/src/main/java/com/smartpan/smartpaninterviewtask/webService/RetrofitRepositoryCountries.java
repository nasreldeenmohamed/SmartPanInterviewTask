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

public class RetrofitRepositoryCountries {

    private APIRequests APIRequests;
    private static RetrofitRepositoryCountries retrofitRepository;

    private RetrofitRepositoryCountries() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_COUNTRIES)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIRequests = retrofit.create(APIRequests.class);
    }

    public synchronized static RetrofitRepositoryCountries getInstance() {

        if (retrofitRepository == null) {
            retrofitRepository = new RetrofitRepositoryCountries();
        }

        return retrofitRepository;
    }

    public LiveData<List<Country>> getAllCountry() {
        final LiveData<List<Country>> countriesList = new MutableLiveData<>();

        APIRequests.getAllCountries().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if (response.isSuccessful()) {
                    ((MutableLiveData<List<Country>>) countriesList).setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

            }
        });

        return countriesList;
    }
}
