package com.unitedtractors.android.unitedtractorsapp.view.activity.form.sidak_catering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.PertanyaanAdapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKesehatanBahanDanPenyimpananMakananBinding;
import com.unitedtractors.android.unitedtractorsapp.model.PertanyaanModel;

import java.util.ArrayList;
import java.util.List;

public class KesehatanBahanDanPenyimpananMakananActivity extends AppCompatActivity {
    private ActivityKesehatanBahanDanPenyimpananMakananBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKesehatanBahanDanPenyimpananMakananBinding.inflate(getLayoutInflater());
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
        boolean[] ptk = getIntent().getBooleanArrayExtra("PTK");

        List<PertanyaanModel> list = new ArrayList<>();
        list.add(new PertanyaanModel(
                "Apakah daging dan ikan dibeli dalam keadaan segar dan dibeli di tempat resmi?",
                false));
        list.add(new PertanyaanModel(
                "Apakah bahan makanan yang akan diolah dalam keadaan baik, segar, dan tidak rusak ( Berubah Warna dan Rasa )?",
                false));
        list.add(new PertanyaanModel(
                "Apakah makanan kering atau kaleng yang di beli terdaftar di DEPKES?",
                false));
        list.add(new PertanyaanModel(
                "Untuk bahan yang di kemas apakan memenuhi syarat kemasan tidak rusak atau kembung dan belum kadaluarsa?",
                false));
        list.add(new PertanyaanModel(
                "Apakah bahan makanan yang di pergunakan dalam keadaan bersih bebas bakteri dan bahan beracun serta bebas dari lembab?",
                false));
        list.add(new PertanyaanModel(
                "Apakah air yang untuk minum dan cuci peralatan sudah di periksa di laboratorium?",
                false));
        list.add(new PertanyaanModel(
                "Makanan jadi harus dalam kondisi baik dan sehat dan tempat penyimpanan harus terlindung dan memenuhi standart kesehatan.",
                false));
        list.add(new PertanyaanModel(
                "Harus ada pemisahan tempat penyimpanan makanan kering dan basah.",
                false));
        list.add(new PertanyaanModel(
                "Apakah penyimpanan bahan mentah terpisah dengan penyimpanan makanan jadi?",
                false));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new PertanyaanAdapter(list));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SanitasiLingkunganDanFasilitasPengolahMakananActivity.class);
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
                intent.putExtra("PTK", ptk);
                intent.putExtra("KBDPM", getStatus(PertanyaanAdapter.getList()));
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