package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPermintaanMobilDinasBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_pribadi.PermintaanMobilPribadiActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PermintaanMobilDinasActivity extends AppCompatActivity {
    private ActivityPermintaanMobilDinasBinding binding;

    private Calendar calendar;

    private String idMapping;
    private String tglPeminjamanView;
    private String tglPeminjamanServer;
    private String tglPengembalianView;
    private String tglPengembalianServer;
    private int jumlahTujuan;
    private Uri imgSIM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPermintaanMobilDinasBinding.inflate(getLayoutInflater());
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

//        String[] divisons = new String[] {"Project Management", "General Service & Maintenance Management", "Budget, Asset & Building Management", "Others"};
//        ArrayAdapter<String> divisionAdapter = new ArrayAdapter<>(this, R.layout.item_spinner, divisons);
//        binding.spinnerDivision.setAdapter(divisionAdapter);

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

        jumlahTujuan = Integer.parseInt(String.valueOf(binding.editTextJumlahTujuan.getText().toString()));

        binding.materialButtonTambahJumlahTujuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahTujuan++;
                binding.editTextJumlahTujuan.setText(String.valueOf(jumlahTujuan));
                if (jumlahTujuan > 0) {
                    binding.materialButtonSelanjutnya.setEnabled(true);
                    binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.primary));
                }
            }
        });

        binding.materialButtonKurangJumlahTujuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumlahTujuan > 0) {
                    jumlahTujuan--;
                    binding.editTextJumlahTujuan.setText(String.valueOf(jumlahTujuan));
                    if (jumlahTujuan == 0) {
                        binding.materialButtonSelanjutnya.setEnabled(false);
                        binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.button_disable));
                    }
                }
            }
        });

        binding.materialCardViewSIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(PermintaanMobilDinasActivity.this)
                        .crop()
                        .cameraOnly()
                        .start();
            }
        });

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), ListPermintaanMobilDinasActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
                    intent.putExtra("PENGEMUDI", binding.editTextNamaPengemudi.getText().toString());
                    intent.putExtra("TGL_PEMINJAMAN_VIEW", tglPeminjamanView);
                    intent.putExtra("TGL_PEMINJAMAN_SERVER", tglPeminjamanServer);
                    intent.putExtra("TGL_PENGEMBALIAN_VIEW", tglPengembalianView);
                    intent.putExtra("TGL_PENGEMBALIAN_SERVER", tglPengembalianServer);
//                    intent.putExtra("DIVISI_DEPARTEMENT", divisons[binding.spinnerDivision.getSelectedItemPosition()]);
//                    intent.putExtra("NO_POLISI", binding.editTextNoPolisi.getText().toString());
                    intent.putExtra("JAM_BERANGKAT", binding.editTextJamBerangkat.getText().toString());
                    intent.putExtra("JAM_PULANG", binding.editTextJamPulang.getText().toString());
//                    intent.putExtra("KM_AWAL", binding.editTextKMAwal.getText().toString());
//                    intent.putExtra("KM_AKHIR", binding.editTextKMAkhir.getText().toString());
                    intent.putExtra("IMG_SIM", imgSIM.toString());
                    intent.putExtra("JUMLAH_TUJUAN", jumlahTujuan);
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
        boolean cek5 = true;
        boolean cek6 = true;
        boolean cek7 = true;
        boolean cek8 = true;
        boolean cek9 = true;
        boolean cek10 = true;

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

//        if (binding.editTextNoPolisi.getText().toString().isEmpty()) {
//            binding.editTextNoPolisi.setError("Mohon isi data berikut");
//            cek5 = false;
//        }

        if (binding.editTextJamBerangkat.getText().toString().isEmpty()) {
            binding.editTextJamBerangkat.setError("Mohon isi data berikut");
            cek6 = false;
        }

        if (binding.editTextJamPulang.getText().toString().isEmpty()) {
            binding.editTextJamPulang.setError("Mohon isi data berikut");
            cek7 = false;
        }

//        if (binding.editTextKMAwal.getText().toString().isEmpty()) {
//            binding.editTextKMAwal.setError("Mohon isi data berikut");
//            cek8 = false;
//        }
//
//        if (binding.editTextKMAkhir.getText().toString().isEmpty()) {
//            binding.editTextKMAkhir.setError("Mohon isi data berikut");
//            cek9 = false;
//        }

        if (imgSIM == null) {
            Toast.makeText(this, "Mohon upload foto SIM anda", Toast.LENGTH_SHORT).show();
            cek10 = false;
        }

        return cek1 && cek2 && cek3 && cek6 && cek7 && cek10;
    }
}