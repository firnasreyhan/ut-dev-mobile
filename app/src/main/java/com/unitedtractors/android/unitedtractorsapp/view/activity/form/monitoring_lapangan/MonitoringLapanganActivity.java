package com.unitedtractors.android.unitedtractorsapp.view.activity.form.monitoring_lapangan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityMonitoringLapanganBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.pembelian_snack.ListPembelianSnackActivity;

public class MonitoringLapanganActivity extends AppCompatActivity {
    private ActivityMonitoringLapanganBinding binding;

    private int jumlahOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMonitoringLapanganBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        jumlahOrder = Integer.parseInt(binding.editTextJumlahOrder.getText().toString());

        binding.materialButtonTambahOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahOrder++;
                binding.editTextJumlahOrder.setText(String.valueOf(jumlahOrder));
                if (jumlahOrder > 0) {
                    binding.materialButtonSelanjutnya.setEnabled(true);
                    binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.primary));
                }
            }
        });

        binding.materialButtonKurangJumlahOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumlahOrder > 0) {
                    jumlahOrder--;
                    binding.editTextJumlahOrder.setText(String.valueOf(jumlahOrder));
                    if (jumlahOrder == 0) {
                        binding.materialButtonSelanjutnya.setEnabled(false);
                        binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.button_disable));
                    }
                }
            }
        });

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListPembelianSnackActivity.class);
                intent.putExtra("JUMLAH_ORDER", jumlahOrder);
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