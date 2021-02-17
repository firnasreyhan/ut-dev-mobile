package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_asset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListPermintaanAssetBinding;
import com.unitedtractors.android.unitedtractorsapp.view.adapter.PermintaanAssetAdapter;
import com.unitedtractors.android.unitedtractorsapp.view.model.PermintaanAssetModel;

import java.util.ArrayList;
import java.util.List;

public class ListPermintaanAssetActivity extends AppCompatActivity {
    private ActivityListPermintaanAssetBinding binding;

    private List<PermintaanAssetModel> list;
    private int jumlahBarangBaru, jumlahBarangLama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListPermintaanAssetBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        jumlahBarangBaru = getIntent().getIntExtra("JUMLAH_BARANG_BARU",0);
        jumlahBarangLama = getIntent().getIntExtra("JUMLAH_BARANG_LAMA",0);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        list = new ArrayList<>();

        if (jumlahBarangBaru > 0) {
            binding.textViewKeterangan.setText("Permintaan Barang Baru");
            for (int i = 0; i < jumlahBarangBaru; i++) {
                list.add(new PermintaanAssetModel(
                        "",
                        "",
                        "",
                        "",
                        ""));
            }
        }

        if (jumlahBarangLama > 0) {
            binding.textViewKeterangan.setText("Pengganti Asset Lama");
            for (int i = 0; i < jumlahBarangLama; i++) {
                list.add(new PermintaanAssetModel(
                        "",
                        "",
                        "",
                        "",
                        ""));
            }
        }

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);

        PermintaanAssetAdapter adapter = new PermintaanAssetAdapter(list);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}