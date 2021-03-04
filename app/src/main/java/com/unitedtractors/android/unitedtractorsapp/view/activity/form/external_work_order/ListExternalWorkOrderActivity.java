package com.unitedtractors.android.unitedtractorsapp.view.activity.form.external_work_order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.ExternalWorkOrderAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.IdentifikasiAdapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListExternalWorkOrderBinding;
import com.unitedtractors.android.unitedtractorsapp.model.ExternalWorkOrderModel;
import com.unitedtractors.android.unitedtractorsapp.model.IdentifikasiModel;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas.ListPermintaanMobilDinasActivity;

import java.util.ArrayList;
import java.util.List;

public class ListExternalWorkOrderActivity extends AppCompatActivity {
    private ActivityListExternalWorkOrderBinding binding;

    private List<ExternalWorkOrderModel> list;
    private int jumlahPekerjaan;
    private String idMapping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListExternalWorkOrderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        jumlahPekerjaan = getIntent().getIntExtra("JUMLAH_PEKERJAAN",0);
        idMapping = getIntent().getStringExtra("ID_MAPPING");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        list = new ArrayList<>();

        if (jumlahPekerjaan > 0) {
            for (int i = 0; i < jumlahPekerjaan; i++) {
                list.add(new ExternalWorkOrderModel(
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                ));
            }
        }

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new ExternalWorkOrderAdapter(list));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        for (ExternalWorkOrderModel model : ExternalWorkOrderAdapter.getList()) {
            if (model.checkData() == false) {
                return false;
            }
        }
        return true;
    }
}