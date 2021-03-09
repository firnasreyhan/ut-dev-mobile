package com.unitedtractors.android.unitedtractorsapp.view.activity.form.perbaikan;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPerbaikanBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas.ListPermintaanMobilDinasActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PerbaikanActivity extends AppCompatActivity {
    private ActivityPerbaikanBinding binding;
    private Calendar calendar;

    private String tanggal, tanggalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPerbaikanBinding.inflate(getLayoutInflater());
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

        binding.editTextWaktu.setOnClickListener(new View.OnClickListener() {
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
                        binding.editTextWaktu.setText(time);
                    }
                }, hour, min, true);

                timePicker.show();
            }
        });

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), KeteranganPerbaikanActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
                    intent.putExtra("TANGGAL", tanggal);
                    intent.putExtra("TANGGAL_VIEW", tanggalView);
                    intent.putExtra("WAKTU", binding.editTextWaktu.getText().toString());
                    intent.putExtra("NAMA_PEMOHON", binding.editTextNamaPemohon.getText().toString());
                    intent.putExtra("DIVISI", binding.editTextDivisiPemohon.getText().toString());
                    intent.putExtra("EXTENSION", binding.editTextExtensionPemohon.getText().toString());
                    intent.putExtra("NAMA_PENERIMA", binding.editTextNamaPenerima.getText().toString());
                    intent.putExtra("NOMOR_TROUBLE_TICKET", binding.editTextNomorTroubleTicket.getText().toString());
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
        boolean cek7 = true;

        if (binding.editTextTanggal.getText().toString().isEmpty()) {
            binding.editTextTanggal.setError("Mohon isi data berikut");
            cek1 = false;
        }

        if (binding.editTextWaktu.getText().toString().isEmpty()) {
            binding.editTextWaktu.setError("Mohon isi data berikut");
            cek2 = false;
        }

        if (binding.editTextNamaPemohon.getText().toString().isEmpty()) {
            binding.editTextNamaPemohon.setError("Mohon isi data berikut");
            cek3 = false;
        }

        if (binding.editTextDivisiPemohon.getText().toString().isEmpty()) {
            binding.editTextDivisiPemohon.setError("Mohon isi data berikut");
            cek4 = false;
        }

        if (binding.editTextExtensionPemohon.getText().toString().isEmpty()) {
            binding.editTextExtensionPemohon.setError("Mohon isi data berikut");
            cek5 = false;
        }

        if (binding.editTextNamaPenerima.getText().toString().isEmpty()) {
            binding.editTextNamaPenerima.setError("Mohon isi data berikut");
            cek6 = false;
        }

        if (binding.editTextNomorTroubleTicket.getText().toString().isEmpty()) {
            binding.editTextNomorTroubleTicket.setError("Mohon isi data berikut");
            cek7 = false;
        }

        return cek1 && cek2 && cek3 && cek4 && cek5 && cek6 && cek7;
    }
}