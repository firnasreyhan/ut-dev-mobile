package com.unitedtractors.android.unitedtractorsapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unitedtractors.android.unitedtractorsapp.databinding.FragmentBerandaBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.hasil_test_food_catering.HasilTestFoodCateringActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.komplain_atau_usulan.KomplainAtauUsulanActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.kontrol_harian.KontrolHarianActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_asset.PermintaanAssetActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas.PermintaanMobilDinasActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.syarat_legalitas_catering.SyaratLegalitasCateringActivity;

public class BerandaFragment extends Fragment {
    private FragmentBerandaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBerandaBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.cardViewPermintaanAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), PermintaanAssetActivity.class));
            }
        });

        binding.cardViewPermintaanMobilDinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), SyaratLegalitasCateringActivity.class));
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}