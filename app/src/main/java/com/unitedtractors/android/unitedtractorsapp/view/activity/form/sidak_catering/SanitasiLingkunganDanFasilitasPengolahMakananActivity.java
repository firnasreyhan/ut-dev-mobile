package com.unitedtractors.android.unitedtractorsapp.view.activity.form.sidak_catering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.PertanyaanAdapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySanitasiLingkunganDanFasilitasPengolahMakananBinding;
import com.unitedtractors.android.unitedtractorsapp.model.PertanyaanModel;

import java.util.ArrayList;
import java.util.List;

public class SanitasiLingkunganDanFasilitasPengolahMakananActivity extends AppCompatActivity {
    private ActivitySanitasiLingkunganDanFasilitasPengolahMakananBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySanitasiLingkunganDanFasilitasPengolahMakananBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        List<PertanyaanModel> list = new ArrayList<>();
        list.add(new PertanyaanModel(
                "Apakah alat-alat makan atau masak sesudah dipakai selalu dibersihkan dengan sabun dan air panas kemudian dikeringkan?",
                false));
        list.add(new PertanyaanModel(
                "Apakah alat makan dan masak bebas dari karat?",
                false));
        list.add(new PertanyaanModel(
                "Apakah dapur kamar makan dan alat keperluan makan selalu dalam keadaan bersih dan rapih?",
                false));
        list.add(new PertanyaanModel(
                "Apakan tersedia bak sampah tertutup yang di letakkan sedekat mungkin dengan sumber produksi sampah namun dapat menghindari kemungkinan tercemar makanan oleh sampah?",
                false));
        list.add(new PertanyaanModel(
                "Apakah telah tersedia lemari es untuk menyimpan makanan dan bahan makanan yang cepat busuk?",
                false));
        list.add(new PertanyaanModel(
                "Apakah tersedia lemari pendingin yang dapat mencapai suhu -50 C dengan kapasitas cukup memadai sesuai dengan jenis makanan yang di gunakan?",
                false));
        list.add(new PertanyaanModel(
                "Apakah telah tersedia pembuangan asap dapur yang di lengkapi dengan cerobong pembuang asap dapur yang dilengkapi dengan penangkap asap (Hood)?",
                false));
        list.add(new PertanyaanModel(
                "Apakah telah tersedia fasilitas pencucian dari bahan yang kuat permukaan halus dan mudah dibersihkan?",
                false));
        list.add(new PertanyaanModel(
                "Apakah tata ruang dan lingkungan kerja dalam kondisi baik dan terpelihara (Saluran air, Penerangan, Sirkulasi udara, Kamar mandi)?",
                false));
        list.add(new PertanyaanModel(
                "Apakah telah tersedia alat keselamatan kerja seperti untuk kompor gas dan alat pemadam api ringan?",
                false));
        list.add(new PertanyaanModel(
                "Apakah alat listrik Oven,toaster,blender,dan hand mixer dll dalam keadaan baik dan aman?",
                false));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new PertanyaanAdapter(list));
    }
}