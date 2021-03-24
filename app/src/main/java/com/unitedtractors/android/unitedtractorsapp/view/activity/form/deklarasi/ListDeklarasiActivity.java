package com.unitedtractors.android.unitedtractorsapp.view.activity.form.deklarasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.DeklarasiAdapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListDeklarasiBinding;
import com.unitedtractors.android.unitedtractorsapp.model.DeklarasiModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListDeklarasiActivity extends AppCompatActivity {
    private ActivityListDeklarasiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListDeklarasiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String tanggal = getIntent().getStringExtra("TANGGAL");
        String tanggalView = getIntent().getStringExtra("TANGGAL_VIEW");
        String nopol = getIntent().getStringExtra("NOPOL");
        int jumlahDeklarasi = getIntent().getIntExtra("JUMLAH_DEKLARASI", 0);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<DeklarasiModel.DetKeperluan> list = new ArrayList<>();
        for (int i = 0; i < jumlahDeklarasi; i++) {
            list.add(new DeklarasiModel.DetKeperluan(
                    0,
                    0,
                    0,
                    0
            ));
        }

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new DeklarasiAdapter(list));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), KonfirmasiDeklarasiActivity.class);
                intent.putExtra("ID_MAPPING", idMapping);
                intent.putExtra("TANGGAL", tanggal);
                intent.putExtra("TANGGAL_VIEW", tanggalView);
                intent.putExtra("NOPOL", nopol);
                intent.putExtra("LIST_DEKLARASI", (Serializable) DeklarasiAdapter.getList());
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