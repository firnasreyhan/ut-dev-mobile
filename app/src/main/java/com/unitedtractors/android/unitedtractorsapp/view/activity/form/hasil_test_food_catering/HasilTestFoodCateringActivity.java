package com.unitedtractors.android.unitedtractorsapp.view.activity.form.hasil_test_food_catering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityHasilTestFoodCateringBinding;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKomplainAtauUsulanBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.komplain_atau_usulan.ListKomplainAtauUsulanActivity;

public class HasilTestFoodCateringActivity extends AppCompatActivity {
    private ActivityHasilTestFoodCateringBinding binding;

    int jumlahCatering;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHasilTestFoodCateringBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        jumlahCatering = Integer.parseInt(binding.editTextJumlahHasilCatering.getText().toString());

        binding.materialButtonTambahJumlahHasilCatering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahCatering++;
                binding.editTextJumlahHasilCatering.setText(String.valueOf(jumlahCatering));
                if (jumlahCatering > 0) {
                    binding.materialButtonSelanjutnya.setEnabled(true);
                    binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.primary));
                }
            }
        });

        binding.materialButtonKurangJumlahHasilCatering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumlahCatering > 0) {
                    jumlahCatering--;
                    binding.editTextJumlahHasilCatering.setText(String.valueOf(jumlahCatering));
                    if (jumlahCatering == 0) {
                        binding.materialButtonSelanjutnya.setEnabled(false);
                        binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.button_disable));
                    }
                }
            }
        });

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListHasilTestFoodCateringActivity.class);
                intent.putExtra("jumlah_catering", jumlahCatering);
                startActivity(intent);
            }
        });
    }
}