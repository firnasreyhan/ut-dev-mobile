package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permohonan_pv_rv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.adapter.DokumenPVRVAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.PVRVAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.IdTransResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKonfirmasiPVRVBinding;
import com.unitedtractors.android.unitedtractorsapp.model.PVRVModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas.KonfirmasiPermintaanMobilDinasActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.KonfirmasiPVRVViewModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class KonfirmasiPVRVActivity extends AppCompatActivity {
    private ActivityKonfirmasiPVRVBinding binding;
    private KonfirmasiPVRVViewModel viewModel;
    private ProgressDialog progressDialog;

    double presentaseTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKonfirmasiPVRVBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String kota = getIntent().getStringExtra("KOTA");
        String tanggal = getIntent().getStringExtra("TANGGAL");
        String tanggalView = getIntent().getStringExtra("TANGGAL_VIEW");
        String dibayarkanKepada = getIntent().getStringExtra("DIBAYARKAN_KEPADA");
        String nrpKaryawan = getIntent().getStringExtra("NRP_KARYAWAN");
        String keperluan = getIntent().getStringExtra("KEPERLUAN");
        String nomorPo = getIntent().getStringExtra("NOMOR_PO");
        String nomorInvoice = getIntent().getStringExtra("NOMOR_INVOICE");
        String caraPembayaranKode = getIntent().getStringExtra("CARA_PEMBAYARAN_KODE");
        String caraPembayaran = getIntent().getStringExtra("CARA_PEMBAYARAN");
        String noPpn = getIntent().getStringExtra("NO_PPN");
        String noPph = getIntent().getStringExtra("NO_PPH");
        String presentase = getIntent().getStringExtra("PRESENTASE");
        boolean dokumen = getIntent().getBooleanExtra("DOKUMEN", false);

        presentaseTotal = Double.parseDouble(presentase);
        Log.e("presentase", String.valueOf(presentase));
        Log.e("cara pembayaran", caraPembayaranKode);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(KonfirmasiPVRVViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu Sebentar...");
        progressDialog.setCancelable(false);

        binding.textViewKota.setText(kota);
        binding.textViewTanggal.setText(tanggalView);
        binding.textViewDibayarkanKepada.setText(dibayarkanKepada);
        binding.textViewNRPKaryawan.setText(nrpKaryawan);
        binding.textViewKeperluan.setText(keperluan);
        binding.textViewNoPO.setText(nomorPo);
        binding.textViewNoInvoice.setText(nomorInvoice);
        binding.textViewCaraPembayaran.setText(caraPembayaran);

        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###", decimalFormatSymbols);
        binding.textViewTotal.setText("Rp. " + decimalFormat.format(totalAmount()));

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new PVRVAdapter(PVRVAdapter.getList(), false));

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

                PVRVModel model = new PVRVModel(
                        AppPreference.getUser(v.getContext()).getIdUsers(),
                        idMapping,
                        dibayarkanKepada,
                        nrpKaryawan,
                        keperluan,
                        nomorPo,
                        nomorInvoice,
                        String.valueOf(Integer.parseInt(caraPembayaranKode) + 1),
                        noPpn,
                        noPph,
                        String.valueOf(totalAmount()),
                        PVRVAdapter.getList()
                );

                viewModel.postPVRV(
                        model
                ).observe(KonfirmasiPVRVActivity.this, new Observer<IdTransResponse>() {
                    @Override
                    public void onChanged(IdTransResponse idTransResponse) {
                        if (idTransResponse != null) {
                            if (idTransResponse.isStatus()) {
                                if (dokumen) {
                                    postDokumenPVRV(
                                            idTransResponse.getIdTrans(),
                                            1,
                                            DokumenPVRVAdapter.getMediaFiles().size()
                                    );
                                } else {
                                    if (progressDialog.isShowing()) {
                                        progressDialog.dismiss();
                                    }
                                    startActivity(new Intent(KonfirmasiPVRVActivity.this, ScreenFeedbackActivity.class));
                                }
                            } else {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
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
                        } else {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
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

    private double totalAmount() {
        double total = 0;
        for (PVRVModel.DetailPVRV detailPVRV : PVRVAdapter.getList()) {
            total = total + Double.parseDouble(detailPVRV.getAmount());
        }
        return (total + (total * 0.1) +(total * (presentaseTotal / 100)));
    }

    public void postDokumenPVRV(String idTrans, int start, int end) {
        Log.e("oldpath", DokumenPVRVAdapter.getMediaFiles().get(start-1).getUri().getPath());
        String path = DokumenPVRVAdapter.getMediaFiles().get(start-1).getUri().getPath().substring(18);
        Log.e("path", path);
        Log.e("idTrans", idTrans);
        Log.e("idUser", AppPreference.getUser(this).getIdUsers());
        viewModel.postDokumenPVRV(
                AppPreference.getUser(this).getIdUsers(),
                idTrans,
                String.valueOf(start),
                String.valueOf(end),
                "/storage/emulated/0/" + path
        ).observe(KonfirmasiPVRVActivity.this, new Observer<BaseResponse>() {
            @Override
            public void onChanged(BaseResponse baseResponse) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                if (baseResponse != null) {
                    if (baseResponse.isStatus()) {
                        if (start == end) {
                            startActivity(new Intent(KonfirmasiPVRVActivity.this, ScreenFeedbackActivity.class));
                        } else {
                            postDokumenPVRV(idTrans, (start + 1), end);
                        }
                    } else {
                        new AlertDialog.Builder(KonfirmasiPVRVActivity.this)
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
                    new AlertDialog.Builder(KonfirmasiPVRVActivity.this)
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
}