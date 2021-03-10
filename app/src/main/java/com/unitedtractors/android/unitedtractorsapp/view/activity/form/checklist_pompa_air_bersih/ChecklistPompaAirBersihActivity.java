package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_pompa_air_bersih;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityChecklistPompaAirBersihBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_for_genset.UnitEngineActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ChecklistPompaAirBersihActivity extends AppCompatActivity {
    private ActivityChecklistPompaAirBersihBinding binding;

    private Calendar calendar;

    private String idMapping;
    private String tanggal;
    private String tanggalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChecklistPompaAirBersihBinding.inflate(getLayoutInflater());
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

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), UnitEngineActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
                    intent.putExtra("TANGGAL", tanggal);
                    intent.putExtra("TANGGAL_VIEW", tanggalView);
                    intent.putExtra("LOKASI", binding.editTextLokasi.getText().toString());
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

        if (binding.editTextTanggal.getText().toString().isEmpty()) {
            binding.editTextTanggal.setError("Mohon isi data berikut");
            cek1 = false;
        }

        if (binding.editTextLokasi.getText().toString().isEmpty()) {
            binding.editTextLokasi.setError("Mohon isi data berikut");
            cek2 = false;
        }

        return cek1 && cek2;
    }
}