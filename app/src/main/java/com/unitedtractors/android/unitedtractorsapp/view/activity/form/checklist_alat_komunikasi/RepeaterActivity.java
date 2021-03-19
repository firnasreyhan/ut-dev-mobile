package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_alat_komunikasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.ChecklistAlatKomunikasiAdapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityRepeaterBinding;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistAlatKomunikasiModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepeaterActivity extends AppCompatActivity {
    private ActivityRepeaterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRepeaterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String tgl = getIntent().getStringExtra("TGL");
        String lokasi = getIntent().getStringExtra("LOKASI");
        List<ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi> pabx = (List<ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi>) getIntent().getSerializableExtra("PABX");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi> list = new ArrayList<>();
        list.add(new ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi(
                "Power Supply",
                1
        ));
        list.add(new ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi(
                "Antena",
                1
        ));
        list.add(new ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi(
                "Kabel Coaxial",
                1
        ));
        list.add(new ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi(
                "Repeater",
                1
        ));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new ChecklistAlatKomunikasiAdapter(list));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RadioActivity.class);
                intent.putExtra("ID_MAPPING", idMapping);
                intent.putExtra("TGL", tgl);
                intent.putExtra("LOKASI", lokasi);
                intent.putExtra("PABX", (Serializable) pabx);
                intent.putExtra("REPEATER", (Serializable) ChecklistAlatKomunikasiAdapter.getList());
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