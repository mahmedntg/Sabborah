package com.example.company.sabborah.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.company.sabborah.R;

public class SplashActivity extends BaseActivity {
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fragment fragment = new SplashFragment();
        loadFragment(fragment);
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
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit(); // save the changes
    }
}
