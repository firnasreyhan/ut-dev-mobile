package com.unitedtractors.android.unitedtractorsapp.view.activity.form.pembelian_snack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.unitedtractors.android.unitedtractorsapp.adapter.PembelianSnackAdapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListPembelianSnackBinding;
import com.unitedtractors.android.unitedtractorsapp.model.PembelianSnackModel;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanAssetModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ListPembelianSnackActivity extends AppCompatActivity {
    private ActivityListPembelianSnackBinding binding;
//    private PembelianSnackAdapter adapter;
    private Calendar calendar;
    private String serverDate;

    private String idMapping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListPembelianSnackBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        idMapping = getIntent().getStringExtra("ID_MAPPING");
        int jumlahPembelianSnack = getIntent().getIntExtra("JUMLAH_PEMBELIAN_SNACK", 0);

        calendar = Calendar.getInstance();

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<PembelianSnackModel.DetailPembelianSnackModel> list = new ArrayList<>();
        for (int i = 0; i < jumlahPembelianSnack; i++) {
            list.add(new PembelianSnackModel.DetailPembelianSnackModel("",""));
        }

//        List<PembelianSnackModel> list = new ArrayList<>();
//        list.add(new PembelianSnackModel("",""));
//        list.add(new PembelianSnackModel("",""));
//        list.add(new PembelianSnackModel("",""));
//        list.add(new PembelianSnackModel("",""));
//        list.add(new PembelianSnackModel("",""));
//        list.add(new PembelianSnackModel("",""));
//        list.add(new PembelianSnackModel("",""));
//        list.add(new PembelianSnackModel("",""));

//        adapter = new PembelianSnackAdapter(list, false);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new PembelianSnackAdapter(list, true));

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormatView = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
                SimpleDateFormat simpleDateFormatServer = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                binding.editTextTanggal.setText(simpleDateFormatView.format(calendar.getTime()));
                serverDate = simpleDateFormatServer.format(calendar.getTime());
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

//        binding.textViewTambahData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final EditText input = new EditText(v.getContext());
//                input.setInputType(InputType.TYPE_CLASS_NUMBER);
//                input.setHint("0");
//
//                LinearLayout linearLayout = new LinearLayout(v.getContext());
//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                input.setLayoutParams(layoutParams);
//                linearLayout.addView(input);
//                linearLayout.setPadding(60, 0, 60, 0);
//
//                new AlertDialog.Builder(v.getContext())
//                        .setTitle("Pesan")
//                        .setMessage("Masukkan jumlah data yang ingin ditambahkan.")
//                        .setView(linearLayout)
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                adapter.addData(Integer.parseInt(input.getText().toString()));
//                            }
//                        })
//                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        })
//                        .create()
//                        .show();
//            }
//        });

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.editTextDivisi.getText().toString().isEmpty()) {
                    binding.editTextDivisi.setError("Mohon isi data berikut.");
                }

                if (binding.editTextTanggal.getText().toString().isEmpty()) {
                    binding.editTextTanggal.setError("Mohon isi data berikut.");
                }

                if (binding.editTextKeperluan.getText().toString().isEmpty()) {
                    binding.editTextKeperluan.setError("Mohon isi data berikut.");
                }

                if (checkData() && !binding.editTextDivisi.getText().toString().isEmpty() && !binding.editTextTanggal.getText().toString().isEmpty() && !binding.editTextKeperluan.getText().toString().isEmpty()) {
                    Intent intent = new Intent(v.getContext(), KonfirmasiPembelianSnackActivity.class);
                    intent.putExtra("DIVISI", binding.editTextDivisi.getText().toString());
                    intent.putExtra("KEPERLUAN", binding.editTextKeperluan.getText().toString());
                    intent.putExtra("SERVER_TIME", serverDate);
                    intent.putExtra("VIEW_TIME", binding.editTextTanggal.getText().toString());
                    intent.putExtra("ID_MAPPING", idMapping);
                    startActivity(intent);
                } else {
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("Pesan")
                            .setMessage("Mohon untuk mengisi data dengan benar.")
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        List<PembelianSnackModel> list = new ArrayList<>();
//        list.addAll(PembelianSnackAdapter.getList());
//
//        for (int i = list.size(); i < 8; i++) {
//            list.add(new PembelianSnackModel("",""));
//        }
//
//        binding.recyclerView.setAdapter(new PembelianSnackAdapter(list, false));
//    }

    private boolean checkData() {
        int i = 0;
        for (PembelianSnackModel.DetailPembelianSnackModel model : PembelianSnackAdapter.getList()) {
            if (model.checkData()) {
                i++;
            }
        }

        return i > 0;
    }
}