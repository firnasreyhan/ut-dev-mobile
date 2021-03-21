package com.unitedtractors.android.unitedtractorsapp.view.activity.form.evaluasi_pekerjaan_vendor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityEvaluasiPekerjaanVendorBinding;
import com.unitedtractors.android.unitedtractorsapp.model.EvaluasiPekerjaanVendorModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.external_work_order.KonfirmasiExternalWorkOrderActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.order_catering.KonfrimasiOrderCateringActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.EvaluasiPekerjaanVendorViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EvaluasiPekerjaanVendorActivity extends AppCompatActivity {
    private ActivityEvaluasiPekerjaanVendorBinding binding;
    private EvaluasiPekerjaanVendorViewModel viewModel;
    private Calendar calendar;
    private ProgressDialog progressDialog;

    private String tanggal;
    private int mutu = 1;
    private int harga = 1;
    private int delivery = 1;
    private int safety = 1;
    private int pelayanan = 1;
    private int totalNilai = mutu + harga + delivery + safety + pelayanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEvaluasiPekerjaanVendorBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        calendar = Calendar.getInstance();
        viewModel = ViewModelProviders.of(this).get(EvaluasiPekerjaanVendorViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu Sebentar...");
        progressDialog.setCancelable(false);

        SimpleDateFormat simpleDateFormatView = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
        SimpleDateFormat simpleDateFormatServer = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                binding.editTextTanggalSPK.setText(simpleDateFormatView.format(calendar.getTime()));
                tanggal = simpleDateFormatServer.format(calendar.getTime());
            }
        };

        binding.editTextTanggalSPK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        binding.radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton1:
                        mutu = 3;
                        totalNilai = mutu + harga + delivery + safety + pelayanan;
                        binding.textViewTotalNilai.setText("Total Nilai : " + totalNilai);
                        if (totalNilai < 8) {
                            binding.textViewKesimpulan.setText("Rekomendasi tidak diteruskan sebagai vendor");
                        } else if (totalNilai >= 8 && totalNilai <= 11) {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor dengan improvement");
                        } else {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor");
                        }
                        break;
                    case R.id.radioButton2:
                        mutu = 2;
                        totalNilai = mutu + harga + delivery + safety + pelayanan;
                        binding.textViewTotalNilai.setText("Total Nilai : " + totalNilai);
                        if (totalNilai < 8) {
                            binding.textViewKesimpulan.setText("Rekomendasi tidak diteruskan sebagai vendor");
                        } else if (totalNilai >= 8 && totalNilai <= 11) {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor dengan improvement");
                        } else {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor");
                        }
                        break;
                    case R.id.radioButton3:
                        mutu = 1;
                        totalNilai = mutu + harga + delivery + safety + pelayanan;
                        binding.textViewTotalNilai.setText("Total Nilai : " + totalNilai);
                        if (totalNilai < 8) {
                            binding.textViewKesimpulan.setText("Rekomendasi tidak diteruskan sebagai vendor");
                        } else if (totalNilai >= 8 && totalNilai <= 11) {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor dengan improvement");
                        } else {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor");
                        }
                        break;
                }
            }
        });

        binding.radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton4:
                        harga = 3;
                        totalNilai = mutu + harga + delivery + safety + pelayanan;
                        binding.textViewTotalNilai.setText("Total Nilai : " + totalNilai);
                        if (totalNilai < 8) {
                            binding.textViewKesimpulan.setText("Rekomendasi tidak diteruskan sebagai vendor");
                        } else if (totalNilai >= 8 && totalNilai <= 11) {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor dengan improvement");
                        } else {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor");
                        }
                        break;
                    case R.id.radioButton5:
                        harga = 2;
                        totalNilai = mutu + harga + delivery + safety + pelayanan;
                        binding.textViewTotalNilai.setText("Total Nilai : " + totalNilai);
                        if (totalNilai < 8) {
                            binding.textViewKesimpulan.setText("Rekomendasi tidak diteruskan sebagai vendor");
                        } else if (totalNilai >= 8 && totalNilai <= 11) {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor dengan improvement");
                        } else {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor");
                        }
                        break;
                    case R.id.radioButton6:
                        harga = 1;
                        totalNilai = mutu + harga + delivery + safety + pelayanan;
                        binding.textViewTotalNilai.setText("Total Nilai : " + totalNilai);
                        if (totalNilai < 8) {
                            binding.textViewKesimpulan.setText("Rekomendasi tidak diteruskan sebagai vendor");
                        } else if (totalNilai >= 8 && totalNilai <= 11) {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor dengan improvement");
                        } else {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor");
                        }
                        break;
                }
            }
        });

        binding.radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton7:
                        delivery = 3;
                        totalNilai = mutu + harga + delivery + safety + pelayanan;
                        binding.textViewTotalNilai.setText("Total Nilai : " + totalNilai);
                        if (totalNilai < 8) {
                            binding.textViewKesimpulan.setText("Rekomendasi tidak diteruskan sebagai vendor");
                        } else if (totalNilai >= 8 && totalNilai <= 11) {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor dengan improvement");
                        } else {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor");
                        }
                        break;
                    case R.id.radioButton8:
                        delivery = 2;
                        totalNilai = mutu + harga + delivery + safety + pelayanan;
                        binding.textViewTotalNilai.setText("Total Nilai : " + totalNilai);
                        if (totalNilai < 8) {
                            binding.textViewKesimpulan.setText("Rekomendasi tidak diteruskan sebagai vendor");
                        } else if (totalNilai >= 8 && totalNilai <= 11) {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor dengan improvement");
                        } else {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor");
                        }
                        break;
                    case R.id.radioButton9:
                        delivery = 1;
                        totalNilai = mutu + harga + delivery + safety + pelayanan;
                        binding.textViewTotalNilai.setText("Total Nilai : " + totalNilai);
                        if (totalNilai < 8) {
                            binding.textViewKesimpulan.setText("Rekomendasi tidak diteruskan sebagai vendor");
                        } else if (totalNilai >= 8 && totalNilai <= 11) {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor dengan improvement");
                        } else {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor");
                        }
                        break;
                }
            }
        });

        binding.radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton10:
                        safety = 3;
                        totalNilai = mutu + harga + delivery + safety + pelayanan;
                        binding.textViewTotalNilai.setText("Total Nilai : " + totalNilai);
                        if (totalNilai < 8) {
                            binding.textViewKesimpulan.setText("Rekomendasi tidak diteruskan sebagai vendor");
                        } else if (totalNilai >= 8 && totalNilai <= 11) {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor dengan improvement");
                        } else {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor");
                        }
                        break;
                    case R.id.radioButton11:
                        safety = 2;
                        totalNilai = mutu + harga + delivery + safety + pelayanan;
                        binding.textViewTotalNilai.setText("Total Nilai : " + totalNilai);
                        if (totalNilai < 8) {
                            binding.textViewKesimpulan.setText("Rekomendasi tidak diteruskan sebagai vendor");
                        } else if (totalNilai >= 8 && totalNilai <= 11) {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor dengan improvement");
                        } else {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor");
                        }
                        break;
                    case R.id.radioButton12:
                        safety = 1;
                        totalNilai = mutu + harga + delivery + safety + pelayanan;
                        binding.textViewTotalNilai.setText("Total Nilai : " + totalNilai);
                        if (totalNilai < 8) {
                            binding.textViewKesimpulan.setText("Rekomendasi tidak diteruskan sebagai vendor");
                        } else if (totalNilai >= 8 && totalNilai <= 11) {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor dengan improvement");
                        } else {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor");
                        }
                        break;
                }
            }
        });

        binding.radioGroup5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton13:
                        pelayanan = 3;
                        totalNilai = mutu + harga + delivery + safety + pelayanan;
                        binding.textViewTotalNilai.setText("Total Nilai : " + totalNilai);
                        if (totalNilai < 8) {
                            binding.textViewKesimpulan.setText("Rekomendasi tidak diteruskan sebagai vendor");
                        } else if (totalNilai >= 8 && totalNilai <= 11) {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor dengan improvement");
                        } else {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor");
                        }
                        break;
                    case R.id.radioButton14:
                        pelayanan = 2;
                        totalNilai = mutu + harga + delivery + safety + pelayanan;
                        binding.textViewTotalNilai.setText("Total Nilai : " + totalNilai);
                        if (totalNilai < 8) {
                            binding.textViewKesimpulan.setText("Rekomendasi tidak diteruskan sebagai vendor");
                        } else if (totalNilai >= 8 && totalNilai <= 11) {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor dengan improvement");
                        } else {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor");
                        }
                        break;
                    case R.id.radioButton15:
                        pelayanan = 1;
                        totalNilai = mutu + harga + delivery + safety + pelayanan;
                        binding.textViewTotalNilai.setText("Total Nilai : " + totalNilai);
                        if (totalNilai < 8) {
                            binding.textViewKesimpulan.setText("Rekomendasi tidak diteruskan sebagai vendor");
                        } else if (totalNilai >= 8 && totalNilai <= 11) {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor dengan improvement");
                        } else {
                            binding.textViewKesimpulan.setText("Rekomendasi diteruskan sebagai vendor");
                        }
                        break;
                }
            }
        });

        binding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    binding.materialButtonAjukan.setEnabled(true);
                    binding.materialButtonAjukan.setBackgroundColor(getResources().getColor(R.color.primary));
                } else {
                    binding.materialButtonAjukan.setEnabled(false);
                    binding.materialButtonAjukan.setBackgroundColor(getResources().getColor(R.color.button_disable));
                }
            }
        });

        binding.materialButtonAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    progressDialog.show();

                    EvaluasiPekerjaanVendorModel model = new EvaluasiPekerjaanVendorModel(
                            AppPreference.getUser(v.getContext()).getIdUsers(),
                            idMapping,
                            binding.editTextPeriode.getText().toString(),
                            binding.editTextNamaVendor.getText().toString(),
                            binding.editTextNamaPekerjaan.getText().toString(),
                            binding.editTextSPKNo.getText().toString(),
                            tanggal,
                            new EvaluasiPekerjaanVendorModel.Refrensi(
                                    binding.editTextTroubleTicketNo.getText().toString(),
                                    binding.editTextWOExternalNo.getText().toString()
                            ),
                            new EvaluasiPekerjaanVendorModel.HasilPenilaian(
                                    mutu,
                                    harga,
                                    delivery,
                                    safety,
                                    pelayanan
                            ),
                            binding.textViewKesimpulan.getText().toString()
                    );

                    viewModel.postEvaluasiVendor(
                            model
                    ).observe(EvaluasiPekerjaanVendorActivity.this, new Observer<BaseResponse>() {
                        @Override
                        public void onChanged(BaseResponse baseResponse) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            if (baseResponse != null) {
                                if (baseResponse.isStatus()) {
                                    startActivity(new Intent(v.getContext(), ScreenFeedbackActivity.class));
                                } else {
                                    new AlertDialog.Builder(v.getContext())
                                            .setTitle("Pesan")
                                            .setMessage(baseResponse.getMessage())
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            })
                                            .create()
                                            .show();
                                }
                            } else {
                                new AlertDialog.Builder(v.getContext())
                                        .setTitle("Pesan")
                                        .setMessage("Terjadi kesalahan pada server, silahkan coba beberapa saat lagi")
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

        if (binding.editTextPeriode.getText().toString().isEmpty()) {
            binding.editTextPeriode.setError("Mohon isi data berikut");
            cek1 = false;
        }

        if (binding.editTextNamaVendor.getText().toString().isEmpty()) {
            binding.editTextNamaVendor.setError("Mohon isi data berikut");
            cek2 = false;
        }

        if (binding.editTextNamaPekerjaan.getText().toString().isEmpty()) {
            binding.editTextNamaPekerjaan.setError("Mohon isi data berikut");
            cek3 = false;
        }

        if (binding.editTextSPKNo.getText().toString().isEmpty()) {
            binding.editTextSPKNo.setError("Mohon isi data berikut");
            cek4 = false;
        }

        if (binding.editTextTanggalSPK.getText().toString().isEmpty()) {
            binding.editTextTanggalSPK.setError("Mohon isi data berikut");
            cek5 = false;
        }

        if (binding.editTextTroubleTicketNo.getText().toString().isEmpty()) {
            binding.editTextTroubleTicketNo.setError("Mohon isi data berikut");
            cek6 = false;
        }

        if (binding.editTextWOExternalNo.getText().toString().isEmpty()) {
            binding.editTextWOExternalNo.setError("Mohon isi data berikut");
            cek7 = false;
        }

        return cek1 && cek2 && cek3 && cek4 && cek5 && cek6 && cek7;
    }
}