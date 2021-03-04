package com.unitedtractors.android.unitedtractorsapp.view.activity.form.sidak_catering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

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
    }
}