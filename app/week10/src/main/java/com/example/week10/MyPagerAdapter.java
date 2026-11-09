package com.example.week10;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {

    public MyPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) { // 0 ~ 2
            case 0 :
                fragment = new BMIFragment();
                break;
            case 1 :
                fragment = new AreaFragment();
                break;
            case 2 :
                fragment = new NaverFragment1();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence title = null;
        switch (position) {
            case 0 :
                title = "BMI 계산기";
                break;
            case 1 :
                title = "면적 계산기";
                break;
            case 2 :
                title = "Naver";
                break;
        }
        return title;
    }
}