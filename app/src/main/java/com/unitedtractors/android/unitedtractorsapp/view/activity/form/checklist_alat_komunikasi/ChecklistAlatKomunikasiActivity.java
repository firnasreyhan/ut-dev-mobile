package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_alat_komunikasi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityChecklistAlatKomunikasiBinding;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityChecklistRuangMeetingBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_ruang_meeting.ListCheckRuangMeetingActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ChecklistAlatKomunikasiActivity extends AppCompatActivity {
    private ActivityChecklistAlatKomunikasiBinding binding;

    Calendar calendar;
    String idMapping;
    private String tgl;
    private String lokasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idMapping = getIntent().getStringExtra("ID_MAPPING");

        binding = ActivityChecklistAlatKomunikasiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.materialButtonSelanjutnya.setEnabled(true);
        binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.primary));

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
                binding.editTextTanggalChecklist.setText(simpleDateFormatView.format(calendar.getTime()));
                tgl = simpleDateFormatView.format(calendar.getTime());
            }
        };

        binding.editTextTanggalChecklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        lokasi = binding.editTextLokasi.getText().toString().trim();

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), PabxActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
                    intent.putExtra("TGL", tgl);
                    intent.putExtra("LOKASI", lokasi);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean checkData() {
        boolean cek1 = true;
        boolean cek2 = true;

        if (binding.editTextTanggalChecklist.getText().toString().isEmpty()) {
            binding.editTextTanggalChecklist.setError("Mohon isi data berikut");
            cek1 = false;
        }

        if (binding.editTextLokasi.getText().toString().isEmpty()) {
            binding.editTextLokasi.setError("Mohon isi data berikut");
            cek2 = false;
        }


        return cek1 && cek2;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}