package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_pribadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKonfirmasiPermintaanMobilPribadiBinding;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;

public class KonfirmasiPermintaanMobilPribadiActivity extends AppCompatActivity {
    private ActivityKonfirmasiPermintaanMobilPribadiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKonfirmasiPermintaanMobilPribadiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

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
        String jamBerangkat = getIntent().getStringExtra("JAM_BERANGKAT");
        String jamPulang = getIntent().getStringExtra("JAM_PULANG");
        Uri imgSIM = Uri.parse(getIntent().getStringExtra("IMG_SIM"));

        Log.e("uri", getIntent().getStringExtra("IMG_SIM"));

        binding.textViewPeminjam.setText(AppPreference.getUser(this).getNamaUsers());
        binding.textViewPengemudi.setText(pengemudi);
        binding.textViewTanggalPeminjaman.setText(tglPeminjamanView);
        binding.textViewTanggalPengembalian.setText(tglPengembalianView);
        binding.textViewDivisiDepartement.setText(AppPreference.getUser(this).getDivUsers());
        binding.textViewJamBerangkat.setText(jamBerangkat);
        binding.textViewJamPulang.setText(jamPulang);
        binding.imageViewSIM.setImageURI(imgSIM);

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