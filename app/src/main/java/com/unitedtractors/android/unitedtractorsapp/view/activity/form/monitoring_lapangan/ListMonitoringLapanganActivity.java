package com.unitedtractors.android.unitedtractorsapp.view.activity.form.monitoring_lapangan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;

import com.unitedtractors.android.unitedtractorsapp.adapter.MonitoringLapanganAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.Pertanyaan3Adapter;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguMonitoringCateringEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguPompaAirBersihEntity;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListMonitoringLapanganBinding;
import com.unitedtractors.android.unitedtractorsapp.model.MonitoringLapanganCatering;
import com.unitedtractors.android.unitedtractorsapp.model.MonitoringLapanganModel;
import com.unitedtractors.android.unitedtractorsapp.model.Pertanyaan3Model;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.ListMonitoringLapanganViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ListMonitoringLapanganActivity extends AppCompatActivity {
    private ActivityListMonitoringLapanganBinding binding;
    private ListMonitoringLapanganViewModel viewModel;

    private Calendar calendar;
    private int id;
    private String tanggal;
    private int jumlahPesanan, actualBawa, actualKupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListMonitoringLapanganBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        id = getIntent().getIntExtra("ID", 0);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        calendar = Calendar.getInstance();
        viewModel = ViewModelProviders.of(this).get(ListMonitoringLapanganViewModel.class);

        SimpleDateFormat simpleDateFormatView = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
        SimpleDateFormat simpleDateFormatServer = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                tanggal = simpleDateFormatServer.format(calendar.getTime());
                binding.editTextTanggal.setText(simpleDateFormatView.format(calendar.getTime()));
            }
        };

        viewModel.getDetailMinggu(
                id
        ).observe(this, new Observer<List<DetailMingguMonitoringCateringEntity>>() {
            @Override
            public void onChanged(List<DetailMingguMonitoringCateringEntity> detailMingguEntities) {
                if (!detailMingguEntities.isEmpty()) {
                    binding.editTextTanggal.setText(detailMingguEntities.get(0).tanggalView);
                    binding.editTextJumlahPesanan.setText(detailMingguEntities.get(0).order);
                    binding.editTextActualBawa.setText(detailMingguEntities.get(0).bawa);
                    binding.editTextActualKupon.setText(detailMingguEntities.get(0).kupon);
                }

                jumlahPesanan = Integer.parseInt(binding.editTextJumlahPesanan.getText().toString());
                actualBawa = Integer.parseInt(binding.editTextActualBawa.getText().toString());
                actualKupon = Integer.parseInt(binding.editTextActualKupon.getText().toString());
            }
        });

        binding.editTextTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        binding.materialButtonTambahJumlahPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahPesanan++;
                binding.editTextJumlahPesanan.setText(String.valueOf(jumlahPesanan));
            }
        });

        binding.materialButtonKurangJumlahPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumlahPesanan > 0) {
                    jumlahPesanan--;
                    binding.editTextJumlahPesanan.setText(String.valueOf(jumlahPesanan));
                }
            }
        });

        binding.materialButtonTambahActualBawa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualBawa++;
                binding.editTextActualBawa.setText(String.valueOf(actualBawa));
            }
        });

        binding.materialButtonKurangActualBawa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actualBawa > 0) {
                    actualBawa--;
                    binding.editTextActualBawa.setText(String.valueOf(actualBawa));
                }
            }
        });

        binding.materialButtonKTambahActualKupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualKupon++;
                binding.editTextActualKupon.setText(String.valueOf(actualKupon));
            }
        });

        binding.materialButtonKurangActualKupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actualKupon > 0) {
                    actualKupon--;
                    binding.editTextActualKupon.setText(String.valueOf(actualKupon));
                }
            }
        });

        binding.materialButtonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    viewModel.deleteDetailMinggu(id);
                    viewModel.insertDetailMinggu(entity());
                    viewModel.updateMinggu(id, true);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private DetailMingguMonitoringCateringEntity entity() {
        DetailMingguMonitoringCateringEntity entity = new DetailMingguMonitoringCateringEntity();
        entity.mingguKe = id;
        entity.tanggal = tanggal;
        entity.tanggalView = binding.editTextTanggal.getText().toString();
        entity.order = String.valueOf(jumlahPesanan);
        entity.bawa = String.valueOf(actualBawa);
        entity.kupon = String.valueOf(actualKupon);
        return entity;
    }

    private boolean checkData() {
        boolean cek1 = true;

        if (binding.editTextTanggal.getText().toString().isEmpty()) {
            binding.editTextTanggal.setError("Mohon isi data berikut");
            cek1 = false;
        }

        return cek1;
    }
}