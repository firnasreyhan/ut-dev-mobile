package com.unitedtractors.android.unitedtractorsapp.view.activity.form.material_used_slip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.MaterialUsedSlipAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.PermintaanMobilDinasAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListMaterialUsedSlipBinding;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListPermintaanMobilDinasBinding;
import com.unitedtractors.android.unitedtractorsapp.model.MaterialUsedSlipModel;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanMobilModel;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas.KonfirmasiPermintaanMobilDinasActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.KonfirmasiPermintaanMobilDinasViewModel;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.MaterialUsedSlipViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListMaterialUsedSlipActivity extends AppCompatActivity {
    private ActivityListMaterialUsedSlipBinding binding;
    private List<MaterialUsedSlipModel.DetailMaterialUsedSlipModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListMaterialUsedSlipBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String tglMaterialView = getIntent().getStringExtra("TGL_MATERIAL_VIEW");
        String tglMaterialServer = getIntent().getStringExtra("TGL_MATERIAL_SERVER");
        int banyakBarang = getIntent().getIntExtra("BANYAK_BARANG", 0);

        list = new ArrayList<>();
        for (int i = 0; i < banyakBarang; i++) {
            list.add(new MaterialUsedSlipModel.DetailMaterialUsedSlipModel(
                    "",
                    0,
                    "",
                    ""));
        }

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new MaterialUsedSlipAdapter(list));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), KonfirmasiMaterialUsedSlipActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
                    intent.putExtra("TGL_MATERIAL_VIEW", tglMaterialView);
                    intent.putExtra("TGL_MATERIAL_SERVER", tglMaterialServer);
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
        for (MaterialUsedSlipModel.DetailMaterialUsedSlipModel model : MaterialUsedSlipAdapter.getList()) {
            if (model.checkData() == false) {
                return false;
            }
        }
        return true;
    }
}