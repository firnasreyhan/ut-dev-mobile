package com.unitedtractors.android.unitedtractorsapp.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unitedtractors.android.unitedtractorsapp.databinding.FragmentTaskBinding;

public class TaskFragment extends Fragment {
    private FragmentTaskBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTaskBinding.inflate(inflater, container, false);
        View view = binding.getRoot();



        return view;
    }
}