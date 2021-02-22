package com.unitedtractors.android.unitedtractorsapp.view.activity.form.hasil_test_food_catering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListHasilTestFoodCateringBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.view.adapter.HasilTestFoodCateringAdapter;
import com.unitedtractors.android.unitedtractorsapp.view.adapter.KomplainAtauUsulanAdapter;
import com.unitedtractors.android.unitedtractorsapp.view.model.HasilTestFoodCateringModel;
import com.unitedtractors.android.unitedtractorsapp.view.model.KomplainAtauUsulanModel;

import java.util.ArrayList;
import java.util.List;

public class ListHasilTestFoodCateringActivity extends AppCompatActivity {

    private ActivityListHasilTestFoodCateringBinding binding;

    int jumlahCatering;
    List<HasilTestFoodCateringModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListHasilTestFoodCateringBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        jumlahCatering = getIntent().getIntExtra("jumlah_catering", 0);

        list = new ArrayList<>();

        if (jumlahCatering > 0) {
            for (int i = 0; i < jumlahCatering; i++) {
                list.add(new HasilTestFoodCateringModel(
                        "",
                        0,
                        0,
                        0,
                        0
                        ));
            }
        }

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new HasilTestFoodCateringAdapter(list, true));

        binding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    binding.materialButtonAjukan.setEnabled(true);
                    binding.materialButtonAjukan.setBackgroundColor(getResources().getColor(R.color.primary));
                } else {
                    binding.materialButtonAjukan.setEnabled(false);
                    binding.materialButtonAjukan.setBackgroundColor(getResources().getColor(R.color.button_disable));
                }
            }
        });

        binding.materialButtonAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("checkData", String.valueOf(checkData()));
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), ScreenFeedbackActivity.class);
                    startActivity(intent);
                } else {
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("Pesan")
                            .setMessage("Terdapat data yang kosong, mohon untuk diisi.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create()
                            .show();
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
        for (HasilTestFoodCateringModel model : HasilTestFoodCateringAdapter.getList()) {
            if (model.checkData() == false) {
                return false;
            }
        }
        return true;
    }
}