package com.smartpan.smartpaninterviewtask.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.smartpan.smartpaninterviewtask.models.Country;
import com.smartpan.smartpaninterviewtask.webService.RetrofitRepositoryCountries;

import java.util.List;

public class CountriesRepository {

    RetrofitRepositoryCountries retrofitRepository;

    public CountriesRepository(Application application) {
        retrofitRepository = RetrofitRepositoryCountries.getInstance();
    }

    public LiveData<List<Country>> getAllCountries() {

        return retrofitRepository.getAllCountry();
    }
}
