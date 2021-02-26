package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPermintaanMobilDinasBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PermintaanMobilDinasActivity extends AppCompatActivity {
    private ActivityPermintaanMobilDinasBinding binding;

    private Calendar calendar;

    private String tglPeminjamanView;
    private String tglPeminjamanServer;
    private String tglPengembalianView;
    private String tglPengembalianServer;
    private int jumlahTujuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPermintaanMobilDinasBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

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
                        String time1 = String.valueOf(hourOfDay) + "." + String.valueOf(minute);
                        binding.editTextJamBerangkat.setText(time1);
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
                        String time1 = String.valueOf(hourOfDay) + "." + String.valueOf(minute);
                        binding.editTextJamPulang.setText(time1);
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


        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), ListPermintaanMobilDinasActivity.class);
                    intent.putExtra("PENGEMUDI", binding.editTextNamaPengemudi.getText().toString());
                    intent.putExtra("TGL_PEMINJAMAN_VIEW", tglPeminjamanView);
                    intent.putExtra("TGL_PEMINJAMAN_SERVER", tglPeminjamanServer);
                    intent.putExtra("TGL_PENGEMBALIAN_VIEW", tglPengembalianView);
                    intent.putExtra("TGL_PENGEMBALIAN_SERVER", tglPengembalianServer);
                    intent.putExtra("DIVISI_DEPARTEMENT", binding.editTextDivisiDepartement.getText().toString());
                    intent.putExtra("NO_POLISI", binding.editTextNoPolisi.getText().toString());
                    intent.putExtra("JAM_BERANGKAT", binding.editTextJamBerangkat.getText().toString());
                    intent.putExtra("JAM_PULANG", binding.editTextJamPulang.getText().toString());
                    intent.putExtra("JUMLAH_TUJUAN", jumlahTujuan);
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

    private boolean checkData() {
        boolean cek1 = true;
        boolean cek2 = true;
        boolean cek3 = true;
        boolean cek4 = true;
        boolean cek5 = true;
        boolean cek6 = true;
        boolean cek7 = true;

        if (binding.editTextNamaPengemudi.getText().toString().isEmpty()) {
            binding.editTextNamaPengemudi.setError("Mohon isi data berikut.");
            cek1 = false;
        }

        if (binding.editTextTanggalPeminjaman.getText().toString().isEmpty()) {
            binding.editTextTanggalPeminjaman.setError("Mohon isi data berikut.");
            cek2 = false;
        }

        if (binding.editTextTanggalPengembalian.getText().toString().isEmpty()) {
            binding.editTextTanggalPengembalian.setError("Mohon isi data berikut.");
            cek3 = false;
        }

        if (binding.editTextDivisiDepartement.getText().toString().isEmpty()) {
            binding.editTextDivisiDepartement.setError("Mohon isi data berikut.");
            cek4 = false;
        }

        if (binding.editTextNoPolisi.getText().toString().isEmpty()) {
            binding.editTextNoPolisi.setError("Mohon isi data berikut.");
            cek5 = false;
        }

        if (binding.editTextJamBerangkat.getText().toString().isEmpty()) {
            binding.editTextJamBerangkat.setError("Mohon isi data berikut.");
            cek6= false;
        }

        if (binding.editTextJamPulang.getText().toString().isEmpty()) {
            binding.editTextJamPulang.setError("Mohon isi data berikut.");
            cek7 = false;
        }

        if (cek1 && cek2 && cek3 && cek4 && cek5 && cek6 && cek7) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}