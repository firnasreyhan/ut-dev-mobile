package com.unitedtractors.android.unitedtractorsapp.view.activity.form.monitoring_lapangan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.MonitoringLapanganAdapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListMonitoringLapanganBinding;
import com.unitedtractors.android.unitedtractorsapp.model.MonitoringLapanganModel;

import java.util.ArrayList;
import java.util.List;

public class ListMonitoringLapanganActivity extends AppCompatActivity {
    private ActivityListMonitoringLapanganBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListMonitoringLapanganBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        int jumlahOrder = getIntent().getIntExtra("JUMLAH_ORDER", 0);

        List<MonitoringLapanganModel> list = new ArrayList<>();
        for (int i = 0; i < jumlahOrder; i++) {
            list.add(new MonitoringLapanganModel("","","0","0","0"));
        }

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new MonitoringLapanganAdapter(list));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), KonfirmasiMonitoringLapanganActivity.class);
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