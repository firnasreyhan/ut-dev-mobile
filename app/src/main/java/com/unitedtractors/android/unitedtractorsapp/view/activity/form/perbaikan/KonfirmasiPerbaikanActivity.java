package com.unitedtractors.android.unitedtractorsapp.view.activity.form.perbaikan;

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
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKonfimasiPerbaikanBinding;
import com.unitedtractors.android.unitedtractorsapp.model.PerbaikanModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.pembelian_snack.KonfirmasiPembelianSnackActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.KonfirmasiPerbaikanViewModel;

public class KonfirmasiPerbaikanActivity extends AppCompatActivity {
    private ActivityKonfimasiPerbaikanBinding binding;
    private KonfirmasiPerbaikanViewModel viewModel;
    private ProgressDialog progressDialog;
    private PerbaikanModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKonfimasiPerbaikanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = ViewModelProviders.of(this).get(KonfirmasiPerbaikanViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu sebentar...");
        progressDialog.setCancelable(false);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String tanggal = getIntent().getStringExtra("TANGGAL");
        String tanggalView = getIntent().getStringExtra("TANGGAL_VIEW");
        String waktu = getIntent().getStringExtra("WAKTU");
        String namaPemohon = getIntent().getStringExtra("NAMA_PEMOHON");
        String divisi = getIntent().getStringExtra("DIVISI");
        String extension = getIntent().getStringExtra("EXTENSION");
        String namaPenerima = getIntent().getStringExtra("NAMA_PENERIMA");
        String nomorTroubleTicket = getIntent().getStringExtra("NOMOR_TROUBLE_TICKET");
        String jenisPerbaikan = getIntent().getStringExtra("JENIS_PERBAIKAN");
        String alasanPerbaikan = getIntent().getStringExtra("ALASAN_PERBAIKAN");
        int dikerjakanOleh = getIntent().getIntExtra("DIKERJAKAN_OLEH", 0);
        String estimasiWaktu = getIntent().getStringExtra("ESTIMASI_WAKTU");
        String estimasiBiaya = getIntent().getStringExtra("ESTIMASI_BIAYA");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.textViewTanggal.setText(tanggalView);
        binding.textViewWaktu.setText(waktu);
        binding.textViewNamaPemohon.setText(namaPemohon);
        binding.textViewDivisiPemohon.setText(divisi);
        binding.textViewExtensionPemohon.setText(extension);
        binding.textViewNamaPenerima.setText(namaPenerima);
        binding.textViewNomorTroubleTicket.setText(nomorTroubleTicket);
        binding.editTextJenisPerbaikan.setText(jenisPerbaikan);
        binding.editTextAlasanPerbaikan.setText(alasanPerbaikan);

        if (dikerjakanOleh == 1) {
            binding.radioButtonVendor.setChecked(true);
        } else if (dikerjakanOleh == 2) {
            binding.radioButtonMaintenance.setChecked(true);
        }

        binding.editTextEstimasiWaktu.setText(estimasiWaktu);
        binding.editTextEstimasiBiaya.setText(estimasiBiaya);

        model = new PerbaikanModel(
                AppPreference.getUser(this).getIdUsers(),
                idMapping,
                tanggal,
                waktu,
                AppPreference.getUser(this).getNamaUsers(),
                AppPreference.getUser(this).getDivUsers(),
                extension,
                namaPenerima,
                nomorTroubleTicket,
                jenisPerbaikan,
                alasanPerbaikan,
                String.valueOf(dikerjakanOleh),
                estimasiWaktu,
                estimasiBiaya
        );

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
                viewModel.postPerbaikan(
                        model
                ).observe(KonfirmasiPerbaikanActivity.this, new Observer<BaseResponse>() {
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}