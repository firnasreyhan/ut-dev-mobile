package com.unitedtractors.android.unitedtractorsapp.view.activity.form.material_used_slip;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityMaterialUsedSlipBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas.ListPermintaanMobilDinasActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MaterialUsedSlipActivity extends AppCompatActivity {
    private ActivityMaterialUsedSlipBinding binding;
    Calendar calendar;

    private String idMapping;
    private String tglMaterialView;
    private String tglMaterialServer;
    private  int banyakBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMaterialUsedSlipBinding.inflate(getLayoutInflater());
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
                binding.editTextTanggalMaterialUsedSlip.setText(simpleDateFormatView.format(calendar.getTime()));
                tglMaterialView = simpleDateFormatView.format(calendar.getTime());
                tglMaterialServer = simpleDateFormatServer.format(calendar.getTime());
            }
        };

        binding.editTextTanggalMaterialUsedSlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        banyakBarang = Integer.parseInt(String.valueOf(binding.editTextJumlahBarang.getText().toString()));

        binding.materialButtonTambahJumlahBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                banyakBarang++;
                binding.editTextJumlahBarang.setText(String.valueOf(banyakBarang));
                if (banyakBarang > 0) {
                    binding.materialButtonSelanjutnya.setEnabled(true);
                    binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.primary));
                }
            }
        });

        binding.materialButtonKurangJumlahBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (banyakBarang > 0) {
                    banyakBarang--;
                    binding.editTextJumlahBarang.setText(String.valueOf(banyakBarang));
                    if (banyakBarang == 0) {
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
                    Intent intent = new Intent(v.getContext(), ListMaterialUsedSlipActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
                    intent.putExtra("TGL_MATERIAL_VIEW", tglMaterialView);
                    intent.putExtra("TGL_MATERIAL_SERVER", tglMaterialServer);
                    intent.putExtra("BANYAK_BARANG", banyakBarang);
                    startActivity(intent);
                } else {
//                    new AlertDialog.Builder(v.getContext())
//                            .setTitle("Pesan")
//                            .setMessage("Terdapat data yang kosong, mohon untuk diisi.")
//                            .setCancelable(false)
//                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            })
//                            .create()
//                            .show();
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

        if (binding.editTextTanggalMaterialUsedSlip.getText().toString().isEmpty()) {
            binding.editTextTanggalMaterialUsedSlip.setError("Mohon isi data berikut");
            cek1 = false;
        }

        if (binding.editTextJumlahBarang.getText().toString().isEmpty()) {
            binding.editTextJumlahBarang.setError("Mohon isi data berikut");
            cek1 = false;
        }

        return cek1 && cek2;
    }
}

