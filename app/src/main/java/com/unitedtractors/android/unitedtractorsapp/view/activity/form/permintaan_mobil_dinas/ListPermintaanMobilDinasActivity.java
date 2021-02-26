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
import com.unitedtractors.android.unitedtractorsapp.adapter.TujuanPermintaanMobilDinasAdapter;
import com.unitedtractors.android.unitedtractorsapp.model.TujuanMobilDinasModel;

import java.util.ArrayList;
import java.util.List;

public class ListPermintaanMobilDinasActivity extends AppCompatActivity {
    private ActivityTujuanKeperluanBinding binding;

    private List<TujuanMobilDinasModel> list;

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

        String pengemudi = getIntent().getStringExtra("PENGEMUDI");
        String tglPeminjamanView = getIntent().getStringExtra("TGL_PEMINJAMAN_VIEW");
        String tglPeminjamanServer = getIntent().getStringExtra("TGL_PEMINJAMAN_SERVER");
        String tglPengembalianView = getIntent().getStringExtra("TGL_PENGEMBALIAN_VIEW");
        String tglPengembalianServer = getIntent().getStringExtra("TGL_PENGEMBALIAN_SERVER");
        String divisiDepartement = getIntent().getStringExtra("DIVISI_DEPARTEMENT");
        String noPolisi = getIntent().getStringExtra("NO_POLISI");
        String jamBerangkat = getIntent().getStringExtra("JAM_BERANGKAT");
        String jamPulang = getIntent().getStringExtra("JAM_PULANG");
        int jumlahTujuan = getIntent().getIntExtra("JUMLAH_TUJUAN",0);

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
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), KonfirmasiPermintaanMobilDinasActivity.class);
                    intent.putExtra("PENGEMUDI", pengemudi);
                    intent.putExtra("TGL_PEMINJAMAN_VIEW", tglPeminjamanView);
                    intent.putExtra("TGL_PEMINJAMAN_SERVER", tglPeminjamanServer);
                    intent.putExtra("TGL_PENGEMBALIAN_VIEW", tglPengembalianView);
                    intent.putExtra("TGL_PENGEMBALIAN_SERVER", tglPengembalianServer);
                    intent.putExtra("DIVISI_DEPARTEMENT", divisiDepartement);
                    intent.putExtra("NO_POLISI", noPolisi);
                    intent.putExtra("JAM_BERANGKAT", jamBerangkat);
                    intent.putExtra("JAM_PULANG", jamPulang);
                    intent.putExtra("JUMLAH_TUJUAN", jumlahTujuan);
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