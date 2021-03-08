package com.unitedtractors.android.unitedtractorsapp.view.activity.form.laporan_perbaikan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityLaporanPerbaikanBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas.ListPermintaanMobilDinasActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class LaporanPerbaikanActivity extends AppCompatActivity {
    private ActivityLaporanPerbaikanBinding binding;
    private String idMapping, tanggal, tanggalView;
    private int jumlahLaporan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLaporanPerbaikanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormatView = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
        SimpleDateFormat simpleDateFormatServer = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                binding.editTextTanggalPeminjaman.setText(simpleDateFormatView.format(calendar.getTime()));
                tanggalView = simpleDateFormatView.format(calendar.getTime());
                tanggal = simpleDateFormatServer.format(calendar.getTime());
            }
        };

        binding.editTextTanggalPeminjaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        jumlahLaporan = Integer.parseInt(String.valueOf(binding.editTextJumlahLaporan.getText().toString()));

        binding.materialButtonTambahLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahLaporan++;
                binding.editTextJumlahLaporan.setText(String.valueOf(jumlahLaporan));
                if (jumlahLaporan > 0) {
                    binding.materialButtonSelanjutnya.setEnabled(true);
                    binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.primary));
                }
            }
        });

        binding.materialButtonKurangLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumlahLaporan > 0) {
                    jumlahLaporan--;
                    binding.editTextJumlahLaporan.setText(String.valueOf(jumlahLaporan));
                    if (jumlahLaporan == 0) {
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
                    Intent intent = new Intent(v.getContext(), ListLaporanPerbaikanActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
                    intent.putExtra("TANGGAL", tanggal);
                    intent.putExtra("TANGGAL_VIEW", tanggalView);
                    intent.putExtra("JUMLAH_LAPORAN", jumlahLaporan);
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
        boolean cek = true;

        if (binding.editTextTanggalPeminjaman.getText().toString().isEmpty()) {
            cek = false;
            binding.editTextTanggalPeminjaman.setError("Mohon isi data berikut");
        }

        return cek;
    }
}