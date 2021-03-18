package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_catering_reguler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityNewPermintaanCateringRegulerBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.pembelian_snack.ListPembelianSnackActivity;

public class NewPermintaanCateringRegulerActivity extends AppCompatActivity {
    private ActivityNewPermintaanCateringRegulerBinding binding;

    private String idMapping;
    private int jumlahPermintaan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewPermintaanCateringRegulerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        idMapping = getIntent().getStringExtra("ID_MAPPING");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        jumlahPermintaan = Integer.parseInt(binding.editTextJumlahPermintaan.getText().toString());

        binding.materialButtonTambahJumlahPermintaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahPermintaan++;
                binding.editTextJumlahPermintaan.setText(String.valueOf(jumlahPermintaan));
                if (jumlahPermintaan > 0) {
                    binding.materialButtonSelanjutnya.setEnabled(true);
                    binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.primary));
                }
            }
        });

        binding.materialButtonKurangJumlahPermintaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumlahPermintaan > 0) {
                    jumlahPermintaan--;
                    binding.editTextJumlahPermintaan.setText(String.valueOf(jumlahPermintaan));
                    if (jumlahPermintaan == 0) {
                        binding.materialButtonSelanjutnya.setEnabled(false);
                        binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.button_disable));
                    }
                }
            }
        });

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NewListPermintaanCateringRegulerActivity.class);
                intent.putExtra("ID_MAPPING", idMapping);
                intent.putExtra("JUMLAH_PERMINTAAN", jumlahPermintaan);
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