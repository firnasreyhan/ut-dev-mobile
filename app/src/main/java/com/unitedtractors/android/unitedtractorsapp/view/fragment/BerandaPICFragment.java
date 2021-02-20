package com.unitedtractors.android.unitedtractorsapp.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.FragmentBerandaPICBinding;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;

public class BerandaPICFragment extends Fragment {
    private FragmentBerandaPICBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBerandaPICBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.textViewNamaUser.setText(AppPreference.getUser(getActivity()).getNamaUsers());

        return view;
    }
}