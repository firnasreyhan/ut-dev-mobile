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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListExternalWorkOrderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String diintruksikanKepada = getIntent().getStringExtra("DIINTRUKSIKAN_KEPADA");
        String intruksiDari = getIntent().getStringExtra("INTRUKSI_DARI");
        String pekerjaan = getIntent().getStringExtra("PEKERJAAN");
        String regNo = getIntent().getStringExtra("REG_NO");
        String requestDateView = getIntent().getStringExtra("REQUEST_DATE_VIEW");
        String requestDateServer = getIntent().getStringExtra("REQUEST_DATE_SERVER");
        String pages = getIntent().getStringExtra("PAGES");
        String cc = getIntent().getStringExtra("CC");
        int jumlahPekerjaan = getIntent().getIntExtra("JUMLAH_PEKERJAAN", 0);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<ExternalWorkOrderModel.DetailExternalWorkOrder> list = new ArrayList<>();

        if (jumlahPekerjaan > 0) {
            for (int i = 0; i < jumlahPekerjaan; i++) {
                list.add(new ExternalWorkOrderModel.DetailExternalWorkOrder(
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
        binding.recyclerView.setAdapter(new ExternalWorkOrderAdapter(list, true));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), KonfirmasiExternalWorkOrderActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
                    intent.putExtra("DIINTRUKSIKAN_KEPADA", diintruksikanKepada);
                    intent.putExtra("INTRUKSI_DARI", intruksiDari);
                    intent.putExtra("PEKERJAAN", pekerjaan);
                    intent.putExtra("REG_NO", regNo);
                    intent.putExtra("REQUEST_DATE_VIEW", requestDateView);
                    intent.putExtra("REQUEST_DATE_SERVER", requestDateServer);
                    intent.putExtra("PAGES", pages);
                    intent.putExtra("CC", cc);
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
        for (ExternalWorkOrderModel.DetailExternalWorkOrder model : ExternalWorkOrderAdapter.getList()) {
            if (model.checkData() == false) {
                return false;
            }
        }
        return true;
    }
}