package com.smartpan.smartpaninterviewtask.modules.main.view.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.smartpan.smartpaninterviewtask.R;
import com.smartpan.smartpaninterviewtask.databinding.FragmentContactsBinding;
import com.smartpan.smartpaninterviewtask.modules.main.view.adapters.ContactsAdapter;
import com.smartpan.smartpaninterviewtask.modules.main.viewModels.ContactsViewModel;

public class ContactsFragment extends Fragment {

    FragmentContactsBinding binding;
    ContactsAdapter contactsAdapter;
    ContactsViewModel contactsViewModel;

    public static final int MY_PERMISSIONS_REQUEST_CONTACTS = 11;

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
        if (!checkLocationPermission()) {
//            registerContacts();
        }
    }


    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_CONTACTS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                new AlertDialog.Builder(getActivity())
                        .setTitle("phone contacts permission")
                        .setMessage("grant permission")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.READ_CONTACTS},
                                        MY_PERMISSIONS_REQUEST_CONTACTS);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_CONTACTS);
            }
            return false;

        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS)
                            == PackageManager.PERMISSION_GRANTED) {
                        registerContacts();

                    }
                    // googleMap.setMyLocationEnabled(true);
                } else {

                    Toast.makeText(getActivity(), "Error in contacts permission",
                            Toast.LENGTH_SHORT).show();

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }

    void registerContacts() {
        contactsViewModel = ViewModelProviders.of(this).get(ContactsViewModel.class);
        contactsViewModel.getContactsLiveData().observe(this, phoneContacts -> {
            binding.progressBar.setVisibility(View.INVISIBLE);
            contactsAdapter.submitList(phoneContacts);
        });
    }

}
