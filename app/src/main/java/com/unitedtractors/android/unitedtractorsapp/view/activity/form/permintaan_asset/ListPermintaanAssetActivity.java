package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_asset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListPermintaanAssetBinding;
import com.unitedtractors.android.unitedtractorsapp.adapter.PermintaanAssetAdapter;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanAssetModel;

import java.util.ArrayList;
import java.util.List;

public class ListPermintaanAssetActivity extends AppCompatActivity {
    private ActivityListPermintaanAssetBinding binding;

    private List<PermintaanAssetModel> list;
    private int jumlahBarangBaru, jumlahBarangLama;
    private String jenisPermintaan;

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
            jenisPermintaan = "Baru";
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
            jenisPermintaan = "Lama";
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
        binding.recyclerView.setAdapter(new PermintaanAssetAdapter(list, true));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("checkData", String.valueOf(checkData()));
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), KonfirmasiPermintaanAssetActivity.class);
                    intent.putExtra("JENIS_PERMINTAAN", jenisPermintaan);
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

//    private boolean checkData() {
//        for (int i = 0; i < PermintaanAssetAdapter.getList().size(); i++) {
//            for (int j = 0; j < 5; j++) {
//
//            }
//        }
//        return true;
//    }
    private boolean checkData() {
        for (PermintaanAssetModel model : PermintaanAssetAdapter.getList()) {
            if (model.checkData() == false) {
                return false;
            }
        }
        return true;
    }
}