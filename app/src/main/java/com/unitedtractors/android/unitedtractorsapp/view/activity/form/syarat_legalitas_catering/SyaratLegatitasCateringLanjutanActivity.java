package com.unitedtractors.android.unitedtractorsapp.view.activity.form.syarat_legalitas_catering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.PertanyaanAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySyaratLegalitasCateringBinding;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySyaratLegatitasCateringLanjutanBinding;
import com.unitedtractors.android.unitedtractorsapp.model.PertanyaanModel;
import com.unitedtractors.android.unitedtractorsapp.model.SyaratLegalitasCateringModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.sidak_catering.KonfirmasiSidakCateringActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.KonfirmasiSyaratLegalitasCateringViewModel;

import java.util.ArrayList;
import java.util.List;

public class SyaratLegatitasCateringLanjutanActivity extends AppCompatActivity {
    private ActivitySyaratLegatitasCateringLanjutanBinding binding;
    private KonfirmasiSyaratLegalitasCateringViewModel viewModel;
    private ProgressDialog progressDialog;
    private SyaratLegalitasCateringModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySyaratLegatitasCateringLanjutanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = ViewModelProviders.of(this).get(KonfirmasiSyaratLegalitasCateringViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu sebentar...");
        progressDialog.setCancelable(false);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String namaCatering = getIntent().getStringExtra("NAMA_CATERING");
        String alamatCatering = getIntent().getStringExtra("ALAMAT_CATERING");
        boolean[] syarat = getIntent().getBooleanArrayExtra("SYARAT");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<PertanyaanModel> list = new ArrayList<>();
        list.add(new PertanyaanModel(
                "Dapur Bersih",
                true
        ));
        list.add(new PertanyaanModel(
                "Perlengkapan Alat Dapur mampu melayani minimal 5000 porsi",
                true
        ));
        list.add(new PertanyaanModel(
                "Dapur pisah dengan rumah tinggal",
                true
        ));
        list.add(new PertanyaanModel(
                "Memiliki kendaraan terutama mobil box",
                true
        ));
        list.add(new PertanyaanModel(
                "Jarak dapur ke – UT",
                true
        ));
        list.add(new PertanyaanModel(
                "Kedapur catering harus bisa masuk mobil",
                true
        ));
        list.add(new PertanyaanModel(
                "Memiliki karyawan",
                true
        ));

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new PertanyaanAdapter(list));

        model = new SyaratLegalitasCateringModel(
                AppPreference.getUser(this).getIdUsers(),
                idMapping,
                namaCatering,
                alamatCatering,
                syarat,
                getStatus()
        );

        binding.materialButtonAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                viewModel.postLegalitas(
                        model
                ).observe(SyaratLegatitasCateringLanjutanActivity.this, new Observer<BaseResponse>() {
                    @Override
                    public void onChanged(BaseResponse baseResponse) {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        if (baseResponse != null) {
                            if (baseResponse.isStatus()) {
                                startActivity(new Intent(v.getContext(), ScreenFeedbackActivity.class));
                            } else {
                                new AlertDialog.Builder(v.getContext())
                                        .setTitle("Pesan")
                                        .setMessage(baseResponse.getMessage())
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        })
                                        .create()
                                        .show();
                            }
                        } else {
                            new AlertDialog.Builder(v.getContext())
                                    .setTitle("Pesan")
                                    .setMessage("Terjadi kesalahan pada server, silahkan coba beberapa saat lagi")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .create()
                                    .show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public boolean[] getStatus() {
        boolean[] list = new boolean[PertanyaanAdapter.getList().size()];
        for (int i = 0; i < PertanyaanAdapter.getList().size(); i++) {
            list[i] = PertanyaanAdapter.getList().get(i).isStatus();
        }
        return list;
    }
}