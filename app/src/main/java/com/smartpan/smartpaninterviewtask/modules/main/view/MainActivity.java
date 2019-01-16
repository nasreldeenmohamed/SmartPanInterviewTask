package com.smartpan.smartpaninterviewtask.modules.main.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.smartpan.smartpaninterviewtask.R;
import com.smartpan.smartpaninterviewtask.modules.main.view.fragments.ContactsFragment;
import com.smartpan.smartpaninterviewtask.modules.main.view.fragments.CountriesFragment;
import com.smartpan.smartpaninterviewtask.modules.main.view.fragments.MapFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager tabsViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle(R.string.home);

        initViews();
    }


    private void initViews() {
        tabsViewPager = findViewById(R.id.viewpager);
        setupViewPager(tabsViewPager);
        tabLayout = findViewById(R.id.sliding_tabs);

        tabLayout.setupWithViewPager(tabsViewPager);
        setupTabIcons();
        tabsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            int count = 0;

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabsViewPager.setCurrentItem(tab.getPosition());


//                if (tabLayout.getTabAt(0) == tab) {
//                    tabLayout.getTabAt(1).setText(R.string.to);
//                } else if (tabLayout.getTabAt(1) == tab) {
//                    tabLayout.getTabAt(0).setText(R.string.overview);
//                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }


            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new MapFragment());
        adapter.addFragment(new CountriesFragment());
        adapter.addFragment(new ContactsFragment());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
    }

    private void setupTabIcons() {

        tabLayout.getTabAt(0).setText(R.string.map);
        tabLayout.getTabAt(1).setText(R.string.countries);
        tabLayout.getTabAt(2).setText(R.string.speakers);

//        tabLayout.setTabTextColors(Color.parseColor(getColor(R.i)), Color.parseColor("#f8cf2d"));
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            switch (position) {
                case 0:
                    MapFragment mapFragment = new MapFragment();
//                    bundle.putString("fav_sessions", gson.toJson(FavoriteSessions));
//                    favSessionsFragment.setArguments(bundle);
                    return mapFragment;
                case 1:
                    CountriesFragment countriesFragment = new CountriesFragment();
//                    bundle.putString("fav_talks", gson.toJson(FavoriteTalks));
//                    favTalksFragment.setArguments(bundle);
                    return countriesFragment;
                case 2:
                    ContactsFragment contactsFragment = new ContactsFragment();
//                    bundle.putString("fav_speakers", gson.toJson(FavoriteSpeakers));
//                    favSpeakersFragment.setArguments(bundle);
                    return contactsFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }


        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

    }

}
