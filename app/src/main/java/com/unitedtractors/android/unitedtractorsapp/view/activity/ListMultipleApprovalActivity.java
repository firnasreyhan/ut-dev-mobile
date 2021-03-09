package com.unitedtractors.android.unitedtractorsapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.adapter.TabAdapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListMultipleApprovalBinding;
import com.unitedtractors.android.unitedtractorsapp.view.fragment.ApprovalFragment;
import com.unitedtractors.android.unitedtractorsapp.view.fragment.ApprovalProgresFragment;

public class ListMultipleApprovalActivity extends AppCompatActivity {
    private ActivityListMultipleApprovalBinding binding;
    private TabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListMultipleApprovalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tabAdapter = new TabAdapter(getSupportFragmentManager(), this);
        tabAdapter.addFragment(new ApprovalFragment(), "Approval", R.drawable.ic_circle);
        tabAdapter.addFragment(new ApprovalProgresFragment(), "Progres", R.drawable.ic_circle);

        binding.viewPager.setAdapter(tabAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        highLightCurrentTab(0);

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                highLightCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void highLightCurrentTab(int position) {
        for (int i = 0; i < binding.tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = binding.tabLayout.getTabAt(i);
            assert tab != null;
            tab.setCustomView(null);
            tab.setCustomView(tabAdapter.getTabView(i));
        }
        TabLayout.Tab tab = binding.tabLayout.getTabAt(position);
        assert tab != null;
        tab.setCustomView(null);
        tab.setCustomView(tabAdapter.getSelectedTabView(position));
    }
}