package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permohonan_pv_rv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.config.Configurations;
import com.jaiselrahman.filepicker.model.MediaFile;
import com.unitedtractors.android.unitedtractorsapp.adapter.DokumenPVRVAdapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityDokumenPVRVBinding;

import java.util.ArrayList;
import java.util.List;

public class DokumenPVRVActivity extends AppCompatActivity {
    private final static int FILE_REQUEST_CODE = 1;

    private ActivityDokumenPVRVBinding binding;
    private DokumenPVRVAdapter dokumenPVRVAdapter;

    private ArrayList<MediaFile> mediaFiles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDokumenPVRVBinding.inflate(getLayoutInflater());
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

        Log.e("presentase", String.valueOf(presentase));
        Log.e("cara pembayaran", caraPembayaranKode);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dokumenPVRVAdapter = new DokumenPVRVAdapter(mediaFiles);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(dokumenPVRVAdapter);

        binding.materialCardViewDokumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FilePickerActivity.class);
                intent.putExtra(FilePickerActivity.CONFIGS, new Configurations.Builder()
                        .setSelectedMediaFiles(mediaFiles)
                        .setCheckPermission(true)
                        .setShowFiles(true)
                        .setShowImages(false)
                        .setShowAudios(false)
                        .setShowVideos(false)
                        .setIgnoreNoMedia(true)
                        .enableVideoCapture(false)
                        .enableImageCapture(false)
                        .setIgnoreHiddenFile(false)
                        .setMaxSelection(10)
                        .build());
                startActivityForResult(intent, FILE_REQUEST_CODE);
            }
        });

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaFiles.size() > 0) {
                    Intent intent = new Intent(v.getContext(), KonfirmasiPVRVActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
                    intent.putExtra("KOTA", kota);
                    intent.putExtra("TANGGAL", tanggal);
                    intent.putExtra("TANGGAL_VIEW", tanggalView);
                    intent.putExtra("DIBAYARKAN_KEPADA", dibayarkanKepada);
                    intent.putExtra("NRP_KARYAWAN", nrpKaryawan);
                    intent.putExtra("KEPERLUAN", keperluan);
                    intent.putExtra("NOMOR_PO", nomorPo);
                    intent.putExtra("NOMOR_INVOICE", nomorInvoice);
                    intent.putExtra("CARA_PEMBAYARAN_KODE", caraPembayaranKode);
                    intent.putExtra("CARA_PEMBAYARAN", caraPembayaran);
                    intent.putExtra("NO_PPN", noPpn);
                    intent.putExtra("NO_PPH", noPph);
                    intent.putExtra("PRESENTASE", presentase);
                    intent.putExtra("DOKUMEN", true);
                    startActivity(intent);
                } else {
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("Pesan")
                            .setMessage("Mohon untuk melampirkan dokumen")
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

        binding.materialButtonTidakAda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), KonfirmasiPVRVActivity.class);
                intent.putExtra("ID_MAPPING", idMapping);
                intent.putExtra("KOTA", kota);
                intent.putExtra("TANGGAL", tanggal);
                intent.putExtra("TANGGAL_VIEW", tanggalView);
                intent.putExtra("DIBAYARKAN_KEPADA", dibayarkanKepada);
                intent.putExtra("NRP_KARYAWAN", nrpKaryawan);
                intent.putExtra("KEPERLUAN", keperluan);
                intent.putExtra("NOMOR_PO", nomorPo);
                intent.putExtra("NOMOR_INVOICE", nomorInvoice);
                intent.putExtra("CARA_PEMBAYARAN_KODE", caraPembayaranKode);
                intent.putExtra("CARA_PEMBAYARAN", caraPembayaran);
                intent.putExtra("NO_PPN", noPpn);
                intent.putExtra("NO_PPH", noPph);
                intent.putExtra("PRESENTASE", presentase);
                intent.putExtra("DOKUMEN", false);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_REQUEST_CODE
                && resultCode == RESULT_OK
                && data != null) {
            List<MediaFile> mediaFiles = data.<MediaFile>getParcelableArrayListExtra(FilePickerActivity.MEDIA_FILES);
            if(mediaFiles != null) {
                setMediaFiles(mediaFiles);
            } else {
                Toast.makeText(this, "Image not selected", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setMediaFiles(List<MediaFile> mediaFiles) {
        this.mediaFiles.clear();
        this.mediaFiles.addAll(mediaFiles);
        dokumenPVRVAdapter.notifyDataSetChanged();
        binding.linearLayoutDaftarDokumen.setVisibility(View.VISIBLE);
    }
}