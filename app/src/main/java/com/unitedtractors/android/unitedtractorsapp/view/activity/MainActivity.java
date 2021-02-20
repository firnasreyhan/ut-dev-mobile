package com.unitedtractors.android.unitedtractorsapp.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityMainBinding;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.fragment.BerandaFragment;
import com.unitedtractors.android.unitedtractorsapp.view.fragment.BerandaPICFragment;
import com.unitedtractors.android.unitedtractorsapp.view.fragment.ProfileFragment;
import com.unitedtractors.android.unitedtractorsapp.view.fragment.ProgressFragment;
import com.unitedtractors.android.unitedtractorsapp.view.fragment.TaskFragment;
import com.zach.znavigator.ZNavigation.NavigationActivity;

import java.util.LinkedHashMap;

public class MainActivity extends NavigationActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        LinkedHashMap<Integer, Fragment> rootFragment = new LinkedHashMap<>();
        if (AppPreference.getUser(this).getRoleUsers().equalsIgnoreCase("staff")) {
            rootFragment.put(R.id.menu_beranda, new BerandaFragment());
        } else {
            rootFragment.put(R.id.menu_beranda, new BerandaPICFragment());
        }
        rootFragment.put(R.id.menu_task, new TaskFragment());
        rootFragment.put(R.id.menu_progress, new ProgressFragment());
        rootFragment.put(R.id.menu_profile, new ProfileFragment());
        init(rootFragment, R.id.frameLayoutContent);

        binding.bottomNavigationViewMenu.setOnNavigationItemSelectedListener(this);
        binding.bottomNavigationViewMenu.setOnNavigationItemReselectedListener(this);
    }

    @Override
    public void tabChanged(int id) {
        binding.bottomNavigationViewMenu.getMenu().findItem(id).setChecked(true);
    }
}