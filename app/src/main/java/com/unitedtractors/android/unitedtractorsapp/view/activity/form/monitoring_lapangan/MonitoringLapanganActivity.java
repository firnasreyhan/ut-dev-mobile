package com.unitedtractors.android.unitedtractorsapp.view.activity.form.monitoring_lapangan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.adapter.MingguMonitoringLapanganAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguMonitoringCateringEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguMonitoringCateringEntity;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityMonitoringLapanganBinding;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistPompaAirBersihModel;
import com.unitedtractors.android.unitedtractorsapp.model.MonitoringLapanganModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_pompa_air_bersih.ChecklistPompaAirBersihActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.MonitoringLapanganViewModel;

import java.util.List;

public class MonitoringLapanganActivity extends AppCompatActivity {
    private ActivityMonitoringLapanganBinding binding;
    private MonitoringLapanganViewModel viewModel;
    private ProgressDialog progressDialog;

    private boolean isValid;
    private MonitoringLapanganModel.DetailMonitoringLapanganCatering cek1, cek2, cek3, cek4, cek5, cek6, cek7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMonitoringLapanganBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        cek1 = new MonitoringLapanganModel.DetailMonitoringLapanganCatering();
        cek2 = new MonitoringLapanganModel.DetailMonitoringLapanganCatering();
        cek3 = new MonitoringLapanganModel.DetailMonitoringLapanganCatering();
        cek4 = new MonitoringLapanganModel.DetailMonitoringLapanganCatering();
        cek5 = new MonitoringLapanganModel.DetailMonitoringLapanganCatering();
        cek6 = new MonitoringLapanganModel.DetailMonitoringLapanganCatering();
        cek7 = new MonitoringLapanganModel.DetailMonitoringLapanganCatering();

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(MonitoringLapanganViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu sebentar...");
        progressDialog.setCancelable(false);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(MonitoringLapanganActivity.this));

        viewModel.getMinggu().observe(this, new Observer<List<MingguMonitoringCateringEntity>>() {
            @Override
            public void onChanged(List<MingguMonitoringCateringEntity> mingguEntities) {
                if (mingguEntities.isEmpty()) {
                    viewModel.insertMinggu(mingguEntity("Hari Ke 1", false));
                    viewModel.insertMinggu(mingguEntity("Hari Ke 2", false));
                    viewModel.insertMinggu(mingguEntity("Hari Ke 3", false));
                    viewModel.insertMinggu(mingguEntity("Hari Ke 4", false));
                    viewModel.insertMinggu(mingguEntity("Hari Ke 5", false));
                    viewModel.insertMinggu(mingguEntity("Hari Ke 6", false));
                    viewModel.insertMinggu(mingguEntity("Hari Ke 7", false));
                } else {
                    binding.recyclerView.setAdapter(new MingguMonitoringLapanganAdapter(mingguEntities));
                    isValid = mingguEntities.get(0).status && mingguEntities.get(1).status && mingguEntities.get(2).status && mingguEntities.get(3).status && mingguEntities.get(4).status;;
                }
            }
        });

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

        detailMinggu(1);
        detailMinggu(2);
        detailMinggu(3);
        detailMinggu(4);
        detailMinggu(5);
        detailMinggu(6);
        detailMinggu(7);

