package com.unitedtractors.android.unitedtractorsapp.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityMainBinding;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.fragment.BerandaMultipleApprovalFragment;
import com.unitedtractors.android.unitedtractorsapp.view.fragment.BerandaNoApprovalFragment;
import com.unitedtractors.android.unitedtractorsapp.view.fragment.BerandaSingleApprovalFragment;
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
        if (AppPreference.getUser(this).getRoleUsers().equalsIgnoreCase("Staff")
                || AppPreference.getUser(this).getRoleUsers().equalsIgnoreCase("PICM")
                || AppPreference.getUser(this).getRoleUsers().equalsIgnoreCase("PICG")) {
            rootFragment.put(R.id.menu_beranda, new BerandaNoApprovalFragment());
        } else if (AppPreference.getUser(this).getRoleUsers().equalsIgnoreCase("Section Head")
                || AppPreference.getUser(this).getRoleUsers().equalsIgnoreCase("PICA")) {
            rootFragment.put(R.id.menu_beranda, new BerandaMultipleApprovalFragment());
        } else if (AppPreference.getUser(this).getRoleUsers().equalsIgnoreCase("PICK")
                || AppPreference.getUser(this).getRoleUsers().equalsIgnoreCase("Department Head")
                || AppPreference.getUser(this).getRoleUsers().equalsIgnoreCase("Division Head")) {
            rootFragment.put(R.id.menu_beranda, new BerandaSingleApprovalFragment());
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