package com.unitedtractors.android.unitedtractorsapp.view.activity.form.identifikasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityIdentifikasiBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.pembelian_snack.ListPembelianSnackActivity;

public class IdentifikasiActivity extends AppCompatActivity {
    private ActivityIdentifikasiBinding binding;

    private String idMapping;
    private int jumlahTemuanLapangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIdentifikasiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        idMapping = getIntent().getStringExtra("ID_MAPPING");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        jumlahTemuanLapangan = Integer.parseInt(binding.editTextJumlahTemuanLapangan.getText().toString());

        binding.materialButtonTambahJumlahTemuanLapangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahTemuanLapangan++;
                binding.editTextJumlahTemuanLapangan.setText(String.valueOf(jumlahTemuanLapangan));
                if (jumlahTemuanLapangan > 0) {
                    binding.materialButtonSelanjutnya.setEnabled(true);
                    binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.primary));
                }
            }
        });

        binding.materialButtonKurangJumlahTemuanLapangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumlahTemuanLapangan > 0) {
                    jumlahTemuanLapangan--;
                    binding.editTextJumlahTemuanLapangan.setText(String.valueOf(jumlahTemuanLapangan));
                    if (jumlahTemuanLapangan == 0) {
                        binding.materialButtonSelanjutnya.setEnabled(false);
                        binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.button_disable));
                    }
                }
            }
        });

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListIdentifikasiActivity.class);
                intent.putExtra("JUMLAH_TEMUAN_LAPANGAN", jumlahTemuanLapangan);
                intent.putExtra("ID_MAPPING", idMapping);
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