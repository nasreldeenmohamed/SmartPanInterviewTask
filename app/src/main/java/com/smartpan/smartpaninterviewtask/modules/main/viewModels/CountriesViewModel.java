package com.smartpan.smartpaninterviewtask.modules.main.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.smartpan.smartpaninterviewtask.models.Country;
import com.smartpan.smartpaninterviewtask.repositories.CountriesRepository;

import java.util.List;

public class CountriesViewModel extends AndroidViewModel {
    LiveData<List<Country>> CountriesListLiveData = new MutableLiveData<>();
    CountriesRepository countriesRepository;

    public CountriesViewModel(@NonNull Application application) {
        super(application);
        countriesRepository = new CountriesRepository(application);
        CountriesListLiveData = countriesRepository.getAllCountries();
    }

    public LiveData<List<Country>> getCountriesListLiveData(){
        return this.CountriesListLiveData;
    }
}
