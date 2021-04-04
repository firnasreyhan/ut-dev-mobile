package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permohonan_pv_rv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPermohonanPVRVBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas.ListPermintaanMobilDinasActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PermohonanPVRVActivity extends AppCompatActivity {
    private ActivityPermohonanPVRVBinding binding;

    private Calendar calendar;

    private String idMapping;
    private String tanggal;
    private String tanggalView;
    private int jumlahPembayaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPermohonanPVRVBinding.inflate(getLayoutInflater());
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

        String[] caraPembayaran = new String[] {"Cash", "Transfer", "Fixed", "Temporary", "Deklarasi EX PP"};
        ArrayAdapter<String> caraPembayaranAdapter = new ArrayAdapter<>(this, R.layout.item_spinner, caraPembayaran);
        binding.spinnerCaraPembayaran.setAdapter(caraPembayaranAdapter);

        jumlahPembayaran = Integer.parseInt(String.valueOf(binding.editTextJumlahPembayaran.getText().toString()));
        binding.materialButtonTambahJumlahPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahPembayaran++;
                binding.editTextJumlahPembayaran.setText(String.valueOf(jumlahPembayaran));
                if (jumlahPembayaran > 0) {
                    binding.materialButtonSelanjutnya.setEnabled(true);
                    binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.primary));
                }
            }
        });

        binding.materialButtonKurangJumlahPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumlahPembayaran > 0) {
                    jumlahPembayaran--;
                    binding.editTextJumlahPembayaran.setText(String.valueOf(jumlahPembayaran));
                    if (jumlahPembayaran == 0) {
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
                    Intent intent = new Intent(v.getContext(), ListPermohonanPVRVActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
                    intent.putExtra("KOTA", binding.editTextKota.getText().toString());
                    intent.putExtra("TANGGAL", tanggal);
                    intent.putExtra("TANGGAL_VIEW", tanggalView);
                    intent.putExtra("DIBAYARKAN_KEPADA", binding.editTextDibayarkanKepada.getText().toString());
                    intent.putExtra("NRP_KARYAWAN", binding.editTextNRPKaryawan.getText().toString());
                    intent.putExtra("KEPERLUAN", binding.editTextKeperluan.getText().toString());
                    intent.putExtra("NOMOR_PO", binding.editTextNomorPO.getText().toString());
                    intent.putExtra("NOMOR_INVOICE", binding.editTextNomorInvoice.getText().toString());
                    intent.putExtra("CARA_PEMBAYARAN_KODE", String.valueOf(binding.spinnerCaraPembayaran.getSelectedItemPosition()));
                    intent.putExtra("CARA_PEMBAYARAN", caraPembayaran[binding.spinnerCaraPembayaran.getSelectedItemPosition()]);
                    intent.putExtra("NO_PPN", binding.editTextNoPPN.getText().toString());
                    intent.putExtra("NO_PPH", binding.editTextNoPPH.getText().toString());
                    intent.putExtra("PRESENTASE", binding.editTextPresentase.getText().toString());
                    intent.putExtra("JUMLAH_PEMBAYARAN", jumlahPembayaran);
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
        boolean cek8 = true;
        boolean cek9 = true;
        boolean cek10 = true;

        if (binding.editTextKota.getText().toString().isEmpty()) {
            binding.editTextKota.setError("Mohon isi data berikut");
            cek1 = false;
        }

        if (binding.editTextTanggal.getText().toString().isEmpty()) {
            binding.editTextTanggal.setError("Mohon isi data berikut");
            cek2 = false;
        }

        if (binding.editTextDibayarkanKepada.getText().toString().isEmpty()) {
            binding.editTextDibayarkanKepada.setError("Mohon isi data berikut");
            cek3 = false;
        }

        if (binding.editTextNRPKaryawan.getText().toString().isEmpty()) {
            binding.editTextNRPKaryawan.setError("Mohon isi data berikut");
            cek4 = false;
        }

        if (binding.editTextKeperluan.getText().toString().isEmpty()) {
            binding.editTextKeperluan.setError("Mohon isi data berikut");
            cek5 = false;
        }

        if (binding.editTextNomorPO.getText().toString().isEmpty()) {
            binding.editTextNomorPO.setError("Mohon isi data berikut");
            cek6 = false;
        }

        if (binding.editTextNomorInvoice.getText().toString().isEmpty()) {
            binding.editTextNomorInvoice.setError("Mohon isi data berikut");
            cek7 = false;
        }

        if (binding.editTextNoPPN.getText().toString().isEmpty()) {
            binding.editTextNoPPN.setError("Mohon isi data berikut");
            cek8 = false;
        }

        if (binding.editTextNoPPH.getText().toString().isEmpty()) {
            binding.editTextNoPPH.setError("Mohon isi data berikut");
            cek9 = false;
        }

        if (binding.editTextPresentase.getText().toString().isEmpty()) {
            binding.editTextPresentase.setError("Mohon isi data berikut");
            cek10 = false;
        }

        return cek1 && cek2 && cek3 && cek4 && cek5 && cek6 && cek7 && cek8 && cek9 && cek10;
    }
}