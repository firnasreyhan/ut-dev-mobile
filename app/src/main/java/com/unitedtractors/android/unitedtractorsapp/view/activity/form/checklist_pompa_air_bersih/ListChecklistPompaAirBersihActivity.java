package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_pompa_air_bersih;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.unitedtractors.android.unitedtractorsapp.adapter.Pertanyaan3Adapter;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguPompaAirBersihEntity;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListChecklistPompaAirBersihBinding;
import com.unitedtractors.android.unitedtractorsapp.model.Pertanyaan3Model;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.ListChecklistPompaAirBersihViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ListChecklistPompaAirBersihActivity extends AppCompatActivity {
    private ActivityListChecklistPompaAirBersihBinding binding;
    private ListChecklistPompaAirBersihViewModel viewModel;

    private Calendar calendar;

    private int id;
    private String tanggal, tanggalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListChecklistPompaAirBersihBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        id = getIntent().getIntExtra("ID", 0);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(ListChecklistPompaAirBersihViewModel.class);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);

        List<Pertanyaan3Model> list = new ArrayList<>();

        viewModel.getDetailMinggu(
                id
        ).observe(this, new Observer<List<DetailMingguPompaAirBersihEntity>>() {
            @Override
            public void onChanged(List<DetailMingguPompaAirBersihEntity> detailMingguEntities) {
                if (detailMingguEntities.isEmpty()) {
                    list.add(new Pertanyaan3Model(
                            "Cek Kondisi Air Dalam Reservoir",
                            1,
                            "*Standard : Bak Terisi Penuh",
                            ""
                    ));
                    list.add(new Pertanyaan3Model(
                            "Cek Air Pancingan Pompa",
                            1,
                            "*Standard : Posisi 'ON' Menyembur",
                            ""
                    ));
                    list.add(new Pertanyaan3Model(
                            "Cek / Lihat Indicator Lamp",
                            1,
                            "*Standard : Indicator Lamp ‘OFF’",
                            ""
                    ));
                    list.add(new Pertanyaan3Model(
                            "Cek / Lihat Tekanan Udara dalam tangki",
                            1,
                            "*Standard : Posisi pada garis merah",
                            ""
                    ));
                    list.add(new Pertanyaan3Model(
                            "Cek / lihat flow meter",
                            1,
                            "*Standard : Catat angka",
                            ""
                    ));
                    list.add(new Pertanyaan3Model(
                            "Cek Supply air dari PDAM dan air tanah",
                            1,
                            "*Standard : Flow meter memutar",
                            ""
                    ));
                    list.add(new Pertanyaan3Model(
                            "Test secara manual supply air sumur",
                            1,
                            "*Standard : Angkat electroda",
                            ""
                    ));
                    list.add(new Pertanyaan3Model(
                            "Cek fungsi panel",
                            1,
                            "*Standard : Indikator lamp posisi 'Off'",
                            ""
                    ));
                } else {
                    for (DetailMingguPompaAirBersihEntity entity : detailMingguEntities) {
                        list.add(model(entity));
                        binding.editTextTanggal.setText(entity.tanggalView);
                    }
                }
                binding.recyclerView.setAdapter(new Pertanyaan3Adapter(list));
            }
        });

        calendar = Calendar.getInstance();

        SimpleDateFormat simpleDateFormatView = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
        SimpleDateFormat simpleDateFormatServer = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                binding.editTextTanggal.setText(simpleDateFormatView.format(calendar.getTime()));
                tanggalView = simpleDateFormatView.format(calendar.getTime());
                tanggal = simpleDateFormatServer.format(calendar.getTime());
            }
        };

        binding.editTextTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        binding.materialButtonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    viewModel.deleteDetailMinggu(id);
                    for (Pertanyaan3Model model : Pertanyaan3Adapter.getList()) {
                        viewModel.insertDetailMinggu(entity(model));
                    }
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

    private DetailMingguPompaAirBersihEntity entity(Pertanyaan3Model model) {
        DetailMingguPompaAirBersihEntity entity = new DetailMingguPompaAirBersihEntity();
        entity.pertanyaan = model.getPertanyaan();
        entity.mingguKe = id;
        entity.status = model.getStatus();
        entity.keterangan = model.getKeterangan();
        entity.catatan = model.getCatatan();
        entity.tanggal = tanggal;
        entity.tanggalView = tanggalView;
        return entity;
    }

    private Pertanyaan3Model model(DetailMingguPompaAirBersihEntity entity) {
        Pertanyaan3Model model = new Pertanyaan3Model(
                entity.pertanyaan,
                entity.status,
                entity.keterangan,
                entity.catatan
        );
        return model;
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