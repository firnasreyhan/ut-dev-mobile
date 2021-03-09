package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_pribadi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPermintaanMobilPribadiBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PermintaanMobilPribadiActivity extends AppCompatActivity {
    private ActivityPermintaanMobilPribadiBinding binding;

    private Calendar calendar;

    private String idMapping;
    private String tglPeminjamanView;
    private String tglPeminjamanServer;
    private String tglPengembalianView;
    private String tglPengembalianServer;
    private Uri imgSIM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPermintaanMobilPribadiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        idMapping = getIntent().getStringExtra("ID_MAPPING");

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
                binding.editTextTanggalPeminjaman.setText(simpleDateFormatView.format(calendar.getTime()));
                tglPeminjamanView = simpleDateFormatView.format(calendar.getTime());
                tglPeminjamanServer = simpleDateFormatServer.format(calendar.getTime());
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

        DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                binding.editTextTanggalPengembalian.setText(simpleDateFormatView.format(calendar.getTime()));
                tglPengembalianView = simpleDateFormatView.format(calendar.getTime());
                tglPengembalianServer = simpleDateFormatServer.format(calendar.getTime());
            }
        };

        binding.editTextTanggalPengembalian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), date1, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        binding.editTextJamBerangkat.setOnClickListener(new View.OnClickListener() {
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
                        binding.editTextJamBerangkat.setText(time);
                    }
                }, hour, min, true);

                timePicker.show();
            }
        });

        binding.editTextJamPulang.setOnClickListener(new View.OnClickListener() {
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
                        binding.editTextJamPulang.setText(time);
                    }
                }, hour, min, true);

                timePicker.show();
            }
        });

        binding.materialCardViewSIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(PermintaanMobilPribadiActivity.this)
                        .crop()
                        .cameraOnly()
                        .start();
            }
        });

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), KonfirmasiPermintaanMobilPribadiActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
                    intent.putExtra("PENGEMUDI", binding.editTextNamaPengemudi.getText().toString());
                    intent.putExtra("TGL_PEMINJAMAN_VIEW", tglPeminjamanView);
                    intent.putExtra("TGL_PEMINJAMAN_SERVER", tglPeminjamanServer);
                    intent.putExtra("TGL_PENGEMBALIAN_VIEW", tglPengembalianView);
                    intent.putExtra("TGL_PENGEMBALIAN_SERVER", tglPengembalianServer);
                    intent.putExtra("JAM_BERANGKAT", binding.editTextJamBerangkat.getText().toString());
                    intent.putExtra("JAM_PULANG", binding.editTextJamPulang.getText().toString());
                    intent.putExtra("IMG_SIM", imgSIM.toString());
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            Uri fileUri = data.getData();
            imgSIM = fileUri;
            binding.imageViewSIM.setImageURI(fileUri);
        }
    }

    private boolean checkData() {
        boolean cek1 = true;
        boolean cek2 = true;
        boolean cek3 = true;
        boolean cek4 = true;
        boolean cek5 = true;
        boolean cek6 = true;

        if (binding.editTextNamaPengemudi.getText().toString().isEmpty()) {
            binding.editTextNamaPengemudi.setError("Mohon isi data berikut");
            cek1 = false;
        }

        if (binding.editTextTanggalPeminjaman.getText().toString().isEmpty()) {
            binding.editTextTanggalPeminjaman.setError("Mohon isi data berikut");
            cek2 = false;
        }

        if (binding.editTextTanggalPengembalian.getText().toString().isEmpty()) {
            binding.editTextTanggalPengembalian.setError("Mohon isi data berikut");
            cek3 = false;
        }

        if (binding.editTextJamBerangkat.getText().toString().isEmpty()) {
            binding.editTextJamBerangkat.setError("Mohon isi data berikut");
            cek4 = false;
        }

        if (binding.editTextJamPulang.getText().toString().isEmpty()) {
            binding.editTextJamPulang.setError("Mohon isi data berikut");
            cek5 = false;
        }

        if (imgSIM == null) {
            Toast.makeText(this, "Mohon upload foto SIM anda", Toast.LENGTH_SHORT).show();
            cek6 = false;
        }

        return cek1 && cek2 && cek3 && cek4 && cek5 && cek6;
    }
}