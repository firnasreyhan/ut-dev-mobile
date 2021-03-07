package com.unitedtractors.android.unitedtractorsapp.view.activity.form.sidak_catering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySidakCateringBinding;

public class SidakCateringActivity extends AppCompatActivity {
    private ActivitySidakCateringBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySidakCateringBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), PersyaratanTenagaKerjaActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
                    intent.putExtra("PERUSAHAAN", binding.editTextNamaPerusahaan.getText().toString());
                    intent.putExtra("PEMILIK", binding.editTextNamaPemilik.getText().toString());
                    intent.putExtra("PENGURUS", binding.editTextNamaPengurus.getText().toString());
                    intent.putExtra("ALAMAT", binding.editTextAlamat.getText().toString());
                    intent.putExtra("TELEPON", binding.editTextTelepon.getText().toString());
                    intent.putExtra("FAX", binding.editTextFax.getText().toString());
                    intent.putExtra("JUMLAH_TENAGA_KERJA", binding.editTextJumlahTenagaKerja.getText().toString());
                    intent.putExtra("PERUSAHAAN_YANG_DILAYANI", binding.editTextPerusahaanYangDilayani.getText().toString());
                    intent.putExtra("KANDEPNAKER", binding.editTextKandepnaker.getText().toString());
                    intent.putExtra("KANWIL", binding.editTextKanwil.getText().toString());
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
        boolean cek5 = true;
        boolean cek6 = true;
        boolean cek7 = true;
        boolean cek8 = true;
        boolean cek9 = true;
        boolean cek10 = true;
        boolean cek11 = true;

        if (binding.editTextNamaPerusahaan.getText().toString().isEmpty()) {
            binding.editTextNamaPerusahaan.setError("Mohon isi data berikut");
            cek1 = false;
        }

        if (binding.editTextNamaPemilik.getText().toString().isEmpty()) {
            binding.editTextNamaPemilik.setError("Mohon isi data berikut");
            cek2 = false;
        }

        if (binding.editTextNamaPengurus.getText().toString().isEmpty()) {
            binding.editTextNamaPengurus.setError("Mohon isi data berikut");
            cek3 = false;
        }

        if (binding.editTextAlamat.getText().toString().isEmpty()) {
            binding.editTextAlamat.setError("Mohon isi data berikut");
            cek5 = false;
        }

        if (binding.editTextTelepon.getText().toString().isEmpty()) {
            binding.editTextTelepon.setError("Mohon isi data berikut");
            cek6 = false;
        }

        if (binding.editTextFax.getText().toString().isEmpty()) {
            binding.editTextFax.setError("Mohon isi data berikut");
            cek7 = false;
        }

        if (binding.editTextJumlahTenagaKerja.getText().toString().isEmpty()) {
            binding.editTextJumlahTenagaKerja.setError("Mohon isi data berikut");
            cek8 = false;
        }

        if (binding.editTextPerusahaanYangDilayani.getText().toString().isEmpty()) {
            binding.editTextPerusahaanYangDilayani.setError("Mohon isi data berikut");
            cek9 = false;
        }

        if (binding.editTextKandepnaker.getText().toString().isEmpty()) {
            binding.editTextKandepnaker.setError("Mohon isi data berikut");
            cek10 = false;
        }

        if (binding.editTextKanwil.getText().toString().isEmpty()) {
            binding.editTextKanwil.setError("Mohon isi data berikut");
            cek11 = false;
        }

        return cek1 && cek2 && cek3 && cek5 && cek6 && cek7 && cek8 && cek9 && cek10 && cek11;
    }
}