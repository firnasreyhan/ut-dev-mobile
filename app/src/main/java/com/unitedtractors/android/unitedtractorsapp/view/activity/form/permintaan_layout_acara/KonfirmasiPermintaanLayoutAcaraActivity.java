package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_layout_acara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.CompoundButton;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.IdTransResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKonfirmasiPermintaanLayoutAcaraBinding;
import com.unitedtractors.android.unitedtractorsapp.model.LayoutAcaraModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.KonfirmasiLayoutAcaraViewModel;

import java.io.File;

public class KonfirmasiPermintaanLayoutAcaraActivity extends AppCompatActivity {
    private ActivityKonfirmasiPermintaanLayoutAcaraBinding binding;
    private KonfirmasiLayoutAcaraViewModel viewModel;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKonfirmasiPermintaanLayoutAcaraBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String namaAcara = getIntent().getStringExtra("NAMA_ACARA");
        String lokasiAcara = getIntent().getStringExtra("LOKASI_ACARA");
        String tanggal = getIntent().getStringExtra("TANGGAL");
        String tanggalView = getIntent().getStringExtra("TANGGAL_VIEW");
        String jam = getIntent().getStringExtra("JAM");
        String jumlahPeserta = getIntent().getStringExtra("JUMLAH_PESERTA");
        String bebanBiaya = getIntent().getStringExtra("BEBAN_BIAYA");
        String keterangan = getIntent().getStringExtra("KETERANGAN");
        Uri gambarLayoutAcara = Uri.parse(getIntent().getStringExtra("GAMBAR_LAYOUT_ACARA"));

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(KonfirmasiLayoutAcaraViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu Sebentar...");
        progressDialog.setCancelable(false);

        binding.textViewNamaAcara.setText(namaAcara);
        binding.textViewLokasiAcara.setText(lokasiAcara);
        binding.textViewTanggal.setText(tanggalView);
        binding.textViewJam.setText(jam);
        binding.textViewJumlahPeserta.setText(jumlahPeserta);
        binding.textViewBebanBiaya.setText(bebanBiaya);
        binding.textViewKeterangan.setText(keterangan);
        binding.imageViewGambarDesainLayoutAcara.setImageURI(gambarLayoutAcara);

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

                LayoutAcaraModel model = new LayoutAcaraModel(
                        AppPreference.getUser(v.getContext()).getIdUsers(),
                        idMapping,
                        namaAcara,
                        lokasiAcara,
                        jam,
                        tanggal,
                        jumlahPeserta,
                        bebanBiaya,
                        keterangan.isEmpty() ? "-" : keterangan
                );

                viewModel.postLayoutAcara(
                        model
                ).observe(KonfirmasiPermintaanLayoutAcaraActivity.this, new Observer<IdTransResponse>() {
                    @Override
                    public void onChanged(IdTransResponse baseResponse) {
                        if (baseResponse != null) {
                            if (baseResponse.isStatus()) {
                                viewModel.postGambarLayoutAcara(
                                        AppPreference.getUser(KonfirmasiPermintaanLayoutAcaraActivity.this).getIdUsers(),
                                        baseResponse.getIdTrans(),
                                        new File(gambarLayoutAcara.getPath())
                                ).observe(KonfirmasiPermintaanLayoutAcaraActivity.this, new Observer<BaseResponse>() {
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