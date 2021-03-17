package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_extension_dan_akses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPermintaanExtensionDanAksesBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas.ListPermintaanMobilDinasActivity;

public class PermintaanExtensionDanAksesActivity extends AppCompatActivity {
    private ActivityPermintaanExtensionDanAksesBinding binding;

    private String idMapping;
    private int jumlahAkses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPermintaanExtensionDanAksesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        jumlahAkses = Integer.parseInt(String.valueOf(binding.editTextJumlahAkses.getText().toString()));

        binding.materialButtonTambahJumlahAkses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahAkses++;
                binding.editTextJumlahAkses.setText(String.valueOf(jumlahAkses));
                if (jumlahAkses > 0) {
                    binding.materialButtonSelanjutnya.setEnabled(true);
                    binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.primary));
                }
            }
        });

        binding.materialButtonKurangJumlahAkses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumlahAkses > 0) {
                    jumlahAkses--;
                    binding.editTextJumlahAkses.setText(String.valueOf(jumlahAkses));
                    if (jumlahAkses == 0) {
                        binding.materialButtonSelanjutnya.setEnabled(false);
                        binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.button_disable));
                    }
                }
            }
        });

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), ListPermintaanMobilDinasActivity.class);
                    intent.putExtra("JUMLAH_AKSES", jumlahAkses);
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

        if (binding.editTextNomorExtension.getText().toString().isEmpty()) {
            binding.editTextNomorExtension.setError("Mohon isi data berikut");
            cek1 = false;
        }

        return cek1;
    }
}