package com.unitedtractors.android.unitedtractorsapp.view.activity.form.order_catering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKomplainAtauUsulanBinding;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityOrderCateringBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.komplain_atau_usulan.ListKomplainAtauUsulanActivity;

public class OrderCateringActivity extends AppCompatActivity {
    private ActivityOrderCateringBinding binding;

    private int jumlahOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderCateringBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        jumlahOrder = Integer.parseInt(binding.editTextJumlahOrder.getText().toString());

        binding.materialButtonTambahJumlahOrder.setOnClickListener(new View.OnClickListener() {
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
                Intent intent = new Intent(v.getContext(), ListOrderCateringActivity.class);
                intent.putExtra("ID_MAPPING", idMapping);
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