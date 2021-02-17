package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_asset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPermintaanAssetBinding;

public class PermintaanAssetActivity extends AppCompatActivity {
    private ActivityPermintaanAssetBinding binding;

    private int jumlahBarangBaru, jumlahBarangLama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPermintaanAssetBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        jumlahBarangBaru = Integer.parseInt(binding.editTextJumlahPermintaanBarangBaru.getText().toString());
        jumlahBarangLama = Integer.parseInt(binding.editTextJumlahPenggantiAssetLama.getText().toString());

        binding.materialButtonTambahJumlahPermintaanBarangBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahBarangBaru++;
                binding.editTextJumlahPermintaanBarangBaru.setText(String.valueOf(jumlahBarangBaru));
                if (jumlahBarangBaru > 0) {
                    binding.materialButtonTambahJumlahPenggantiAssetLama.setEnabled(false);
                    binding.materialButtonKurangJumlahPenggantiAssetLama.setSaveEnabled(false);

                    binding.materialButtonTambahJumlahPenggantiAssetLama.setBackgroundColor(getResources().getColor(R.color.button_disable));
                    binding.materialButtonKurangJumlahPenggantiAssetLama.setBackgroundColor(getResources().getColor(R.color.button_disable));

                    binding.materialButtonSelanjutnya.setEnabled(true);
                    binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.primary));
                }
            }
        });

        binding.materialButtonKurangJumlahPermintaanBarangBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumlahBarangBaru > 0) {
                    jumlahBarangBaru--;
                    binding.editTextJumlahPermintaanBarangBaru.setText(String.valueOf(jumlahBarangBaru));
                    if (jumlahBarangBaru == 0) {
                        binding.materialButtonTambahJumlahPenggantiAssetLama.setEnabled(true);
                        binding.materialButtonKurangJumlahPenggantiAssetLama.setSaveEnabled(true);

                        binding.materialButtonTambahJumlahPenggantiAssetLama.setBackgroundColor(getResources().getColor(R.color.primary));
                        binding.materialButtonKurangJumlahPenggantiAssetLama.setBackgroundColor(getResources().getColor(R.color.primary));

                        binding.materialButtonSelanjutnya.setEnabled(false);
                        binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.button_disable));
                    }
                }
            }
        });

        binding.materialButtonTambahJumlahPenggantiAssetLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahBarangLama++;
                binding.editTextJumlahPenggantiAssetLama.setText(String.valueOf(jumlahBarangLama));
                if (jumlahBarangLama > 0) {
                    binding.materialButtonTambahJumlahPermintaanBarangBaru.setEnabled(false);
                    binding.materialButtonKurangJumlahPermintaanBarangBaru.setSaveEnabled(false);

                    binding.materialButtonTambahJumlahPermintaanBarangBaru.setBackgroundColor(getResources().getColor(R.color.button_disable));
                    binding.materialButtonKurangJumlahPermintaanBarangBaru.setBackgroundColor(getResources().getColor(R.color.button_disable));

                    binding.materialButtonSelanjutnya.setEnabled(true);
                    binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.primary));
                }
            }
        });

        binding.materialButtonKurangJumlahPenggantiAssetLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumlahBarangLama > 0) {
                    jumlahBarangLama--;
                    binding.editTextJumlahPenggantiAssetLama.setText(String.valueOf(jumlahBarangLama));
                    if (jumlahBarangLama == 0) {
                        binding.materialButtonTambahJumlahPermintaanBarangBaru.setEnabled(true);
                        binding.materialButtonKurangJumlahPermintaanBarangBaru.setSaveEnabled(true);

                        binding.materialButtonTambahJumlahPermintaanBarangBaru.setBackgroundColor(getResources().getColor(R.color.primary));
                        binding.materialButtonKurangJumlahPermintaanBarangBaru.setBackgroundColor(getResources().getColor(R.color.primary));

                        binding.materialButtonSelanjutnya.setEnabled(false);
                        binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.button_disable));
                    }
                }
            }
        });

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListPermintaanAssetActivity.class);
                intent.putExtra("JUMLAH_BARANG_BARU", jumlahBarangBaru);
                intent.putExtra("JUMLAH_BARANG_LAMA", jumlahBarangLama);
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