package com.unitedtractors.android.unitedtractorsapp.view.activity.form.komplain_atau_usulan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListKomplainAtauUsulanBinding;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.adapter.KomplainAtauUsulanAdapter;
import com.unitedtractors.android.unitedtractorsapp.model.KomplainAtauUsulanModel;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.internal_work_order.KonfirmasiInternalWorkOrderActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.KomplainAtauUsulanViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListKomplainAtauUsulanActivity extends AppCompatActivity {
    private ActivityListKomplainAtauUsulanBinding binding;
    private KomplainAtauUsulanViewModel viewModel;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListKomplainAtauUsulanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        int jumlahKomplain = getIntent().getIntExtra("JUMLAH_KOMPLAIN", 0);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(KomplainAtauUsulanViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu Sebentar...");
        progressDialog.setCancelable(false);

        List<KomplainAtauUsulanModel.DetailKomplain> list = new ArrayList<>();
        for (int i = 0; i < jumlahKomplain; i++) {
            list.add(new KomplainAtauUsulanModel.DetailKomplain(
                    ""));
        }

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new KomplainAtauUsulanAdapter(list));

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

                    KomplainAtauUsulanModel model = new KomplainAtauUsulanModel(
                            AppPreference.getUser(v.getContext()).getIdUsers(),
                            idMapping,
                            KomplainAtauUsulanAdapter.getList()
                    );

                    viewModel.postKomplainUsulan(
                            model
                    ).observe(ListKomplainAtauUsulanActivity.this, new Observer<BaseResponse>() {
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private boolean checkData() {
        for (KomplainAtauUsulanModel.DetailKomplain model : KomplainAtauUsulanAdapter.getList()) {
            if (model.checkData() == false) {
                return false;
            }
        }
        return true;
    }
}