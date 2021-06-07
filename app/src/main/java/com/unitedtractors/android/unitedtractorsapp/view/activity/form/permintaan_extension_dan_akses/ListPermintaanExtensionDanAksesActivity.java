package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_extension_dan_akses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.adapter.PermintaanExtensionDanAksesAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.PermintaanMobilDinasAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.PermintaanNonAssetAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListPermintaanExtensionDanAksesBinding;
import com.unitedtractors.android.unitedtractorsapp.model.ExtensionDanAksesModel;
import com.unitedtractors.android.unitedtractorsapp.model.NonAssetModel;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanMobilModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_non_asset.ListPermintaanNonAssetActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.PermintaanExtensionDanAksesViewModel;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.PermintaanNonAssetViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListPermintaanExtensionDanAksesActivity extends AppCompatActivity {
    private ActivityListPermintaanExtensionDanAksesBinding binding;
    private PermintaanExtensionDanAksesViewModel viewModel;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListPermintaanExtensionDanAksesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        int jumlahAkses = getIntent().getIntExtra("JUMLAH_AKSES",0);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(PermintaanExtensionDanAksesViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu sebentar...");
        progressDialog.setCancelable(false);

        List<ExtensionDanAksesModel.DetExtension> list = new ArrayList<>();
        for (int i = 0; i < jumlahAkses; i++) {
            ExtensionDanAksesModel.DetExtension.ContactTo contactTo = new ExtensionDanAksesModel.DetExtension.ContactTo(
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false
            );

            list.add(new ExtensionDanAksesModel.DetExtension(
                    AppPreference.getUser(this).getNamaUsers(),
                    "171111001",
                    "",
                    AppPreference.getUser(this).getDivUsers(),
                    "I",
                    "I",
                    contactTo
            ));
        }

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new PermintaanExtensionDanAksesAdapter(list));

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
                if (checkData()) {
                    progressDialog.show();

                    ExtensionDanAksesModel model = new ExtensionDanAksesModel(
                            AppPreference.getUser(v.getContext()).getIdUsers(),
                            idMapping,
                            PermintaanExtensionDanAksesAdapter.getList()
                    );

                    viewModel.postExtensionDanAkses(
                            model
                    ).observe(ListPermintaanExtensionDanAksesActivity.this, new Observer<BaseResponse>() {
                        @Override
                        public void onChanged(BaseResponse baseResponse) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            if (baseResponse != null) {
                                if (baseResponse.isStatus()) {
                                    startActivity(new Intent(v.getContext(), ScreenFeedbackActivity.class));
                                } else {
                                    new AlertDialog.Builder(v.getContext())
                                            .setTitle("Pesan")
                                            .setMessage(baseResponse.getMessage())
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            })
                                            .create()
                                            .show();
                                }
                            } else {
                                new AlertDialog.Builder(v.getContext())
                                        .setTitle("Pesan")
                                        .setMessage("Terjadi kesalahan pada server, silahkan coba beberapa saat lagi")
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
        for (ExtensionDanAksesModel.DetExtension model : PermintaanExtensionDanAksesAdapter.getList()) {
            if (model.checkData() == false) {
                return false;
            }
        }
        return true;
    }
}