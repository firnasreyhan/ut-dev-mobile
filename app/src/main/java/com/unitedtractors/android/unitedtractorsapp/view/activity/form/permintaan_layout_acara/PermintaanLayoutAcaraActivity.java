package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_layout_acara;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPermintaanLayoutAcaraBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas.ListPermintaanMobilDinasActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PermintaanLayoutAcaraActivity extends AppCompatActivity {
    private ActivityPermintaanLayoutAcaraBinding binding;

    private Calendar calendar;

    private String tanggalView;
    private String tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPermintaanLayoutAcaraBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormatView = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
        SimpleDateFormat simpleDateFormatServer = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                binding.editTextTanggal.setText(simpleDateFormatView.format(calendar.getTime()));
                tanggalView = simpleDateFormatView.format(calendar.getTime());
                tanggal = simpleDateFormatServer.format(calendar.getTime());
            }
        };

        binding.editTextTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        binding.editTextJam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int min = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePicker = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String hour = hourOfDay < 10 ? ("0"+ hourOfDay) : String.valueOf(hourOfDay);
                        String sMinute = minute < 10 ? ("0"+ minute) : String.valueOf(minute);
                        String time = hour + ":" + sMinute;
                        binding.editTextJam.setText(time);
                    }
                }, hour, min, true);

                timePicker.show();
            }
        });

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), DrawPermintaanLayoutAcaraActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
                    intent.putExtra("NAMA_ACARA", binding.editTextNamaAcara.getText().toString());
                    intent.putExtra("LOKASI_ACARA", binding.editTextLokasiAcara.getText().toString());
                    intent.putExtra("TANGGAL", tanggal);
                    intent.putExtra("TANGGAL_VIEW", tanggalView);
                    intent.putExtra("JAM", binding.editTextJam.getText().toString());
                    intent.putExtra("JUMLAH_PESERTA", binding.editTextJumlahPeserta.getText().toString());
                    intent.putExtra("BEBAN_BIAYA", binding.editTextBebanBiaya.getText().toString());
                    intent.putExtra("KETERANGAN", binding.editTextKeterangan.getText().toString());
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
        boolean cek3 = true;
        boolean cek4 = true;
        boolean cek5 = true;
        boolean cek6 = true;

        if (binding.editTextNamaAcara.getText().toString().isEmpty()) {
            binding.editTextNamaAcara.setError("Mohon isi data berikut");
            cek1 = false;
        }

        if (binding.editTextLokasiAcara.getText().toString().isEmpty()) {
            binding.editTextLokasiAcara.setError("Mohon isi data berikut");
            cek2 = false;
        }

        if (binding.editTextTanggal.getText().toString().isEmpty()) {
            binding.editTextTanggal.setError("Mohon isi data berikut");
            cek3 = false;
        }

        if (binding.editTextJam.getText().toString().isEmpty()) {
            binding.editTextJam.setError("Mohon isi data berikut");
            cek4 = false;
        }

        if (binding.editTextJumlahPeserta.getText().toString().isEmpty()) {
            binding.editTextJumlahPeserta.setError("Mohon isi data berikut");
            cek5 = false;
        }

        if (binding.editTextBebanBiaya.getText().toString().isEmpty()) {
            binding.editTextBebanBiaya.setError("Mohon isi data berikut");
            cek6 = false;
        }

        return cek1 && cek2 && cek3 && cek4 && cek5 && cek6;
    }
}