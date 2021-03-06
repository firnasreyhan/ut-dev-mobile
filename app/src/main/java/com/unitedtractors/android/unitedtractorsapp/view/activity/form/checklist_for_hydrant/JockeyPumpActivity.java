package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_for_hydrant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.Pertanyaan3Adapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityJockeyPumpBinding;
import com.unitedtractors.android.unitedtractorsapp.model.Pertanyaan3Model;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_for_genset.K5Activity;

import java.util.ArrayList;
import java.util.List;

public class JockeyPumpActivity extends AppCompatActivity {
    private ActivityJockeyPumpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJockeyPumpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<Pertanyaan3Model> list = new ArrayList<>();
        list.add(new Pertanyaan3Model(
                "Gate Valve",
                true,
                "*Standard: Air Pancingan selalu pada posisi ‘OPEN’",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Arah Kipas Pompa",
                true,
                "*Standard: Putaran searah dengan arah panah",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Kabel Power Terminal",
                true,
                "*Standard: Posisi OFF pada waktu pemeriksaan",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Motor Pompa",
                true,
                "*Standard: Tidak ada kelainan bunyi motor",
                ""
        ));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new Pertanyaan3Adapter(list));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ElectricPumpActivity.class);
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