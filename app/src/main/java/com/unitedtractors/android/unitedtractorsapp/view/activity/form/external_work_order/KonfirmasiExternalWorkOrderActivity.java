package com.unitedtractors.android.unitedtractorsapp.view.activity.form.external_work_order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.adapter.ExternalWorkOrderAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKonfirmasiExternalWorkOrderBinding;
import com.unitedtractors.android.unitedtractorsapp.model.ExternalWorkOrderModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.pembelian_snack.KonfirmasiPembelianSnackActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.KonfirmasiExternalWorkOrderViewModel;

public class KonfirmasiExternalWorkOrderActivity extends AppCompatActivity {
    private ActivityKonfirmasiExternalWorkOrderBinding binding;
    private KonfirmasiExternalWorkOrderViewModel viewModel;
    private ProgressDialog progressDialog;
    private ExternalWorkOrderModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKonfirmasiExternalWorkOrderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = ViewModelProviders.of(this).get(KonfirmasiExternalWorkOrderViewModel.class);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu sebentar...");
        progressDialog.setCancelable(false);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String diintruksikanKepada = getIntent().getStringExtra("DIINTRUKSIKAN_KEPADA");
        String intruksiDari = getIntent().getStringExtra("INTRUKSI_DARI");
        String pekerjaan = getIntent().getStringExtra("PEKERJAAN");
        String regNo = getIntent().getStringExtra("REG_NO");
        String requestDateView = getIntent().getStringExtra("REQUEST_DATE_VIEW");
        String requestDateServer = getIntent().getStringExtra("REQUEST_DATE_SERVER");
        String pages = getIntent().getStringExtra("PAGES");
        String cc = getIntent().getStringExtra("CC");

        model = new ExternalWorkOrderModel(
                AppPreference.getUser(this).getIdUsers(),
                idMapping,
                diintruksikanKepada,
                intruksiDari,
                AppPreference.getUser(this).getDivUsers(),
                pekerjaan,
                regNo,
                requestDateServer,
                pages,
                cc,
                ExternalWorkOrderAdapter.getList()
        );

        binding.textViewDiintruksikanKepada.setText(diintruksikanKepada);
        binding.textViewIntruksiDari.setText(intruksiDari);
        binding.textViewDeptDiv.setText(AppPreference.getUser(this).getDivUsers());
        binding.textViewPekerjaan.setText(pekerjaan);
        binding.textViewRegNo.setText(regNo);
        binding.textViewRequestDate.setText(requestDateView);
        binding.textViewPages.setText(pages);
        binding.textViewCC.setText(cc);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new ExternalWorkOrderAdapter(ExternalWorkOrderAdapter.getList(), false));

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
                progressDialog.show();
                viewModel.postExternalWorkOrder(
                        model
                ).observe(KonfirmasiExternalWorkOrderActivity.this, new Observer<BaseResponse>() {
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
                                    .setMessage("Terjadi kesalah pada server, silahkan coba beberapa saat lagi")
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
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}