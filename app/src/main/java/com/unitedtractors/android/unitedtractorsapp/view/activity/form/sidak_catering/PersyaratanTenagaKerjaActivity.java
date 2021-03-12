package com.unitedtractors.android.unitedtractorsapp.view.activity.form.sidak_catering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.PertanyaanAdapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPersyaratanTenagaKerjaBinding;
import com.unitedtractors.android.unitedtractorsapp.model.PertanyaanModel;

import java.util.ArrayList;
import java.util.List;

public class PersyaratanTenagaKerjaActivity extends AppCompatActivity {
    private ActivityPersyaratanTenagaKerjaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersyaratanTenagaKerjaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

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

        List<PertanyaanModel> list = new ArrayList<>();
        list.add(new PertanyaanModel(
                "Apakah semua pegawai perusahaan catering yang mengerjakan dan melayani makanan dan minuman telah bebas dari penyakit menular (Surat Keterangan Dokter)?",
                false));
        list.add(new PertanyaanModel(
                "Apakah sudah dilakukan pemeriksaan paru-paru dengan rontgen?",
                false));
        list.add(new PertanyaanModel(
                "Apakah dilakukan pemeriksaan jasa boga secara berkala?",
                false));
        list.add(new PertanyaanModel(
                "Apakah petugas yang melayani makanan tidak menderita penyakit menular?",
                false));
        list.add(new PertanyaanModel(
                "Apakah pegawai sudah mengikuti pelatihan gizi kerja hygiene dan sanitasi serta penanggulangan keracunanan?",
                false));
        list.add(new PertanyaanModel(
                "Aakah tenaga kerja memakai pakaian kerja dan alat pelindung diri yang sesuai?",
                false));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new PertanyaanAdapter(list));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), KesehatanBahanDanPenyimpananMakananActivity.class);
                intent.putExtra("ID_MAPPING", idMapping);
                intent.putExtra("PERUSAHAAN", perusahaan);
                intent.putExtra("PEMILIK", pemilik);
                intent.putExtra("PENGURUS", pengurus);
                intent.putExtra("ALAMAT", alamat);
                intent.putExtra("TELEPON", telepon);
                intent.putExtra("FAX", fax);
                intent.putExtra("JUMLAH_TENAGA_KERJA", jumlahTenagaKerja);
                intent.putExtra("PERUSAHAAN_YANG_DILAYANI", perusahaanYangDilayani);
                intent.putExtra("KANDEPNAKER", kandepnaker);
                intent.putExtra("KANWIL", kanwil);
                intent.putExtra("PTK", getStatus(PertanyaanAdapter.getList()));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public boolean[] getStatus(List<PertanyaanModel> list) {
        boolean[] booleans = new boolean[list.size()];
        for (int i = 0; i < list.size(); i++) {
            booleans[i] = list.get(i).isStatus();
        }
        return booleans;
    }
}