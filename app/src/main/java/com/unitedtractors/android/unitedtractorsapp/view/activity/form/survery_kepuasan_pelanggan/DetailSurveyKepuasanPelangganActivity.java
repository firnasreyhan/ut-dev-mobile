package com.unitedtractors.android.unitedtractorsapp.view.activity.form.survery_kepuasan_pelanggan;

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

import com.unitedtractors.android.unitedtractorsapp.adapter.Pertanyaan2Adapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.SurveyKepuasanPelangganAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityDetailSurveyKepuasanPelangganBinding;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySurveryKepuasanPelangganBinding;
import com.unitedtractors.android.unitedtractorsapp.model.SurveyKepuasanPelangganModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.pembelian_snack.KonfirmasiPembelianSnackActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.SurveyKepuasanPelangganViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailSurveyKepuasanPelangganActivity extends AppCompatActivity {
    private ActivityDetailSurveyKepuasanPelangganBinding binding;
    private SurveyKepuasanPelangganViewModel viewModel;
    private ProgressDialog progressDialog;

    SurveyKepuasanPelangganModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailSurveyKepuasanPelangganBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String tglSurvey = getIntent().getStringExtra("TGL_SURVEY");
        String nama = getIntent().getStringExtra("NAM");
        String departemen = getIntent().getStringExtra("DEPARTEMEN");
        String divisi = getIntent().getStringExtra("DIVISI");


        viewModel = ViewModelProviders.of(this).get(SurveyKepuasanPelangganViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu sebentar...");
        progressDialog.setCancelable(false);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<SurveyKepuasanPelangganModel.DetailSurveyKepuasanPelangan> list = new ArrayList<>();
        list.add(new SurveyKepuasanPelangganModel.DetailSurveyKepuasanPelangan(
                "Penyelesaian Claim, Team Maintenance mengutamakan Qualitas dan performance",
                3
        ));
        list.add(new SurveyKepuasanPelangganModel.DetailSurveyKepuasanPelangan(
                "Penyelesaian Claim, Team Maintenance melakukan secara profesional",
                3
        ));
        list.add(new SurveyKepuasanPelangganModel.DetailSurveyKepuasanPelangan(
                "Team Maintenance menyelesaikan permintaan dengan cepat",
                3
        ));
        list.add(new SurveyKepuasanPelangganModel.DetailSurveyKepuasanPelangan(
                "Penyelesaian Claim dilakukan dengan memperhatikan Aspek LK3",
                3
        ));
        list.add(new SurveyKepuasanPelangganModel.DetailSurveyKepuasanPelangan(
                "Dalam melakukan Service / Perbaikan, sudah didukung dengan sikap yang baik",
                3
        ));
        list.add(new SurveyKepuasanPelangganModel.DetailSurveyKepuasanPelangan(
                "Support Team Maintenance untuk kelancaran bagi Pelanggan / User",
                3
        ));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new SurveyKepuasanPelangganAdapter(list));

        binding.materialButtonAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();

                SurveyKepuasanPelangganModel model = new SurveyKepuasanPelangganModel(
                        AppPreference.getUser(v.getContext()).getIdUsers(),
                        idMapping,
                        AppPreference.getUser(v.getContext()).getNamaUsers(),
                        SurveyKepuasanPelangganAdapter.getList()
                );

                viewModel.postKepuasan(
                        model
                ).observe(DetailSurveyKepuasanPelangganActivity.this, new Observer<BaseResponse>() {
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
}