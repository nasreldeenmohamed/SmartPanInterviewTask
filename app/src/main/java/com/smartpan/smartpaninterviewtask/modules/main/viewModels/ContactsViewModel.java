package com.smartpan.smartpaninterviewtask.modules.main.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;

import com.smartpan.smartpaninterviewtask.models.PhoneContact;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class ContactsViewModel extends AndroidViewModel {
    LiveData<List<PhoneContact>> ContactsLiveData;
//    CountriesRepository countriesRepository;

    public ContactsViewModel(@NonNull Application application) {
        super(application);

        new GetContactsAsyncTask().execute("");
    }

    public LiveData<List<PhoneContact>> getContactsLiveData() {
        return ContactsLiveData;
    }


    private LiveData<List<PhoneContact>> getContactList() {
        List<PhoneContact> phoneContacts = new ArrayList<>();
        LiveData<List<PhoneContact>> phoneContactsListLiveData = new MutableLiveData<>();

        ContentResolver cr = getApplication().getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Log.i(TAG, "Name: " + name);
                        Log.i(TAG, "Phone Number: " + phoneNo);
                        phoneContacts.add(new PhoneContact(name, phoneNo));
                    }
                    pCur.close();
                }
            }
        }
        if (cur != null) {
            cur.close();
        }
        ((MutableLiveData<List<PhoneContact>>) phoneContactsListLiveData).postValue(phoneContacts);
        return phoneContactsListLiveData;
    }

    private class GetContactsAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            ContactsLiveData = getContactList();

            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {

        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

}
