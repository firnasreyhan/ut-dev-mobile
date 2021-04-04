package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permohonan_pv_rv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.PVRVAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.PermintaanMobilDinasAdapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListPermohonanPVRVBinding;
import com.unitedtractors.android.unitedtractorsapp.model.PVRVModel;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanMobilModel;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas.KonfirmasiPermintaanMobilDinasActivity;

import java.util.ArrayList;
import java.util.List;

public class ListPermohonanPVRVActivity extends AppCompatActivity {
    private ActivityListPermohonanPVRVBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListPermohonanPVRVBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String kota = getIntent().getStringExtra("KOTA");
        String tanggal = getIntent().getStringExtra("TANGGAL");
        String tanggalView = getIntent().getStringExtra("TANGGAL_VIEW");
        String dibayarkanKepada = getIntent().getStringExtra("DIBAYARKAN_KEPADA");
        String nrpKaryawan = getIntent().getStringExtra("NRP_KARYAWAN");
        String keperluan = getIntent().getStringExtra("KEPERLUAN");
        String nomorPo = getIntent().getStringExtra("NOMOR_PO");
        String nomorInvoice = getIntent().getStringExtra("NOMOR_INVOICE");
        String caraPembayaranKode = getIntent().getStringExtra("CARA_PEMBAYARAN_KODE");
        String caraPembayaran = getIntent().getStringExtra("CARA_PEMBAYARAN");
        String noPpn = getIntent().getStringExtra("NO_PPN");
        String noPph = getIntent().getStringExtra("NO_PPH");
        String presentase = getIntent().getStringExtra("PRESENTASE");
        int jumlahPembayaran = getIntent().getIntExtra("JUMLAH_PEMBAYARAN",0);

        Log.e("presentase", String.valueOf(presentase));
        Log.e("carapembayaran", caraPembayaranKode);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<PVRVModel.DetailPVRV> list = new ArrayList<>();
        for (int i = 0; i < jumlahPembayaran; i++) {
            list.add(new PVRVModel.DetailPVRV(
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""));
        }

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new PVRVAdapter(list, true));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), DokumenPVRVActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
                    intent.putExtra("KOTA", kota);
                    intent.putExtra("TANGGAL", tanggal);
                    intent.putExtra("TANGGAL_VIEW", tanggalView);
                    intent.putExtra("DIBAYARKAN_KEPADA", dibayarkanKepada);
                    intent.putExtra("NRP_KARYAWAN", nrpKaryawan);
                    intent.putExtra("KEPERLUAN", keperluan);
                    intent.putExtra("NOMOR_PO", nomorPo);
                    intent.putExtra("NOMOR_INVOICE", nomorInvoice);
                    intent.putExtra("CARA_PEMBAYARAN_KODE", caraPembayaranKode);
                    intent.putExtra("CARA_PEMBAYARAN", caraPembayaran);
                    intent.putExtra("NO_PPN", noPpn);
                    intent.putExtra("NO_PPH", noPph);
                    intent.putExtra("PRESENTASE", presentase);
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
        for (PVRVModel.DetailPVRV model : PVRVAdapter.getList()) {
            if (model.checkData() == false) {
                return false;
            }
        }
        return true;
    }
}