package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_catering_reguler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.ExternalWorkOrderAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.LaporanPerbaikanAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.PermintaanCateringRegulerAdapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityNewListPermintaanCateringRegulerBinding;
import com.unitedtractors.android.unitedtractorsapp.model.CateringRegulerModel;
import com.unitedtractors.android.unitedtractorsapp.model.ExternalWorkOrderModel;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.laporan_perbaikan.KonfirmasiLaporanPerbaikanActivity;

import java.util.ArrayList;
import java.util.List;

public class NewListPermintaanCateringRegulerActivity extends AppCompatActivity {
    private ActivityNewListPermintaanCateringRegulerBinding binding;
    private List<CateringRegulerModel.DetailCatering> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewListPermintaanCateringRegulerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        int jumlahPermintaan = getIntent().getIntExtra("JUMLAH_PERMINTAAN",0);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        list = new ArrayList<>();
        for (int i = 0; i < jumlahPermintaan; i++) {
            list.add(new CateringRegulerModel.DetailCatering(
                    "",
                    ""
            ));
        }

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new PermintaanCateringRegulerAdapter(list, true));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), NewKonfirmasiPermintaanCateringRegulerActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
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
        for (CateringRegulerModel.DetailCatering model : PermintaanCateringRegulerAdapter.getList()) {
            if (model.checkData() == false) {
                return false;
            }
        }
        return true;
    }
}