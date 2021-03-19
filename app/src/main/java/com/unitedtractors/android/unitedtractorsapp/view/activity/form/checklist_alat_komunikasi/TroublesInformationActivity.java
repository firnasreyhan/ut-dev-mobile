package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_alat_komunikasi;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityTroublesInformation2Binding;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistAlatKomunikasiModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.ChecklistAlatkomunikasiViewModel;

import java.util.List;

public class TroublesInformationActivity extends AppCompatActivity {
    private ActivityTroublesInformation2Binding binding;
    private ChecklistAlatkomunikasiViewModel viewModel;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTroublesInformation2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String tgl = getIntent().getStringExtra("TGL");
        String lokasi = getIntent().getStringExtra("LOKASI");
        List<ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi> pabx = (List<ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi>) getIntent().getSerializableExtra("PABX");
        List<ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi> repeater = (List<ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi>) getIntent().getSerializableExtra("REPEATER");
        List<ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi> radio = (List<ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi>) getIntent().getSerializableExtra("RADIO");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(ChecklistAlatkomunikasiViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu Sebentar...");
        progressDialog.setCancelable(false);

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

                ChecklistAlatKomunikasiModel model = new ChecklistAlatKomunikasiModel(
                        AppPreference.getUser(v.getContext()).getIdUsers(),
                        idMapping,
                        tgl,
                        lokasi,
                        pabx,
                        repeater,
                        radio,
                        binding.editTextKeterangan.getText().toString().isEmpty() ? "-" : binding.editTextKeterangan.getText().toString(),
                        binding.editTextProblem.getText().toString().isEmpty() ? "-" : binding.editTextProblem.getText().toString(),
                        binding.editTextRootCause.getText().toString().isEmpty() ? "-" : binding.editTextRootCause.getText().toString(),
                        binding.editTextCorrectiveAction.getText().toString().isEmpty() ? "-" : binding.editTextCorrectiveAction.getText().toString(),
                        binding.editTextPreventiveAction.getText().toString().isEmpty() ? "-" : binding.editTextPreventiveAction.getText().toString(),
                        binding.editTextDeadline.getText().toString().isEmpty() ? "-" : binding.editTextDeadline.getText().toString(),
                        binding.editTextPIC.getText().toString().isEmpty() ? "-" : binding.editTextPIC.getText().toString()
                );

                viewModel.postAlatKomunikasi(
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
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}