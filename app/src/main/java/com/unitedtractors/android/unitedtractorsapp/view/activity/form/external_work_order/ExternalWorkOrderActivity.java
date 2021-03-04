package com.unitedtractors.android.unitedtractorsapp.view.activity.form.external_work_order;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityExternalWorkOrderBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas.ListPermintaanMobilDinasActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ExternalWorkOrderActivity extends AppCompatActivity {
    private ActivityExternalWorkOrderBinding binding;

    private Calendar calendar;

    private String idMapping;
    private String requestDateView;
    private String requestDateServer;
    private int jumlahPekerjaan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExternalWorkOrderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        idMapping = getIntent().getStringExtra("ID_MAPPING");
        calendar = Calendar.getInstance();

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SimpleDateFormat simpleDateFormatView = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
        SimpleDateFormat simpleDateFormatServer = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                binding.editTextRequestDate.setText(simpleDateFormatView.format(calendar.getTime()));
                requestDateView = simpleDateFormatView.format(calendar.getTime());
                requestDateServer = simpleDateFormatServer.format(calendar.getTime());
            }
        };

        binding.editTextRequestDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        jumlahPekerjaan = Integer.parseInt(String.valueOf(binding.editTextJumlahPekerjaan.getText().toString()));
        binding.materialButtonTambahJumlahTujuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahPekerjaan++;
                binding.editTextJumlahPekerjaan.setText(String.valueOf(jumlahPekerjaan));
                if (jumlahPekerjaan > 0) {
                    binding.materialButtonSelanjutnya.setEnabled(true);
                    binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.primary));
                }
            }
        });

        binding.materialButtonKurangJumlahTujuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumlahPekerjaan > 0) {
                    jumlahPekerjaan--;
                    binding.editTextJumlahPekerjaan.setText(String.valueOf(jumlahPekerjaan));
                    if (jumlahPekerjaan == 0) {
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
                    Intent intent = new Intent(v.getContext(), ListExternalWorkOrderActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
                    intent.putExtra("DIINTRUKSIKAN_KEPADA", binding.editTextDiinstruksikanKepada.getText().toString());
                    intent.putExtra("INTRUKSI_DARI", binding.editTextIntruksiDari.getText().toString());
                    intent.putExtra("PEKERJAAN", binding.editTextPekerjaan.getText().toString());
                    intent.putExtra("REG_NO", binding.editTextRegNo.getText().toString());
                    intent.putExtra("REQUEST_DATE_VIEW", requestDateView);
                    intent.putExtra("REQUEST_DATE_SERVER", requestDateServer);
                    intent.putExtra("PAGES", binding.editTextPages.getText().toString());
                    intent.putExtra("CC", binding.editTextCC.getText().toString());
                    intent.putExtra("JUMLAH_PEKERJAAN", jumlahPekerjaan);
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

        if (binding.editTextDiinstruksikanKepada.getText().toString().isEmpty()) {
            binding.editTextDiinstruksikanKepada.setError("Mohon isi data berikut");
            cek1 = false;
        }

        if (binding.editTextIntruksiDari.getText().toString().isEmpty()) {
            binding.editTextIntruksiDari.setError("Mohon isi data berikut");
            cek2 = false;
        }

        if (binding.editTextPekerjaan.getText().toString().isEmpty()) {
            binding.editTextPekerjaan.setError("Mohon isi data berikut");
            cek3 = false;
        }

        if (binding.editTextRegNo.getText().toString().isEmpty()) {
            binding.editTextRegNo.setError("Mohon isi data berikut");
            cek4 = false;
        }

        if (binding.editTextRequestDate.getText().toString().isEmpty()) {
            binding.editTextRequestDate.setError("Mohon isi data berikut");
            cek5 = false;
        }

        if (binding.editTextPages.getText().toString().isEmpty()) {
            binding.editTextPages.setError("Mohon isi data berikut");
            cek6 = false;
        }

        if (binding.editTextCC.getText().toString().isEmpty()) {
            binding.editTextCC.setError("Mohon isi data berikut");
            cek7 = false;
        }

        return cek1 && cek2 && cek3 && cek4 && cek5 && cek6 && cek7;
    }
}