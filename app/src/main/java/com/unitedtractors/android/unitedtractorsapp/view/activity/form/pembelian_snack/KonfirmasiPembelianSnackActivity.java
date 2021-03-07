package com.unitedtractors.android.unitedtractorsapp.view.activity.form.pembelian_snack;

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
import com.unitedtractors.android.unitedtractorsapp.adapter.PembelianSnackAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.PermintaanAssetAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKonfirmasiPembelianSnackBinding;
import com.unitedtractors.android.unitedtractorsapp.model.PembelianSnackModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.KonfirmasiPembelianSnackViewModel;

import java.util.ArrayList;
import java.util.List;

public class KonfirmasiPembelianSnackActivity extends AppCompatActivity {
    private ActivityKonfirmasiPembelianSnackBinding binding;
    private KonfirmasiPembelianSnackViewModel viewModel;
    private ProgressDialog progressDialog;

    private PembelianSnackModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKonfirmasiPembelianSnackBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = ViewModelProviders.of(this).get(KonfirmasiPembelianSnackViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu sebentar...");
        progressDialog.setCancelable(false);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String divisi = getIntent().getStringExtra("DIVISI");
        String keperluan = getIntent().getStringExtra("KEPERLUAN");
        String serverTime = getIntent().getStringExtra("SERVER_TIME");
        String viewTime = getIntent().getStringExtra("VIEW_TIME");
        String idMapping = getIntent().getStringExtra("ID_MAPPING");

        binding.textViewDivisi.setText(divisi);
        binding.textViewKeperluan.setText(keperluan);
        binding.textViewTanggal.setText(viewTime);

        List<PembelianSnackModel.DetailPembelianSnackModel> list = getData(PembelianSnackAdapter.getList());

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new PembelianSnackAdapter(list, false));

        model = new PembelianSnackModel();
        model.setIdUser(AppPreference.getUser(this).getIdUsers());
        model.setIdMapping(idMapping);
        model.setTglSnack(serverTime);
        model.setDivisiSnack(divisi);
        model.setKeperluanSnack(keperluan);
        model.setDetSnack(list);

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
                progressDialog.show();
                viewModel.postPembelianSnack(
                        model
                ).observe(KonfirmasiPembelianSnackActivity.this, new Observer<BaseResponse>() {
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
                                    .setMessage("Terjadi kesalah pada server, silahkan coba beberapa saat lagi")
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
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private List<PembelianSnackModel.DetailPembelianSnackModel> getData(List<PembelianSnackModel.DetailPembelianSnackModel> list) {
        List<PembelianSnackModel.DetailPembelianSnackModel> data = new ArrayList<>();

        for (PembelianSnackModel.DetailPembelianSnackModel model : list) {
            if (model.checkData()) {
              data.add(model);
            }
        }

        return data;
    }
}