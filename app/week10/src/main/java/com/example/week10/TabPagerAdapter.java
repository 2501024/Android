package com.example.week10;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabPagerAdapter extends FragmentStateAdapter {

    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) { // 0 ~ 2
            case 0 :
                fragment = new BMIFragment();
                break;
            case 1 :
                fragment = new AreaFragment();
                break;
            case 2 :
                fragment = new NaverFragment();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}