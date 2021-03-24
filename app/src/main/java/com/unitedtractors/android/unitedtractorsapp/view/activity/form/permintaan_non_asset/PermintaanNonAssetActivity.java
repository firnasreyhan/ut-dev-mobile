package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_non_asset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPermintaanNonAssetBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.pembelian_snack.ListPembelianSnackActivity;

public class PermintaanNonAssetActivity extends AppCompatActivity {
    private ActivityPermintaanNonAssetBinding binding;

    private int jumlahPermintaanNonAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPermintaanNonAssetBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        jumlahPermintaanNonAsset = Integer.parseInt(binding.editTextJumlahPermintaanPermintaanNonAsset.getText().toString());
        binding.materialButtonTambahPermintaanNonAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahPermintaanNonAsset++;
                binding.editTextJumlahPermintaanPermintaanNonAsset.setText(String.valueOf(jumlahPermintaanNonAsset));
                if (jumlahPermintaanNonAsset > 0) {
                    binding.materialButtonSelanjutnya.setEnabled(true);
                    binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.primary));
                }
            }
        });

        binding.materialButtonKurangPermintaanNonAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumlahPermintaanNonAsset > 0) {
                    jumlahPermintaanNonAsset--;
                    binding.editTextJumlahPermintaanPermintaanNonAsset.setText(String.valueOf(jumlahPermintaanNonAsset));
                    if (jumlahPermintaanNonAsset == 0) {
                        binding.materialButtonSelanjutnya.setEnabled(false);
                        binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.button_disable));
                    }
                }
            }
        });

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListPermintaanNonAssetActivity.class);
                intent.putExtra("ID_MAPPING", idMapping);
                intent.putExtra("JUMLAH_PERMINTAAN_NON_ASSET", jumlahPermintaanNonAsset);
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