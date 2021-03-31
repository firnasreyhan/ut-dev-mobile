package com.unitedtractors.android.unitedtractorsapp.view.activity.form.monitoring_lapangan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.adapter.MingguMonitoringLapanganAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.MingguPompaAirBersihAdapter;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguMonitoringCateringEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguPompaAirBersihEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguMonitoringCateringEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguPompaAirBersihEntity;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityMonitoringLapanganBinding;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistPompaAirBersihModel;
import com.unitedtractors.android.unitedtractorsapp.model.MonitoringLapanganCatering;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_pompa_air_bersih.ChecklistPompaAirBersihActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.pembelian_snack.ListPembelianSnackActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.MonitoringLapanganViewModel;

import java.util.List;

public class MonitoringLapanganActivity extends AppCompatActivity {
    private ActivityMonitoringLapanganBinding binding;
    private MonitoringLapanganViewModel viewModel;

    private boolean isValid;
    private MonitoringLapanganCatering.DetailMonitoringLapanganCatering cek1, cek2, cek3, cek4, cek5, cek6, cek7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMonitoringLapanganBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        cek1 = new MonitoringLapanganCatering.DetailMonitoringLapanganCatering();
        cek2 = new MonitoringLapanganCatering.DetailMonitoringLapanganCatering();
        cek3 = new MonitoringLapanganCatering.DetailMonitoringLapanganCatering();
        cek4 = new MonitoringLapanganCatering.DetailMonitoringLapanganCatering();
        cek5 = new MonitoringLapanganCatering.DetailMonitoringLapanganCatering();
        cek6 = new MonitoringLapanganCatering.DetailMonitoringLapanganCatering();
        cek7 = new MonitoringLapanganCatering.DetailMonitoringLapanganCatering();

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(MonitoringLapanganViewModel.class);

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

                    isValid = mingguEntities.get(0).status && mingguEntities.get(1).status && mingguEntities.get(2).status && mingguEntities.get(3).status;
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
                    if (!detailMingguEntities.isEmpty()) {
                        if (detailMingguEntities.size() == 4) {
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
            }
        });
    }
}