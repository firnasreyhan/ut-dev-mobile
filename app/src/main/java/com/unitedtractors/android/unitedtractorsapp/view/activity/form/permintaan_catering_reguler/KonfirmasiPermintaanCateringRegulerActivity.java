package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_catering_reguler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKonfirmasiPermintaanCateringRegulerBinding;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanCateringRegulerModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.PermintaanCateringRegulerViewModel;

public class KonfirmasiPermintaanCateringRegulerActivity extends AppCompatActivity {
    private ActivityKonfirmasiPermintaanCateringRegulerBinding binding;
    private PermintaanCateringRegulerViewModel viewModel;
    PermintaanCateringRegulerModel model;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityKonfirmasiPermintaanCateringRegulerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = ViewModelProviders.of(this).get(PermintaanCateringRegulerViewModel.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu Sebentar...");
        progressDialog.setCancelable(false);

        binding.materialButtonAjukan.setEnabled(true);
        binding.materialButtonAjukan.setBackgroundColor(getResources().getColor(R.color.primary));

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String tglCekView = getIntent().getStringExtra("TGL_CATERING_VIEW");
        String tglCekServer = getIntent().getStringExtra("TGL_CATERING_SERVER");
        int jumlahOrang = Integer.parseInt(getIntent().getStringExtra("JUMLAH_ORANG"));

        binding.textViewJumlahOrang.setText(jumlahOrang + "");
        binding.textViewTanggal.setText(tglCekView);

        model = new PermintaanCateringRegulerModel(
                idMapping,
                AppPreference.getUser(this).getIdUsers(),
                jumlahOrang,
                tglCekServer);

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
                progressDialog.setMessage("Mohon tunggu sebentar...");
                progressDialog.show();

                viewModel.postPermintaanCateringReguler(
                        model
                ).observe(KonfirmasiPermintaanCateringRegulerActivity.this, new Observer<BaseResponse>() {
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
            }
        });
    }
}