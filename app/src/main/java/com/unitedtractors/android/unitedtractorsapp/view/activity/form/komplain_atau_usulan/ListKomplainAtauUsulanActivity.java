package com.unitedtractors.android.unitedtractorsapp.view.activity.form.komplain_atau_usulan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.adapter.PermintaanAssetAdapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListKomplainAtauUsulanBinding;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanAssetModel;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_asset.KonfirmasiPermintaanAssetActivity;
import com.unitedtractors.android.unitedtractorsapp.view.adapter.KomplainAtauUsulanAdapter;
import com.unitedtractors.android.unitedtractorsapp.view.model.KomplainAtauUsulanModel;

import java.util.ArrayList;
import java.util.List;

public class ListKomplainAtauUsulanActivity extends AppCompatActivity {
    private ActivityListKomplainAtauUsulanBinding binding;

    List<KomplainAtauUsulanModel> list;
    int jumlahKomplain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListKomplainAtauUsulanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        jumlahKomplain = getIntent().getIntExtra("jumlah_komplain", 0);
        list = new ArrayList<>();

        if (jumlahKomplain > 0) {
            for (int i = 0; i < jumlahKomplain; i++) {
                list.add(new KomplainAtauUsulanModel(
                        "",
                        "",
                        ""));
            }
        }

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new KomplainAtauUsulanAdapter(list, true));

        binding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    binding.materialButtonAjukan.setEnabled(true);
                    binding.materialButtonAjukan.setBackgroundColor(getResources().getColor(R.color.primary));
                } else {
                    binding.materialButtonAjukan.setEnabled(false);
                    binding.materialButtonAjukan.setBackgroundColor(getResources().getColor(R.color.button_disable));
                }
            }
        });

        binding.materialButtonAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("checkData", String.valueOf(checkData()));
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
        for (KomplainAtauUsulanModel model : KomplainAtauUsulanAdapter.getList()) {
            if (model.checkData() == false) {
                return false;
            }
        }
        return true;
    }
}