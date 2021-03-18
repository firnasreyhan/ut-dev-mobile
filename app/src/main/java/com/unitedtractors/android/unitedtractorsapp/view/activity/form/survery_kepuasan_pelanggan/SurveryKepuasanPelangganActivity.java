package com.unitedtractors.android.unitedtractorsapp.view.activity.form.survery_kepuasan_pelanggan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySurveryKepuasanPelangganBinding;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySyaratLegalitasCateringBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.syarat_legalitas_catering.SyaratLegatitasCateringLanjutanActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SurveryKepuasanPelangganActivity extends AppCompatActivity {
    private ActivitySurveryKepuasanPelangganBinding binding;
    Calendar calendar;

    private String tglSurvey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySurveryKepuasanPelangganBinding.inflate(getLayoutInflater());
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
                binding.editTextTanggalSurvey.setText(simpleDateFormatView.format(calendar.getTime()));
                tglSurvey = simpleDateFormatServer.format(calendar.getTime());
            }
        };

        binding.editTextTanggalSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailSurveyKepuasanPelangganActivity.class);
                intent.putExtra("ID_MAPPING", idMapping);
                intent.putExtra("NAMA", binding.editTextNamaSurvey.getText().toString());
                intent.putExtra("TGL_SURVEY", binding.editTextTanggalSurvey.getText().toString());
                intent.putExtra("DEPARTEMEN", binding.editTextDepartemen.getText().toString());
                intent.putExtra("DIVISI", binding.editTextDivisi.getText().toString());
                startActivity(intent);
            }
        });
    }
}