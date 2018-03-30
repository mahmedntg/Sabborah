package com.example.company.sabborah.views;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.company.sabborah.R;
import com.example.company.sabborah.adapters.ViewPagerAdapter;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {
    private FragmentTransaction fragmentTransaction;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.tabs)
    public TabLayout tabLayout;
    @BindView(R.id.viewpager)
    public ViewPager viewPager;
    ReservationFragment reservationFragment;
    SplashFragment splashFragment;
    TutorAddSubjectFragment tutorAddSubjectFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reservationFragment = new ReservationFragment();
        splashFragment = new SplashFragment();
        tutorAddSubjectFragment = new TutorAddSubjectFragment();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0)
                    reservationFragment.refreshData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //Fragment fragment = new SplashFragment();
        //loadFragment(fragment);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(reservationFragment, "Reservation");
        adapter.addFragment(splashFragment, "Availability");
        adapter.addFragment(tutorAddSubjectFragment, "Add Subject");
        viewPager.setAdapter(adapter);
    }

    public void addSubject(View view) {
        Fragment fragment = new TutorAddSubjectFragment();
        loadFragment(fragment);
    }

    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    private void loadFragment(Fragment fragment) {
        // create a FragmentManager
        FragmentManager fm = getSupportFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        //fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit(); // save the changes
    }
}
