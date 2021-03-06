package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_for_genset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.Pertanyaan2Adapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityUnitEngineBinding;
import com.unitedtractors.android.unitedtractorsapp.model.Pertanyaan2Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UnitEngineActivity extends AppCompatActivity {
    private ActivityUnitEngineBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUnitEngineBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String tanggal = getIntent().getStringExtra("TANGGAL");
        String tanggalView = getIntent().getStringExtra("TANGGAL_VIEW");
        String lokasi = getIntent().getStringExtra("LOKASI");
        String engine = getIntent().getStringExtra("ENGINE");
        String engineModel = getIntent().getStringExtra("ENGINE_MODEL");
        String serialNo = getIntent().getStringExtra("SERIAL_NO");
        String genoType = getIntent().getStringExtra("GENO_TYPE");
        String serialNo2 = getIntent().getStringExtra("SERIAL_NO_2");
        String hour_meter = getIntent().getStringExtra("HOUR_METER");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<Pertanyaan2Model> list = new ArrayList<>();
        list.add(new Pertanyaan2Model(
                "Engine Oil",
                1,
                ""
        ));
        list.add(new Pertanyaan2Model(
                "Engine Oil Pressure",
                1,
                ""
        ));
        list.add(new Pertanyaan2Model(
                "Radiator",
                1,
                ""
        ));
        list.add(new Pertanyaan2Model(
                "Radiator Hose",
                1,
                ""
        ));
        list.add(new Pertanyaan2Model(
                "Fan Belt",
                1,
                ""
        ));
        list.add(new Pertanyaan2Model(
                "Battery",
                1,
                ""
        ));
        list.add(new Pertanyaan2Model(
                "Electrolyt (Air Accu)",
                1,
                ""
        ));
        list.add(new Pertanyaan2Model(
                "Starter Motor",
                1,
                ""
        ));
        list.add(new Pertanyaan2Model(
                "Oil Pressure Indicator",
                1,
                ""
        ));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new Pertanyaan2Adapter(list));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ControlSystemActivity.class);
                intent.putExtra("ID_MAPPING", idMapping);
                intent.putExtra("TANGGAL", tanggal);
                intent.putExtra("TANGGAL_VIEW", tanggalView);
                intent.putExtra("LOKASI", lokasi);
                intent.putExtra("ENGINE", engine);
                intent.putExtra("ENGINE_MODEL", engineModel);
                intent.putExtra("SERIAL_NO", serialNo);
                intent.putExtra("GENO_TYPE", genoType);
                intent.putExtra("SERIAL_NO_2", serialNo2);
                intent.putExtra("HOUR_METER", hour_meter);
                intent.putExtra("UNIT_ENGINE", (Serializable) Pertanyaan2Adapter.getList());
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