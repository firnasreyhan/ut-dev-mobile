package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_layout_acara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import com.rm.freedrawview.FreeDrawView;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityDrawPermintaanLayoutAcaraBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.SignUpActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.KonfirmasiLayoutAcaraViewModel;

import java.io.ByteArrayOutputStream;

public class DrawPermintaanLayoutAcaraActivity extends AppCompatActivity {
    private ActivityDrawPermintaanLayoutAcaraBinding binding;
    private KonfirmasiLayoutAcaraViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDrawPermintaanLayoutAcaraBinding.inflate(getLayoutInflater());
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

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(KonfirmasiLayoutAcaraViewModel.class);

        binding.materialButtonHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.freeDrawView.clearDraw();
            }
        });

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.freeDrawView.getDrawScreenshot(new FreeDrawView.DrawCreatorListener() {
                    @Override
                    public void onDrawCreated(Bitmap draw) {
                        Bitmap emptyBitmap = Bitmap.createBitmap(draw.getWidth(), draw.getHeight(), draw.getConfig());
                        if (!draw.sameAs(emptyBitmap)) {
//                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                            draw.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
//                            byte[] byteArray = byteArrayOutputStream .toByteArray();

                            Intent intent = new Intent(v.getContext(), KonfirmasiPermintaanLayoutAcaraActivity.class);
                            intent.putExtra("ID_MAPPING", idMapping);
                            intent.putExtra("NAMA_ACARA", namaAcara);
                            intent.putExtra("LOKASI_ACARA", lokasiAcara);
                            intent.putExtra("TANGGAL", tanggal);
                            intent.putExtra("TANGGAL_VIEW", tanggalView);
                            intent.putExtra("JAM", jam);
                            intent.putExtra("JUMLAH_PESERTA", jumlahPeserta);
                            intent.putExtra("BEBAN_BIAYA", bebanBiaya);
                            intent.putExtra("KETERANGAN", keterangan);
                            intent.putExtra("GAMBAR_LAYOUT_ACARA", Uri.fromFile(viewModel.createTempFile(draw)).toString());
                            startActivity(intent);
                        } else {
                            Toast.makeText(DrawPermintaanLayoutAcaraActivity.this, "Gambar layout acara tidak boleh kosong", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onDrawCreationError() {

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