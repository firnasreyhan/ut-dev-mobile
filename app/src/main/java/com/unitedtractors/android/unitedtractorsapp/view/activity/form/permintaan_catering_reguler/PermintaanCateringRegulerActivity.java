package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_catering_reguler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityIdentifikasiBinding;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPermintaanCateringRegulerBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_ruang_meeting.ListCheckRuangMeetingActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PermintaanCateringRegulerActivity extends AppCompatActivity {
    private ActivityPermintaanCateringRegulerBinding binding;

    private int jumlahOrang;
    Calendar calendar;

    String idMapping;
    String tglCateringView;
    String tglCateringServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPermintaanCateringRegulerBinding.inflate(getLayoutInflater());
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
                tglCateringView = simpleDateFormatView.format(calendar.getTime());
                tglCateringServer = simpleDateFormatServer.format(calendar.getTime());
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

        jumlahOrang = Integer.parseInt(binding.editTextJumlahOrang.getText().toString());

        binding.materialButtonTambahJumlahOrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahOrang++;
                binding.editTextJumlahOrang.setText(String.valueOf(jumlahOrang));
                if (jumlahOrang > 0) {

                    binding.materialButtonSelanjutnya.setEnabled(true);
                    binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.primary));
                }
            }
        });

        binding.materialButtonKurangJumlahOrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumlahOrang > 0) {
                    jumlahOrang--;
                    binding.editTextJumlahOrang.setText(String.valueOf(jumlahOrang));
                    if (jumlahOrang == 0) {

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
                    Intent intent = new Intent(v.getContext(), KonfirmasiPermintaanCateringRegulerActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
                    intent.putExtra("TGL_CATERING_SERVER", tglCateringServer);
                    intent.putExtra("TGL_CATERING_VIEW", tglCateringView);
                    intent.putExtra("JUMLAH_ORANG", binding.editTextJumlahOrang.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    private boolean checkData() {
        boolean cek1 = true;

        if (binding.editTextTanggal.getText().toString().isEmpty()) {
            binding.editTextTanggal.setError("Mohon isi data berikut");
            cek1 = false;
        }

        return cek1;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}