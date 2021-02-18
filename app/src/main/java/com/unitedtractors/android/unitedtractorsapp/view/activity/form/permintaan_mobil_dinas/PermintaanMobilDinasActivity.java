package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPermintaanMobilDinasBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_asset.KonfirmasiPermintaanAssetActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_asset.ListPermintaanAssetActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.unitedtractors.android.unitedtractorsapp.R;

public class PermintaanMobilDinasActivity extends AppCompatActivity {
    private ActivityPermintaanMobilDinasBinding binding;

    private Calendar calendar;

    private String namaPeminjam;
    private String namaPengemudi;
    private String tglPeminjaman;
    private String tglPengembalian;
    private String divisi;
    private String noPolisi;
    private String jamBerangkat;
    private String jamPulang;
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

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormatView = new SimpleDateFormat("dd/M/yyyy", Locale.getDefault());
                binding.editTextTanggalPeminjaman.setText(simpleDateFormatView.format(calendar.getTime()));
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
                SimpleDateFormat simpleDateFormatView = new SimpleDateFormat("dd/M/yyyy", Locale.getDefault());
                binding.editTextTanggalPengembalian.setText(simpleDateFormatView.format(calendar.getTime()));
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
                        String time1 = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
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
                        String time1 = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
                        binding.editTextJamPulang.setText(time1);
                    }
                }, hour, min, true);

                timePicker.show();
            }
        });
        jumlahTujuan = 0;

        if (jumlahTujuan == 0) {
            binding.materialButtonSelanjutnya.setEnabled(false);
            binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.button_disable));
        }

        namaPeminjam = "John Wick";
        namaPengemudi = binding.editTextNamaPengemudi.getText().toString().trim();
        tglPeminjaman = binding.editTextTanggalPeminjaman.getText().toString().trim();
        tglPengembalian = binding.editTextTanggalPengembalian.getText().toString().trim();
        divisi = binding.editTextDivisi.getText().toString().trim();
        noPolisi = binding.editTextNoPolisi.getText().toString().trim();
        jamBerangkat = binding.editTextJamBerangkat.getText().toString().trim();
        jamPulang = binding.editTextJamPulang.getText().toString().trim();
        jumlahTujuan = Integer.parseInt(binding.editTextJumlahTujuan.getText().toString());

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
                    Intent intent = new Intent(v.getContext(), TujuanKeperluanActivity.class);
                    intent.putExtra("nama_pengemudi", namaPengemudi);
                    intent.putExtra("nama_peminjam", namaPeminjam);
                    intent.putExtra("tgl_peminjaman", tglPeminjaman);
                    intent.putExtra("tgl_pengembalian", tglPengembalian);
                    intent.putExtra("divisi", divisi);
                    intent.putExtra("no_polisi", noPolisi);
                    intent.putExtra("jam_berangkat", jamBerangkat);
                    intent.putExtra("jam_pulang", jamPulang);
                    intent.putExtra("jumlah_tujuan", jumlahTujuan);
                    startActivity(intent);
                } else {
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("Pesan")
                            .setMessage("Terdapat data yang kosong, mohon untuk diisi.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create()
                            .show();
                }

            }
        });

    }

    boolean checkData() {
        if (namaPeminjam.isEmpty()) {
            return false;
        }

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}