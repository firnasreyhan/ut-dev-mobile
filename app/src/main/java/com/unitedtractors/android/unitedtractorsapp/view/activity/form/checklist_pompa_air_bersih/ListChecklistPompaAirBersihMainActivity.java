package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_pompa_air_bersih;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.Pertanyaan3Adapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListChecklistPompaAirBersihMainBinding;
import com.unitedtractors.android.unitedtractorsapp.model.Pertanyaan3Model;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_for_hydrant.PanelHydrantActivity;

import java.util.ArrayList;
import java.util.List;

public class ListChecklistPompaAirBersihMainActivity extends AppCompatActivity {
    private ActivityListChecklistPompaAirBersihMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListChecklistPompaAirBersihMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<Pertanyaan3Model> list = new ArrayList<>();
        list.add(new Pertanyaan3Model(
                "Cek Kondisi Air Dalam Reservoir",
                true,
                "*Standard : Bak Terisi Penuh",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Cek Air Pancingan Pompa",
                true,
                "*Standard : Posisi 'ON' Menyembur",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Cek / Lihat Indicator Lamp",
                true,
                "*Standard : Indicator Lamp ‘OFF’",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Cek / Lihat Tekanan Udara dalam tangki",
                true,
                "*Standard : Posisi pada garis merah",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Cek / lihat flow meter",
                true,
                "*Standard : Catat angka",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Cek Supply air dari PDAM dan air tanah",
                true,
                "*Standard : Flow meter memutar",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Test secara manual supply air sumur",
                true,
                "*Standard : Angkat electroda",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Cek fungsi panel",
                true,
                "*Standard : Indikator lamp posisi 'Off'",
                ""
        ));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new Pertanyaan3Adapter(list));

        binding.materialButtonAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ScreenFeedbackActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}