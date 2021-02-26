package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKonfirmasiPermintaanMobilDinasBinding;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.adapter.PermintaanMobilDinasAdapter;


public class KonfirmasiPermintaanMobilDinasActivity extends AppCompatActivity {
    private ActivityKonfirmasiPermintaanMobilDinasBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityKonfirmasiPermintaanMobilDinasBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String pengemudi = getIntent().getStringExtra("PENGEMUDI");
        String tglPeminjamanView = getIntent().getStringExtra("TGL_PEMINJAMAN_VIEW");
        String tglPeminjamanServer = getIntent().getStringExtra("TGL_PEMINJAMAN_SERVER");
        String tglPengembalianView = getIntent().getStringExtra("TGL_PENGEMBALIAN_VIEW");
        String tglPengembalianServer = getIntent().getStringExtra("TGL_PENGEMBALIAN_SERVER");
        String divisiDepartement = getIntent().getStringExtra("DIVISI_DEPARTEMENT");
        String noPolisi = getIntent().getStringExtra("NO_POLISI");
        String jamBerangkat = getIntent().getStringExtra("JAM_BERANGKAT");
        String jamPulang = getIntent().getStringExtra("JAM_PULANG");
        String kmAwal = getIntent().getStringExtra("KM_AWAL");
        String kmAkhir = getIntent().getStringExtra("KM_AKHIR");
        String catatan = getIntent().getStringExtra("CATATAN");
        if (catatan.isEmpty()) {
            catatan = "-";
        }

        binding.textViewPeminjam.setText(AppPreference.getUser(this).getNamaUsers());
        binding.textViewPengemudi.setText(pengemudi);
        binding.textViewTanggalPeminjaman.setText(tglPeminjamanView);
        binding.textViewTanggalPengembalian.setText(tglPengembalianView);
        binding.textViewDivisiDepartement.setText(divisiDepartement);
        binding.textViewNoPolisi.setText(noPolisi);
        binding.textViewJamBerangkat.setText(jamBerangkat);
        binding.textViewJamPulang.setText(jamPulang);
        binding.textViewKMAwal.setText(kmAwal + " KM");
        binding.textViewKMAkhir.setText(kmAkhir + " KM");
        binding.textViewCatatan.setText(catatan);

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
                startActivity(new Intent(v.getContext(), ScreenFeedbackActivity.class));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}