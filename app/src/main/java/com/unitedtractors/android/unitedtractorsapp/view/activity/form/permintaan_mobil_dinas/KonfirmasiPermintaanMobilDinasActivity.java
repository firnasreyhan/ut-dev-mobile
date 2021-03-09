package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CompoundButton;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.PostMobilResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKonfirmasiPermintaanMobilDinasBinding;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanMobilModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.adapter.PermintaanMobilDinasAdapter;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_pribadi.KonfirmasiPermintaanMobilPribadiActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.KonfirmasiPermintaanMobilDinasViewModel;


public class KonfirmasiPermintaanMobilDinasActivity extends AppCompatActivity {
    private ActivityKonfirmasiPermintaanMobilDinasBinding binding;
    private KonfirmasiPermintaanMobilDinasViewModel viewModel;
    private PermintaanMobilModel model;
    private ProgressDialog progressDialog;

    private final int REQUEST_IMAGE_CAPTURE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityKonfirmasiPermintaanMobilDinasBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = ViewModelProviders.of(this).get(KonfirmasiPermintaanMobilDinasViewModel.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu Sebentar...");
        progressDialog.setCancelable(false);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String pengemudi = getIntent().getStringExtra("PENGEMUDI");
        String tglPeminjamanView = getIntent().getStringExtra("TGL_PEMINJAMAN_VIEW");
        String tglPeminjamanServer = getIntent().getStringExtra("TGL_PEMINJAMAN_SERVER");
        String tglPengembalianView = getIntent().getStringExtra("TGL_PENGEMBALIAN_VIEW");
        String tglPengembalianServer = getIntent().getStringExtra("TGL_PENGEMBALIAN_SERVER");
//        String divisiDepartement = getIntent().getStringExtra("DIVISI_DEPARTEMENT");
//        String noPolisi = getIntent().getStringExtra("NO_POLISI");
        String jamBerangkat = getIntent().getStringExtra("JAM_BERANGKAT");
        String jamPulang = getIntent().getStringExtra("JAM_PULANG");
//        String kmAwal = getIntent().getStringExtra("KM_AWAL");
//        String kmAkhir = getIntent().getStringExtra("KM_AKHIR");
        String catatan = getIntent().getStringExtra("CATATAN");
        Uri imgSIM = Uri.parse(getIntent().getStringExtra("IMG_SIM"));
        if (catatan.isEmpty()) {
            catatan = "-";
        }

        model = new PermintaanMobilModel(
                AppPreference.getUser(this).getIdUsers(),
                idMapping,
                AppPreference.getUser(this).getNamaUsers(),
                pengemudi,
                tglPeminjamanServer,
                tglPengembalianServer,
                AppPreference.getUser(this).getDivUsers(),
                "-",
                jamBerangkat,
                jamPulang,
                "-",
                "-",
                catatan,
                PermintaanMobilDinasAdapter.getList()
        );

        binding.textViewPeminjam.setText(AppPreference.getUser(this).getNamaUsers());
        binding.textViewPengemudi.setText(pengemudi);
        binding.textViewTanggalPeminjaman.setText(tglPeminjamanView);
        binding.textViewTanggalPengembalian.setText(tglPengembalianView);
//        binding.textViewDivisiDepartement.setText(divisiDepartement);
//        binding.textViewNoPolisi.setText(noPolisi);
        binding.textViewJamBerangkat.setText(jamBerangkat);
        binding.textViewJamPulang.setText(jamPulang);
//        binding.textViewKMAwal.setText(kmAwal + " KM");
//        binding.textViewKMAkhir.setText(kmAkhir + " KM");
        binding.textViewCatatan.setText(catatan);
        binding.imageViewSIM.setImageURI(imgSIM);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new PermintaanMobilDinasAdapter(PermintaanMobilDinasAdapter.getList(), false));

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
                viewModel.postPermintaanMobilDinas(
                        model
                ).observe(KonfirmasiPermintaanMobilDinasActivity.this, new Observer<PostMobilResponse>() {
                    @Override
                    public void onChanged(PostMobilResponse baseResponse) {
                        if (baseResponse != null) {
                            if (baseResponse.isStatus()) {
                                viewModel.postUploadSim(
                                        AppPreference.getUser(KonfirmasiPermintaanMobilDinasActivity.this).getIdUsers(),
                                        baseResponse.getIdTrans(),
                                        imgSIM
                                ).observe(KonfirmasiPermintaanMobilDinasActivity.this, new Observer<BaseResponse>() {
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
}