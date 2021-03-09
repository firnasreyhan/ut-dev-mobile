package com.unitedtractors.android.unitedtractorsapp.view.activity.form.sidak_catering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rm.freedrawview.FreeDrawView;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKonfirmasiSidakCateringBinding;
import com.unitedtractors.android.unitedtractorsapp.model.SidakCateringModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.SignUpActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.pembelian_snack.KonfirmasiPembelianSnackActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.KonfirmasiSidakCateringViewModel;

public class KonfirmasiSidakCateringActivity extends AppCompatActivity {
    private ActivityKonfirmasiSidakCateringBinding binding;
    private KonfirmasiSidakCateringViewModel viewModel;
    private Bitmap bitmapTimSidak, bitmapTimCatering;
    private ProgressDialog progressDialog;
    private SidakCateringModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKonfirmasiSidakCateringBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = ViewModelProviders.of(this).get(KonfirmasiSidakCateringViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu sebentar...");
        progressDialog.setCancelable(false);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String perusahaan = getIntent().getStringExtra("PERUSAHAAN");
        String pemilik = getIntent().getStringExtra("PEMILIK");
        String pengurus = getIntent().getStringExtra("PENGURUS");
        String alamat = getIntent().getStringExtra("ALAMAT");
        String telepon = getIntent().getStringExtra("TELEPON");
        String fax = getIntent().getStringExtra("FAX");
        String jumlahTenagaKerja = getIntent().getStringExtra("JUMLAH_TENAGA_KERJA");
        String perusahaanYangDilayani = getIntent().getStringExtra("PERUSAHAAN_YANG_DILAYANI");
        String kandepnaker = getIntent().getStringExtra("KANDEPNAKER");
        String kanwil = getIntent().getStringExtra("KANWIL");
        boolean[] ptk = getIntent().getBooleanArrayExtra("PTK");
        boolean[] kbdpm = getIntent().getBooleanArrayExtra("KBDPM");
        boolean[] sldfpm = getIntent().getBooleanArrayExtra("SLDFPM");

        model = new SidakCateringModel(
                AppPreference.getUser(this).getIdUsers(),
                idMapping,
                perusahaan,
                pemilik,
                pengurus,
                alamat,
                telepon,
                fax,
                jumlahTenagaKerja,
                perusahaanYangDilayani,
                kandepnaker,
                kanwil,
                ptk,
                kbdpm,
                sldfpm,
                binding.editTextCatatan.getText().toString()
        );

        binding.materialButtonHapusTimSidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.freeDrawViewTimSidak.clearDraw();
            }
        });

        binding.materialButtonHapusTimCatering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.freeDrawViewTimCatering.clearDraw();
            }
        });

        binding.materialButtonAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    progressDialog.show();
                    viewModel.postformSidakCatering(
                            model
                    ).observe(KonfirmasiSidakCateringActivity.this, new Observer<BaseResponse>() {
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
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public boolean checkData() {
        boolean cekCatatan = true;

        if (binding.editTextCatatan.getText().toString().isEmpty()) {
            binding.editTextCatatan.setError("Mohon isi data berikut");
            cekCatatan = false;
        }

//        binding.freeDrawViewTimSidak.getDrawScreenshot(new FreeDrawView.DrawCreatorListener() {
//            @Override
//            public void onDrawCreated(Bitmap draw) {
//                Bitmap emptyBitmap = Bitmap.createBitmap(draw.getWidth(), draw.getHeight(), draw.getConfig());
//                if (!draw.sameAs(emptyBitmap)) {
//                    bitmapTimSidak = draw;
//                } else {
//                    Toast.makeText(KonfirmasiSidakCateringActivity.this, "Tanda tangan tim sidak tidak boleh kosong.", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onDrawCreationError() {
//
//            }
//        });
//
//        binding.freeDrawViewTimCatering.getDrawScreenshot(new FreeDrawView.DrawCreatorListener() {
//            @Override
//            public void onDrawCreated(Bitmap draw) {
//                Bitmap emptyBitmap = Bitmap.createBitmap(draw.getWidth(), draw.getHeight(), draw.getConfig());
//                if (!draw.sameAs(emptyBitmap)) {
//                    bitmapTimCatering = draw;
//                } else {
//                    Toast.makeText(KonfirmasiSidakCateringActivity.this, "Tanda tangan tim catering tidak boleh kosong.", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onDrawCreationError() {
//
//            }
//        });

        return cekCatatan;
    }
}