package com.unitedtractors.android.unitedtractorsapp.view.activity.form.komplain_atau_usulan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKomplainAtauUsulanBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_asset.ListPermintaanAssetActivity;

public class KomplainAtauUsulanActivity extends AppCompatActivity {
    private ActivityKomplainAtauUsulanBinding binding;

    private int jumlahKomplain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKomplainAtauUsulanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        jumlahKomplain = Integer.parseInt(binding.editTextJumlahKomplainAtauUsulan.getText().toString());

        binding.materialButtonTambahJumlahKomplainAtauUsulan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahKomplain++;
                binding.editTextJumlahKomplainAtauUsulan.setText(String.valueOf(jumlahKomplain));
                if (jumlahKomplain > 0) {
                    binding.materialButtonSelanjutnya.setEnabled(true);
                    binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.primary));
                }
            }
        });

        binding.materialButtonKurangJumlahKomplainAtauUsulan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumlahKomplain > 0) {
                    jumlahKomplain--;
                    binding.editTextJumlahKomplainAtauUsulan.setText(String.valueOf(jumlahKomplain));
                    if (jumlahKomplain == 0) {
                        binding.materialButtonSelanjutnya.setEnabled(false);
                        binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.button_disable));
                    }
                }
            }
        });

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListKomplainAtauUsulanActivity.class);
                intent.putExtra("jumlah_komplain", jumlahKomplain);
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