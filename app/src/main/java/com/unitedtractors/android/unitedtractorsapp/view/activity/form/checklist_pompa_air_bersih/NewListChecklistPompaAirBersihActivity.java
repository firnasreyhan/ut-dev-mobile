package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_pompa_air_bersih;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.Pertanyaan3Adapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityNewListChecklistPompaAirBersihBinding;
import com.unitedtractors.android.unitedtractorsapp.model.Pertanyaan3Model;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;

import java.util.ArrayList;
import java.util.List;

public class NewListChecklistPompaAirBersihActivity extends AppCompatActivity {
    private ActivityNewListChecklistPompaAirBersihBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewListChecklistPompaAirBersihBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String id = String.valueOf(getIntent().getIntExtra("ID", 0));

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<Pertanyaan3Model> list = new ArrayList<>();
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

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new Pertanyaan3Adapter(list));

        binding.materialButtonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}