package com.unitedtractors.android.unitedtractorsapp.view.activity.form.internal_work_order;

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
import com.unitedtractors.android.unitedtractorsapp.adapter.KebutuhanMaterialAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKonfirmasiInternalWorkOrderBinding;
import com.unitedtractors.android.unitedtractorsapp.model.InternalWorkOrderModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.KonfirmasiInternalWorkOrderViewModel;

public class KonfirmasiInternalWorkOrderActivity extends AppCompatActivity {
    private ActivityKonfirmasiInternalWorkOrderBinding binding;
    private KonfirmasiInternalWorkOrderViewModel viewModel;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKonfirmasiInternalWorkOrderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String nomorTroubleTiket = getIntent().getStringExtra("NOMOR_TROUBLE_TIKET");
        String tanggal = getIntent().getStringExtra("TANGGAL");
        String tanggalView = getIntent().getStringExtra("TANGGAL_VIEW");
        String tanggalMulai = getIntent().getStringExtra("TANGGAL_MULAI");
        String tanggalMulaiView = getIntent().getStringExtra("TANGGAL_MULAI_VIEW");
        String tanggalSelesai = getIntent().getStringExtra("TANGGAL_SELESAI");
        String tanggalSelesaiView = getIntent().getStringExtra("TANGGAL_SELESAI_VIEW");
        String waktuMulai = getIntent().getStringExtra("WAKTU_MULAI");
        String waktuSelesai = getIntent().getStringExtra("WAKTU_SELESAI");
        String extensionPemohon = getIntent().getStringExtra("EXTENSION_PEMOHON");
        String namaPenerima = getIntent().getStringExtra("NAMA_PENERIMA");
        String keterangan = getIntent().getStringExtra("KETERANGAN");
        String alasanPerbaikan = getIntent().getStringExtra("ALASAN_PERBAIKAN");
        int jenisPerbaikan = getIntent().getIntExtra("JENIS_PERBAIKAN", 0);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(KonfirmasiInternalWorkOrderViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu Sebentar...");
        progressDialog.setCancelable(false);

        binding.textViewTroubleTicketNo.setText(nomorTroubleTiket);
        binding.textViewTanggal.setText(tanggalView);
        binding.textViewTangalWaktuMulai.setText(tanggalMulaiView + " / " + waktuMulai);
        binding.textViewTanggalWaktuSelesai.setText(tanggalSelesaiView + " / " + waktuSelesai);

        binding.textViewNamaPemohon.setText(AppPreference.getUser(this).getNamaUsers());
        binding.textViewDivisi.setText(AppPreference.getUser(this).getDivUsers());
        binding.textViewExtension.setText(extensionPemohon);

        binding.textViewNamaPenerimaTugas.setText(namaPenerima);
        String txtJenisPerbiakan = "-";
        InternalWorkOrderModel.JenisPerbaikan perbaikan = new InternalWorkOrderModel.JenisPerbaikan();
        if (jenisPerbaikan == 1) {
            txtJenisPerbiakan = "Penggantian Baru";
            perbaikan.setPengBaru(true);
            perbaikan.setPengSebagian(false);
            perbaikan.setPerbaikan(false);
        } else if (jenisPerbaikan == 2) {
            txtJenisPerbiakan = "Penggantian Sebagian";
            perbaikan.setPengBaru(false);
            perbaikan.setPengSebagian(true);
            perbaikan.setPerbaikan(false);
        } else if (jenisPerbaikan == 3) {
            txtJenisPerbiakan = "Perbaikan / Modifikasi";
            perbaikan.setPengBaru(false);
            perbaikan.setPengSebagian(false);
            perbaikan.setPerbaikan(true);
        }
        binding.textViewJenisPerbaikan.setText(txtJenisPerbiakan);
        binding.textViewKeteranganPerbaikan.setText(keterangan);
        binding.textViewAlasanPerbaikan.setText(alasanPerbaikan);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new KebutuhanMaterialAdapter(KebutuhanMaterialAdapter.getList(), false));

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

                InternalWorkOrderModel model = new InternalWorkOrderModel(
                        AppPreference.getUser(v.getContext()).getIdUsers(),
                        idMapping,
                        nomorTroubleTiket,
                        tanggal,
                        tanggalMulai + " " + waktuMulai,
                        tanggalSelesai + " " + waktuSelesai,
                        AppPreference.getUser(v.getContext()).getNamaUsers(),
                        AppPreference.getUser(v.getContext()).getDivUsers(),
                        extensionPemohon,
                        namaPenerima,
                        perbaikan,
                        keterangan,
                        alasanPerbaikan,
                        KebutuhanMaterialAdapter.getList()
                );

                viewModel.postInternalWorkOrder(
                        model
                ).observe(KonfirmasiInternalWorkOrderActivity.this, new Observer<BaseResponse>() {
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