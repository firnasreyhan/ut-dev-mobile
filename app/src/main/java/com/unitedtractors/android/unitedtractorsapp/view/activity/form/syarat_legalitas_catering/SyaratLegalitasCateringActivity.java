package com.unitedtractors.android.unitedtractorsapp.view.activity.form.syarat_legalitas_catering;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.adapter.PertanyaanAdapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySyaratLegalitasCateringBinding;
import com.unitedtractors.android.unitedtractorsapp.model.PertanyaanModel;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;

import java.util.ArrayList;
import java.util.List;

public class SyaratLegalitasCateringActivity extends AppCompatActivity {
    private ActivitySyaratLegalitasCateringBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySyaratLegalitasCateringBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<PertanyaanModel> list = new ArrayList<>();
        list.add(new PertanyaanModel(
                "SIUP dari perindustrian dan pariwisata",
                false
        ));
        list.add(new PertanyaanModel(
                "Tanda daftar jasa boga dari DEPKES",
                false
        ));
        list.add(new PertanyaanModel(
                "Domisili perusahaan dari Kelurahan",
                false
        ));
        list.add(new PertanyaanModel(
                "Tanda daftar perindustrian dari perindustrian dan pariwisata",
                false
        ));
        list.add(new PertanyaanModel(
                "Hasil pemeriksaan dari LAB",
                false
        ));
        list.add(new PertanyaanModel(
                "Surat suku dinas tenaga kerja dan transmigrasi",
                false
        ));

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new PertanyaanAdapter(list));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SyaratLegatitasCateringLanjutanActivity.class);
                intent.putExtra("ID_MAPPING", idMapping);
                intent.putExtra("NAMA_CATERING", binding.editTextNamaCatering.getText().toString());
                intent.putExtra("ALAMAT_CATERING", binding.editTextAlamatCatering.getText().toString());
                intent.putExtra("SYARAT", getStatus(PertanyaanAdapter.getList()));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public boolean[] getStatus(List<PertanyaanModel> list) {
        boolean[] booleans = new boolean[list.size()];
        for (int i = 0; i < list.size(); i++) {
            booleans[i] = list.get(i).isStatus();
        }
        return booleans;
    }
}