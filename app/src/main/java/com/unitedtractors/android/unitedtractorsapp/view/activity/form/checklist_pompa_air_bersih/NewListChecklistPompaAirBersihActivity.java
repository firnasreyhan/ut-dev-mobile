package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_pompa_air_bersih;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.Pertanyaan3Adapter;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguEntity;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityNewListChecklistPompaAirBersihBinding;
import com.unitedtractors.android.unitedtractorsapp.model.Pertanyaan3Model;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.ListChecklistPompaAirBersihViewModel;

import java.util.ArrayList;
import java.util.List;

public class NewListChecklistPompaAirBersihActivity extends AppCompatActivity {
    private ActivityNewListChecklistPompaAirBersihBinding binding;
    private ListChecklistPompaAirBersihViewModel viewModel;

    int id;
    boolean isEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewListChecklistPompaAirBersihBinding.inflate(getLayoutInflater());
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
        ).observe(this, new Observer<List<DetailMingguEntity>>() {
            @Override
            public void onChanged(List<DetailMingguEntity> detailMingguEntities) {
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
                    for (DetailMingguEntity entity : detailMingguEntities) {
                        list.add(model(entity));
                    }
                }
                binding.recyclerView.setAdapter(new Pertanyaan3Adapter(list));
            }
        });

        binding.materialButtonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.deleteDetailMinggu(id);
                for (Pertanyaan3Model model : Pertanyaan3Adapter.getList()) {
                    viewModel.insertDetailMinggu(entity(model));
                }
                viewModel.updateMinggu(id, true);
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private DetailMingguEntity entity(Pertanyaan3Model model) {
        DetailMingguEntity entity = new DetailMingguEntity();
        entity.pertanyaan = model.getPertanyaan();
        entity.mingguKe = id;
        entity.status = model.getStatus();
        entity.keterangan = model.getKeterangan();
        entity.catatan = model.getCatatan();
        return entity;
    }

    private Pertanyaan3Model model(DetailMingguEntity entity) {
        Pertanyaan3Model model = new Pertanyaan3Model(
                entity.pertanyaan,
                entity.status,
                entity.keterangan,
                entity.catatan
        );
        return model;
    }
}