package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityTujuanKeperluanBinding;
import com.unitedtractors.android.unitedtractorsapp.view.adapter.TujuanPermintaanMobilDinasAdapter;
import com.unitedtractors.android.unitedtractorsapp.view.model.PermintaanMobilDinasModel;
import com.unitedtractors.android.unitedtractorsapp.view.model.TujuanMobilDinasModel;

import java.util.ArrayList;
import java.util.List;

public class TujuanKeperluanActivity extends AppCompatActivity {
    private ActivityTujuanKeperluanBinding binding;

    PermintaanMobilDinasModel permintaanMobilDinasModel;
    List<TujuanMobilDinasModel> list;
    int jumlahTujuan;

    private String namaPeminjam;
    private String namaPengemudi;
    private String tglPeminjaman;
    private String tglPengembalian;
    private String divisi;
    private String noPolisi;
    private String jamBerangkat;
    private String jamPulang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTujuanKeperluanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        jumlahTujuan = getIntent().getIntExtra("jumlah_tujuan",0);
        namaPeminjam = getIntent().getStringExtra("nama_peminjam");
        namaPengemudi = getIntent().getStringExtra("nama_pengemudi");
        tglPeminjaman = getIntent().getStringExtra("tgl_peminjaman");
        tglPengembalian = getIntent().getStringExtra("tgl_pengembalian");
        divisi = getIntent().getStringExtra("divisi");
        noPolisi = getIntent().getStringExtra("no_pol");
        jamBerangkat = getIntent().getStringExtra("jam_berangkat");
        jamPulang = getIntent().getStringExtra("jam_pulang");

        list = new ArrayList<>();

        if (jumlahTujuan > 0) {
            for (int i = 0; i < jumlahTujuan; i++) {
                list.add(new TujuanMobilDinasModel(
                        "",
                        "",
                        ""));
            }
        }

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new TujuanPermintaanMobilDinasAdapter(list, true));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("checkData", String.valueOf(checkData()));
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), KonfirmasiPermintaanMobilDinasActivity.class);
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
        for (TujuanMobilDinasModel model : TujuanPermintaanMobilDinasAdapter.getList()) {
            if (model.checkData() == false) {
                return false;
            }
        }
        return true;
    }
}