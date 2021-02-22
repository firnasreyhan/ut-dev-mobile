package com.unitedtractors.android.unitedtractorsapp.view.activity.form.pembelian_snack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.adapter.PembelianSnackAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.PermintaanAssetAdapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKonfirmasiPembelianSnackBinding;
import com.unitedtractors.android.unitedtractorsapp.model.PembelianSnackModel;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;

import java.util.ArrayList;
import java.util.List;

public class KonfirmasiPembelianSnackActivity extends AppCompatActivity {
    private ActivityKonfirmasiPembelianSnackBinding binding;

    private String divisi, keperluan, serverTime, viewTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKonfirmasiPembelianSnackBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        divisi = getIntent().getStringExtra("DIVISI");
        keperluan = getIntent().getStringExtra("KEPERLUAN");
        serverTime = getIntent().getStringExtra("SERVER_TIME");
        viewTime = getIntent().getStringExtra("VIEW_TIME");

        binding.textViewDivisi.setText(divisi);
        binding.textViewKeperluan.setText(keperluan);
        binding.textViewTanggal.setText(viewTime);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new PembelianSnackAdapter(getData(PembelianSnackAdapter.getList()), false));

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
                startActivity(new Intent(v.getContext(), ScreenFeedbackActivity.class));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private List<PembelianSnackModel> getData(List<PembelianSnackModel> list) {
        List<PembelianSnackModel> data = new ArrayList<>();

        for (PembelianSnackModel model : list) {
            if (model.checkData()) {
              data.add(model);
            }
        }

        return data;
    }
}