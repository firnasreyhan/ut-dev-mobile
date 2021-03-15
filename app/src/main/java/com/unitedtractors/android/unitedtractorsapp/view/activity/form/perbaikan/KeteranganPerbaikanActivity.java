package com.unitedtractors.android.unitedtractorsapp.view.activity.form.perbaikan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKeteranganPerbaikanBinding;

public class KeteranganPerbaikanActivity extends AppCompatActivity {
    private ActivityKeteranganPerbaikanBinding binding;
    private int dikerjakanOleh = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKeteranganPerbaikanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String tanggal = getIntent().getStringExtra("TANGGAL");
        String tanggalView = getIntent().getStringExtra("TANGGAL_VIEW");
        String waktu = getIntent().getStringExtra("WAKTU");
        String extension = getIntent().getStringExtra("EXTENSION");
        String namaPenerima = getIntent().getStringExtra("NAMA_PENERIMA");
        String nomorTroubleTicket = getIntent().getStringExtra("NOMOR_TROUBLE_TICKET");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonVendor:
                        dikerjakanOleh = 1;
                        break;
                    case R.id.radioButtonMaintenance:
                        dikerjakanOleh = 2;
                        break;
                }
            }
        });

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), KonfirmasiPerbaikanActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
                    intent.putExtra("TANGGAL", tanggal);
                    intent.putExtra("TANGGAL_VIEW", tanggalView);
                    intent.putExtra("WAKTU", waktu);
                    intent.putExtra("EXTENSION", extension);
                    intent.putExtra("NAMA_PENERIMA", namaPenerima);
                    intent.putExtra("NOMOR_TROUBLE_TICKET", nomorTroubleTicket);
                    intent.putExtra("JENIS_PERBAIKAN", binding.editTextJenisPerbaikan.getText().toString());
                    intent.putExtra("ALASAN_PERBAIKAN", binding.editTextAlasanPerbaikan.getText().toString());
                    intent.putExtra("DIKERJAKAN_OLEH", dikerjakanOleh);
                    intent.putExtra("ESTIMASI_WAKTU", binding.editTextEstimasiWaktu.getText().toString());
                    intent.putExtra("ESTIMASI_BIAYA", binding.editTextEstimasiBiaya.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private boolean checkData() {
        boolean cek1 = true;
        boolean cek2 = true;
        boolean cek3 = true;
        boolean cek4 = true;

        if (binding.editTextJenisPerbaikan.getText().toString().isEmpty()) {
            binding.editTextJenisPerbaikan.setError("Mohon isi data berikut");
            cek1 = false;
        }

        if (binding.editTextAlasanPerbaikan.getText().toString().isEmpty()) {
            binding.editTextAlasanPerbaikan.setError("Mohon isi data berikut");
            cek2 = false;
        }

        if (binding.editTextEstimasiWaktu.getText().toString().isEmpty()) {
            binding.editTextEstimasiWaktu.setError("Mohon isi data berikut");
            cek3 = false;
        }

        if (binding.editTextEstimasiBiaya.getText().toString().isEmpty()) {
            binding.editTextEstimasiBiaya.setError("Mohon isi data berikut");
            cek4 = false;
        }

        return cek1 && cek2 && cek3 && cek4;
    }
}