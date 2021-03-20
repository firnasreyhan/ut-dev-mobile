package com.unitedtractors.android.unitedtractorsapp.view.activity.form.internal_work_order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.KebutuhanMaterialAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.PermintaanMobilDinasAdapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListInternalWorkOrderBinding;
import com.unitedtractors.android.unitedtractorsapp.model.InternalWorkOrderModel;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanMobilModel;

import java.util.ArrayList;
import java.util.List;

public class ListInternalWorkOrderActivity extends AppCompatActivity {
    private ActivityListInternalWorkOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListInternalWorkOrderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String nomorTroubleTiket = getIntent().getStringExtra("NOMOR_TROUBLE_TIKET");
        String tanggal = getIntent().getStringExtra("TANGGAL");
        String tanggalView = getIntent().getStringExtra("TANGGAL_VIEW");
        String tanggalMulai = getIntent().getStringExtra("TANGGAL_MULAI");
        String tanggalMulaiView = getIntent().getStringExtra("TANGGAL_MULAI_VIEW");
        String tanggalSelesai = getIntent().getStringExtra("TANGGAL_SELESAI");
        String tanggalSelesaiView = getIntent().getStringExtra("TANGGAL_SELESAI_VIEW");
        String waktuMulai = getIntent().getStringExtra("WAKTU_MULAI");
        String waktuSelesai = getIntent().getStringExtra("WAKTU_SELESAI");
        String extensionPemohon = getIntent().getStringExtra("EXTENSION_PEMOHON");
        String namaPenerima = getIntent().getStringExtra("NAMA_PENERIMA");
        String keterangan = getIntent().getStringExtra("KETERANGAN");
        String alasanPerbaikan = getIntent().getStringExtra("ALASAN_PERBAIKAN");
        int jumlahKebutuhan = getIntent().getIntExtra("JUMLAH_KEBUTUHAN", 0);
        int jenisPerbaikan = getIntent().getIntExtra("JENIS_PERBAIKAN", 0);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<InternalWorkOrderModel.DetailKebutuhan> list = new ArrayList<>();
        for (int i = 1; i <= jumlahKebutuhan; i++) {
            list.add(new InternalWorkOrderModel.DetailKebutuhan(
                    "",
                    0,
                    ""
            ));
        }
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new KebutuhanMaterialAdapter(list, true));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), KonfirmasiInternalWorkOrderActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
                    intent.putExtra("NOMOR_TROUBLE_TIKET", nomorTroubleTiket);
                    intent.putExtra("TANGGAL", tanggal);
                    intent.putExtra("TANGGAL_VIEW", tanggalView);
                    intent.putExtra("TANGGAL_MULAI", tanggalMulai);
                    intent.putExtra("TANGGAL_MULAI_VIEW", tanggalMulaiView);
                    intent.putExtra("TANGGAL_SELESAI", tanggalSelesai);
                    intent.putExtra("TANGGAL_SELESAI_VIEW", tanggalSelesaiView);
                    intent.putExtra("WAKTU_MULAI", waktuMulai);
                    intent.putExtra("WAKTU_SELESAI", waktuSelesai);
                    intent.putExtra("EXTENSION_PEMOHON", extensionPemohon);
                    intent.putExtra("NAMA_PENERIMA", namaPenerima);
                    intent.putExtra("JENIS_PERBAIKAN", jenisPerbaikan);
                    intent.putExtra("KETERANGAN", keterangan);
                    intent.putExtra("ALASAN_PERBAIKAN", alasanPerbaikan);
                    startActivity(intent);
                } else {
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("Pesan")
                            .setMessage("Terdapat data yang kosong, mohon untuk diisi")
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
        for (InternalWorkOrderModel.DetailKebutuhan model : KebutuhanMaterialAdapter.getList()) {
            if (model.checkData() == false) {
                return false;
            }
        }
        return true;
    }
}