        binding.materialButtonAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid) {
                    progressDialog.show();

                    MonitoringLapanganModel model = new MonitoringLapanganModel(
                            AppPreference.getUser(v.getContext()).getIdUsers(),
                            idMapping,
                            cek1,
                            cek2,
                            cek3,
                            cek4,
                            cek5,
                            cek6,
                            cek7
                    );

                    viewModel.postMonitoringLapangan(
                            model
                    ).observe(MonitoringLapanganActivity.this, new Observer<BaseResponse>() {
                        @Override
                        public void onChanged(BaseResponse baseResponse) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            if (baseResponse != null) {
                                if (baseResponse.isStatus()) {
                                    viewModel.deleteDetaiAlllMinggu();
                                    viewModel.updateAllMinggu(false);
                                    binding.checkBox.setChecked(false);

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
                } else {
                    Toast.makeText(MonitoringLapanganActivity.this, "Mohon isi semua data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private MingguMonitoringCateringEntity mingguEntity(String mingguKe, boolean status) {
        MingguMonitoringCateringEntity entity = new MingguMonitoringCateringEntity();
        entity.mingguKe = mingguKe;
        entity.status = status;
        return entity;
    }

    private void detailMinggu(int mingguKe) {
        viewModel.getDetailMinggu(
                mingguKe
        ).observe(this, new Observer<List<DetailMingguMonitoringCateringEntity>>() {
            @Override
            public void onChanged(List<DetailMingguMonitoringCateringEntity> detailMingguEntities) {
                if (detailMingguEntities != null) {
                    Log.e(String.valueOf(mingguKe), String.valueOf(detailMingguEntities.size()));
                    if (!detailMingguEntities.isEmpty()) {
                        if (mingguKe == 1) {
                            cek1.setTgl(detailMingguEntities.get(0).tanggal);
                            cek1.setOrder(detailMingguEntities.get(0).order.isEmpty() ? "0" : detailMingguEntities.get(0).order);
                            cek1.setBawa(detailMingguEntities.get(0).bawa.isEmpty() ? "0" : detailMingguEntities.get(0).bawa);
                            cek1.setKupon(detailMingguEntities.get(0).kupon.isEmpty() ? "0" : detailMingguEntities.get(0).kupon);
                        } else if (mingguKe == 2) {
                            cek2.setTgl(detailMingguEntities.get(0).tanggal);
                            cek2.setOrder(detailMingguEntities.get(0).order.isEmpty() ? "0" : detailMingguEntities.get(0).order);
                            cek2.setBawa(detailMingguEntities.get(0).bawa.isEmpty() ? "0" : detailMingguEntities.get(0).bawa);
                            cek2.setKupon(detailMingguEntities.get(0).kupon.isEmpty() ? "0" : detailMingguEntities.get(0).kupon);
                        } else if (mingguKe == 3) {
                            cek3.setTgl(detailMingguEntities.get(0).tanggal);
                            cek3.setOrder(detailMingguEntities.get(0).order.isEmpty() ? "0" : detailMingguEntities.get(0).order);
                            cek3.setBawa(detailMingguEntities.get(0).bawa.isEmpty() ? "0" : detailMingguEntities.get(0).bawa);
                            cek3.setKupon(detailMingguEntities.get(0).kupon.isEmpty() ? "0" : detailMingguEntities.get(0).kupon);
                        } else if (mingguKe == 4) {
                            cek4.setTgl(detailMingguEntities.get(0).tanggal);
                            cek4.setOrder(detailMingguEntities.get(0).order.isEmpty() ? "0" : detailMingguEntities.get(0).order);
                            cek4.setBawa(detailMingguEntities.get(0).bawa.isEmpty() ? "0" : detailMingguEntities.get(0).bawa);
                            cek4.setKupon(detailMingguEntities.get(0).kupon.isEmpty() ? "0" : detailMingguEntities.get(0).kupon);
                        } else if (mingguKe == 5) {
                            cek5.setTgl(detailMingguEntities.get(0).tanggal);
                            cek5.setOrder(detailMingguEntities.get(0).order.isEmpty() ? "0" : detailMingguEntities.get(0).order);
                            cek5.setBawa(detailMingguEntities.get(0).bawa.isEmpty() ? "0" : detailMingguEntities.get(0).bawa);
                            cek5.setKupon(detailMingguEntities.get(0).kupon.isEmpty() ? "0" : detailMingguEntities.get(0).kupon);
                        } else if (mingguKe == 6) {
                            cek6.setTgl(detailMingguEntities.get(0).tanggal);
                            cek6.setOrder(detailMingguEntities.get(0).order.isEmpty() ? "0" : detailMingguEntities.get(0).order);
                            cek6.setBawa(detailMingguEntities.get(0).bawa.isEmpty() ? "0" : detailMingguEntities.get(0).bawa);
                            cek6.setKupon(detailMingguEntities.get(0).kupon.isEmpty() ? "0" : detailMingguEntities.get(0).kupon);
                        } else if (mingguKe == 7) {
                            cek7.setTgl(detailMingguEntities.get(0).tanggal);
                            cek7.setOrder(detailMingguEntities.get(0).order.isEmpty() ? "0" : detailMingguEntities.get(0).order);
                            cek7.setBawa(detailMingguEntities.get(0).bawa.isEmpty() ? "0" : detailMingguEntities.get(0).bawa);
                            cek7.setKupon(detailMingguEntities.get(0).kupon.isEmpty() ? "0" : detailMingguEntities.get(0).kupon);
                        }
                    }
                }
            }
        });
    }
}