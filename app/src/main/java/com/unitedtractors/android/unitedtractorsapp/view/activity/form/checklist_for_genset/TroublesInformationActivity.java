package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_for_genset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityTroublesInformationBinding;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistForGensetModel;
import com.unitedtractors.android.unitedtractorsapp.model.Pertanyaan2Model;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas.KonfirmasiPermintaanMobilDinasActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.TroublesInformationViewModel;

import java.util.ArrayList;
import java.util.List;

public class TroublesInformationActivity extends AppCompatActivity {
    private ActivityTroublesInformationBinding binding;
    private TroublesInformationViewModel viewModel;
    private ProgressDialog progressDialog;

    private ChecklistForGensetModel model;
    private int keadaanGenset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTroublesInformationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = ViewModelProviders.of(this).get(TroublesInformationViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu Sebentar...");
        progressDialog.setCancelable(false);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String tanggal = getIntent().getStringExtra("TANGGAL");
        String tanggalView = getIntent().getStringExtra("TANGGAL_VIEW");
        String lokasi = getIntent().getStringExtra("LOKASI");
        String engine = getIntent().getStringExtra("ENGINE");
        String engineModel = getIntent().getStringExtra("ENGINE_MODEL");
        String serialNo = getIntent().getStringExtra("SERIAL_NO");
        String genoType = getIntent().getStringExtra("GENO_TYPE");
        String serialNo2 = getIntent().getStringExtra("SERIAL_NO_2");
        String hour_meter = getIntent().getStringExtra("HOUR_METER");
        List<Pertanyaan2Model> unitEngine = (List<Pertanyaan2Model>) getIntent().getSerializableExtra("UNIT_ENGINE");
        List<Pertanyaan2Model> controlSystem = (List<Pertanyaan2Model>) getIntent().getSerializableExtra("CONTROL_SYSTEM");
        List<Pertanyaan2Model> k5 = (List<Pertanyaan2Model>) getIntent().getSerializableExtra("K5");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonSiapDioprasikan:
                        keadaanGenset = 1;
                        break;
                    case R.id.radioButtonBelumSiapDioprasikan:
                        keadaanGenset = 0;
                        break;
                }
            }
        });

        binding.materialButtonAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    model = new ChecklistForGensetModel(
                            AppPreference.getUser(v.getContext()).getIdUsers(),
                            idMapping,
                            tanggal,
                            lokasi,
                            engine,
                            engineModel,
                            serialNo,
                            genoType,
                            serialNo2,
                            hour_meter,
                            list(unitEngine),
                            list(controlSystem),
                            list(k5),
                            binding.editTextProblemIdentification.getText().toString(),
                            binding.editTextRootCause.getText().toString(),
                            binding.editTextCorrectiveAction.getText().toString(),
                            binding.editTextPreventiveAction.getText().toString(),
                            binding.editTextDeadlinePIC.getText().toString(),
                            binding.editTextStatus.getText().toString(),
                            String.valueOf(keadaanGenset)
                    );

                    progressDialog.show();
                    viewModel.postICGS(
                            model
                    ).observe(TroublesInformationActivity.this, new Observer<BaseResponse>() {
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
        boolean cek5 = true;
        boolean cek6 = true;
        boolean cek7 = true;

        if (binding.editTextProblemIdentification.getText().toString().isEmpty()) {
            binding.editTextProblemIdentification.setError("Mohon isi data berikut");
            cek1 = false;
        }

        if (binding.editTextRootCause.getText().toString().isEmpty()) {
            binding.editTextRootCause.setError("Mohon isi data berikut");
            cek2 = false;
        }

        if (binding.editTextCorrectiveAction.getText().toString().isEmpty()) {
            binding.editTextCorrectiveAction.setError("Mohon isi data berikut");
            cek3 = false;
        }

        if (binding.editTextPreventiveAction.getText().toString().isEmpty()) {
            binding.editTextPreventiveAction.setError("Mohon isi data berikut");
            cek5 = false;
        }

        if (binding.editTextDeadlinePIC.getText().toString().isEmpty()) {
            binding.editTextDeadlinePIC.setError("Mohon isi data berikut");
            cek6 = false;
        }

        if (binding.editTextStatus.getText().toString().isEmpty()) {
            binding.editTextStatus.setError("Mohon isi data berikut");
            cek7 = false;
        }

        return cek1 && cek2 && cek3 && cek5 && cek6 && cek7;
    }

    private List<Pertanyaan2Model> list(List<Pertanyaan2Model> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCatatan().isEmpty()) {
                list.get(i).setCatatan("-");
            }
        }
        return list;
    }
}