package com.unitedtractors.android.unitedtractorsapp.view.activity.form.kontrol_catering_harian;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.adapter.KontrolHarianAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKontrolCateringHarianBinding;
import com.unitedtractors.android.unitedtractorsapp.model.KontrolHarianModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.KontrolHarianViewModel;

import java.util.ArrayList;
import java.util.List;

public class KontrolCateringHarianActivity extends AppCompatActivity {
    private ActivityKontrolCateringHarianBinding binding;
    private KontrolHarianViewModel viewModel;
    private ProgressDialog progressDialog;
    private KontrolHarianModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKontrolCateringHarianBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");

        viewModel = ViewModelProviders.of(this).get(KontrolHarianViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu Sebentar...");
        progressDialog.setCancelable(false);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<KontrolHarianModel.DetailKontrolHarian> list = new ArrayList<>();
        list.add(new KontrolHarianModel.DetailKontrolHarian(
                "Kondisi Karyawan",
                1,
                ""
        ));
        list.add(new KontrolHarianModel.DetailKontrolHarian(
                "Kendaraan",
                1,
                ""
        ));
        list.add(new KontrolHarianModel.DetailKontrolHarian(
                "Penyajian Makanan",
                1,
                ""
        ));
        list.add(new KontrolHarianModel.DetailKontrolHarian(
                "Penyajian Makanan",
                1,
                ""
        ));
        list.add(new KontrolHarianModel.DetailKontrolHarian(
                "Sample yang ditempatkan di poliklinik",
                1,
                ""
        ));
        list.add(new KontrolHarianModel.DetailKontrolHarian(
                "Kebersihan Penyajian",
                1,
                ""
        ));



        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new KontrolHarianAdapter(list));

        binding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    binding.materialButtonAjukan.setEnabled(true);
                    binding.materialButtonAjukan.setBackgroundColor(getResources().getColor(R.color.primary));
                } else {
                    binding.materialButtonAjukan.setEnabled(false);
                    binding.materialButtonAjukan.setBackgroundColor(getResources().getColor(R.color.button_disable));
                }
            }
        });

        binding.materialButtonAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();

                model = new KontrolHarianModel(
                        AppPreference.getUser(v.getContext()).getIdUsers(),
                        idMapping,
                        "08:00:00",
                        "2021-02-02",
                        list(KontrolHarianAdapter.getList())
                );

                viewModel.postControlHarian(
                        model
                ).observe(KontrolCateringHarianActivity.this, new Observer<BaseResponse>() {
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

    private List<KontrolHarianModel.DetailKontrolHarian> list(List<KontrolHarianModel.DetailKontrolHarian> list) {
        for (KontrolHarianModel.DetailKontrolHarian model : list) {
            if (model.getKeterangan().isEmpty()) {
                model.setKeterangan("-");
            }
        }
        return list;
    }
}