package com.smartpan.smartpaninterviewtask.modules.main.view.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartpan.smartpaninterviewtask.R;
import com.smartpan.smartpaninterviewtask.databinding.FragmentContactsBinding;
import com.smartpan.smartpaninterviewtask.models.PhoneContact;
import com.smartpan.smartpaninterviewtask.modules.main.view.adapters.ContactsAdapter;
import com.smartpan.smartpaninterviewtask.modules.main.viewModels.ContactsViewModel;

import java.util.List;

public class ContactsFragment extends Fragment {

    FragmentContactsBinding binding;
    ContactsAdapter contactsAdapter;
    ContactsViewModel contactsViewModel;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contacts, container,
                false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
//        binding.setcountry(data);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.recyclerViewContacts.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerViewContacts.setHasFixedSize(true);
        contactsAdapter = new ContactsAdapter();
        binding.recyclerViewContacts.setAdapter(contactsAdapter);

//        contactsViewModel = ViewModelProviders.of(this).get(ContactsViewModel.class);
//        contactsViewModel.getContactsLiveData().observe(this, new Observer<List<PhoneContact>>() {
//            @Override
//            public void onChanged(@Nullable List<PhoneContact> phoneContacts) {
//                contactsAdapter.submitList(phoneContacts);
//            }
//        });
    }

}
