package com.unitedtractors.android.unitedtractorsapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.unitedtractors.android.unitedtractorsapp.databinding.FragmentBerandaBinding;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanMobilDinasModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.pembelian_snack.PembelianSnackActivity;
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

        binding.textViewNamaUser.setText(AppPreference.getUser(getActivity()).getNamaUsers());

        binding.cardViewPermintaanAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), PermintaanAssetActivity.class));
            }
        });

        binding.cardViewPembelianSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), PembelianSnackActivity.class));
            }
        });

        binding.cardViewPermintaanMobilDinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), PermintaanMobilDinasActivity.class));
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