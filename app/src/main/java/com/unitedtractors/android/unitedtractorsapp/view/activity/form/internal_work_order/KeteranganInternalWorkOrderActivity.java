package com.unitedtractors.android.unitedtractorsapp.view.activity.form.internal_work_order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKeteranganInternalWorkOrderBinding;

public class KeteranganInternalWorkOrderActivity extends AppCompatActivity {
    private ActivityKeteranganInternalWorkOrderBinding binding;

    private int jenisPerbaikan = 1;
    private int jumlahKebutuhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKeteranganInternalWorkOrderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String nomorTroubleTiket = getIntent().getStringExtra("NOMOR_TROUBLE_TIKET");
        String tanggal = getIntent().getStringExtra("TANGGAL");
        String tanggalView = getIntent().getStringExtra("TANGGAL_VIEW");
        String tanggalMulai = getIntent().getStringExtra("TANGGAL_MULAI");
        String tanggalMulaiView = getIntent().getStringExtra("TANGGAL_MULAI_VIEW");
        String tanggalSelesai = getIntent().getStringExtra("TANGGAL_SELESAI");
        String tanggalSelesaiView = getIntent().getStringExtra("TANGGAL_SELESAI_VIEW");
        String waktuMulai = getIntent().getStringExtra("WAKTU_MULAI");
        String waktuSelesai = getIntent().getStringExtra("WAKTU_SELESAI");
        String extensionPemohon = getIntent().getStringExtra("EXTENSION_PEMOHON");
        String namaPenerima = getIntent().getStringExtra("NAMA_PENERIMA");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonPenggantianBaru:
                        jenisPerbaikan = 1;
                        break;
                    case R.id.radioButtonPenggantianSebagian:
                        jenisPerbaikan = 2;
                        break;
                    case R.id.radioButtonPerbaikanModifikasi:
                        jenisPerbaikan = 3;
                        break;
                }
            }
        });

        jumlahKebutuhan = Integer.parseInt(String.valueOf(binding.editTextJumlahKebutuhan.getText().toString()));

        binding.materialButtonTambahJumlahKebutuhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahKebutuhan++;
                binding.editTextJumlahKebutuhan.setText(String.valueOf(jumlahKebutuhan));
                if (jumlahKebutuhan > 0) {
                    binding.materialButtonSelanjutnya.setEnabled(true);
                    binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.primary));
                }
            }
        });

        binding.materialButtonKurangJumlahKebutuhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumlahKebutuhan > 0) {
                    jumlahKebutuhan--;
                    binding.editTextJumlahKebutuhan.setText(String.valueOf(jumlahKebutuhan));
                    if (jumlahKebutuhan == 0) {
                        binding.materialButtonSelanjutnya.setEnabled(false);
                        binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.button_disable));
                    }
                }
            }
        });

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), ListInternalWorkOrderActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
                    intent.putExtra("NOMOR_TROUBLE_TIKET", nomorTroubleTiket);
                    intent.putExtra("TANGGAL", tanggal);
                    intent.putExtra("TANGGAL_VIEW", tanggalView);
                    intent.putExtra("TANGGAL_MULAI", tanggalMulai);
                    intent.putExtra("TANGGAL_MULAI_VIEW", tanggalMulaiView);
                    intent.putExtra("TANGGAL_SELESAI", tanggalSelesai);
                    intent.putExtra("TANGGAL_SELESAI_VIEW", tanggalSelesaiView);
                    intent.putExtra("WAKTU_MULAI", waktuMulai);
                    intent.putExtra("WAKTU_SELESAI", waktuSelesai);
                    intent.putExtra("EXTENSION_PEMOHON", extensionPemohon);
                    intent.putExtra("NAMA_PENERIMA", namaPenerima);
                    intent.putExtra("JENIS_PERBAIKAN", jenisPerbaikan);
                    intent.putExtra("KETERANGAN", binding.editTextKeterangan.getText().toString());
                    intent.putExtra("ALASAN_PERBAIKAN", binding.editTextAlasanPerbaikan.getText().toString());
                    intent.putExtra("JUMLAH_KEBUTUHAN", jumlahKebutuhan);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private boolean checkData() {
        boolean cek1 = true;
        boolean cek2 = true;

        if (binding.editTextKeterangan.getText().toString().isEmpty()) {
            binding.editTextKeterangan.setError("Mohon isi data berikut");
            cek1 = false;
        }

        if (binding.editTextAlasanPerbaikan.getText().toString().isEmpty()) {
            binding.editTextAlasanPerbaikan.setError("Mohon isi data berikut");
            cek2 = false;
        }

        return cek1 && cek2;
    }
}