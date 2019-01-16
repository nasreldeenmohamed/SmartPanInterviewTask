package com.smartpan.smartpaninterviewtask.modules.main.view.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartpan.smartpaninterviewtask.R;
import com.smartpan.smartpaninterviewtask.databinding.FragmentCountriesBinding;
import com.smartpan.smartpaninterviewtask.models.Country;
import com.smartpan.smartpaninterviewtask.modules.main.view.adapters.CountriesAdapter;
import com.smartpan.smartpaninterviewtask.modules.main.viewModels.CountriesViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CountriesFragment extends Fragment {

    FragmentCountriesBinding binding;
    CountriesAdapter countriesAdapter;
    CountriesViewModel coutriesViewModel;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_countries, container,
                false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
//        binding.setcountry(data);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        binding.recyclerViewCountries.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerViewCountries.setHasFixedSize(true);
        countriesAdapter = new CountriesAdapter();
        binding.recyclerViewCountries.setAdapter(countriesAdapter);

        coutriesViewModel = ViewModelProviders.of(this).get(CountriesViewModel.class);
        coutriesViewModel.getCountriesListLiveData().observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(@Nullable List<Country> countries) {
                countriesAdapter.submitList(countries);
            }
        });

    }
}
