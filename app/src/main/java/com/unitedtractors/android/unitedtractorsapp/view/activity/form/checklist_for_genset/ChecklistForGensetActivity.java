package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_for_genset;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityChecklistForGensetBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas.ListPermintaanMobilDinasActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ChecklistForGensetActivity extends AppCompatActivity {
    private ActivityChecklistForGensetBinding binding;

    private Calendar calendar;

    private String idMapping;
    private String tanggal;
    private String tanggalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChecklistForGensetBinding.inflate(getLayoutInflater());
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
                    intent.putExtra("ENGINE", binding.editTextEngine.getText().toString());
                    intent.putExtra("ENGINE_MODEL", binding.editTextEngineModel.getText().toString());
                    intent.putExtra("SERIAL_NO", binding.editTextSerialNo.getText().toString());
                    intent.putExtra("GENO_TYPE", binding.editTextGenoType.getText().toString());
                    intent.putExtra("SERIAL_NO_2", binding.editTextSerialNo2.getText().toString());
                    intent.putExtra("HOUR_METER", binding.editTextHourMeter.getText().toString());
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
        boolean cek5 = true;
        boolean cek6 = true;
        boolean cek7 = true;
        boolean cek8 = true;
        boolean cek9 = true;

        if (binding.editTextTanggal.getText().toString().isEmpty()) {
            binding.editTextTanggal.setError("Mohon isi data berikut");
            cek1 = false;
        }

        if (binding.editTextLokasi.getText().toString().isEmpty()) {
            binding.editTextLokasi.setError("Mohon isi data berikut");
            cek2 = false;
        }

        if (binding.editTextEngine.getText().toString().isEmpty()) {
            binding.editTextEngine.setError("Mohon isi data berikut");
            cek3 = false;
        }

        if (binding.editTextSerialNo.getText().toString().isEmpty()) {
            binding.editTextSerialNo.setError("Mohon isi data berikut");
            cek5 = false;
        }

        if (binding.editTextGenoType.getText().toString().isEmpty()) {
            binding.editTextGenoType.setError("Mohon isi data berikut");
            cek6 = false;
        }

        if (binding.editTextSerialNo2.getText().toString().isEmpty()) {
            binding.editTextSerialNo2.setError("Mohon isi data berikut");
            cek7 = false;
        }

        if (binding.editTextHourMeter.getText().toString().isEmpty()) {
            binding.editTextHourMeter.setError("Mohon isi data berikut");
            cek8 = false;
        }

        if (binding.editTextEngineModel.getText().toString().isEmpty()) {
            binding.editTextEngineModel.setError("Mohon isi data berikut");
            cek9 = false;
        }

        return cek1 && cek2 && cek3 && cek5 && cek6 && cek7 && cek8 && cek9;
    }
}