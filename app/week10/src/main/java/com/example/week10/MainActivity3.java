package com.example.week10;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

public class MainActivity3 extends AppCompatActivity {
    private BMIFragment bmiFragment;
    private AreaFragment areaFragment;
    private NaverFragment naverFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        bmiFragment = new BMIFragment();
        areaFragment = new AreaFragment();
        naverFragment = new NaverFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment, bmiFragment);
        transaction.add(R.id.fragment, areaFragment);
        transaction.hide(areaFragment);
        transaction.add(R.id.fragment, naverFragment);
        transaction.hide(naverFragment);
        transaction.commit();

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabLayout.Tab tab1 = tabLayout.newTab();
        tab1.setText("BMI 계산기");
        tabLayout.addTab(tab1);

        TabLayout.Tab tab2 = tabLayout.newTab();
        tab2.setText("면적 계산기");
        tabLayout.addTab(tab2);

        TabLayout.Tab tab3 = tabLayout.newTab();
        tab3.setText("Naver");
        tabLayout.addTab(tab3);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switchFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void switchFragment(int position) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(bmiFragment);
        transaction.hide(areaFragment);
        transaction.hide(naverFragment);
        if (position == 0) {
            transaction.show(bmiFragment);
        } else if (position == 1) {
            transaction.show(areaFragment);
        } else {
            transaction.show(naverFragment);
        }
        transaction.commit();
    }
}