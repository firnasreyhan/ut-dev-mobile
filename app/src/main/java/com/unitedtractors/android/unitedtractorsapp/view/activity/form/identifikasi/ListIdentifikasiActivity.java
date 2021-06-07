package com.unitedtractors.android.unitedtractorsapp.view.activity.form.identifikasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.IdentifikasiAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.PermintaanAssetAdapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListIdentifikasiBinding;
import com.unitedtractors.android.unitedtractorsapp.model.IdentifikasiModel;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanAssetModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_asset.KonfirmasiPermintaanAssetActivity;

import java.util.ArrayList;
import java.util.List;

public class ListIdentifikasiActivity extends AppCompatActivity {
    private ActivityListIdentifikasiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListIdentifikasiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        int jumlahTemuanLapangan = getIntent().getIntExtra("JUMLAH_TEMUAN_LAPANGAN", 0);
        String idMapping = getIntent().getStringExtra("ID_MAPPING");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<IdentifikasiModel.DetailIdentifikasi> list = new ArrayList<>();

        if (jumlahTemuanLapangan > 0) {
            for (int i = 0; i < jumlahTemuanLapangan; i++) {
                list.add(new IdentifikasiModel.DetailIdentifikasi(
                        "",
                        "",
                        "",
                        "",
                        "",
                        AppPreference.getUser(this).getNamaUsers()
                ));
            }
        }

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new IdentifikasiAdapter(list, true));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("checkData", String.valueOf(checkData()));
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), KonfirmasiIdentifikasiActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
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
        for (IdentifikasiModel.DetailIdentifikasi model : IdentifikasiAdapter.getList()) {
            if (model.checkData() == false) {
                return false;
            }
        }
        return true;
    }
}