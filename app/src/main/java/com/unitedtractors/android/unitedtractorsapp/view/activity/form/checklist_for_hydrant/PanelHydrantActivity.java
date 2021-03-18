package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_for_hydrant;

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
import com.unitedtractors.android.unitedtractorsapp.adapter.ChecklistForHydrantAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPanelHydrantBinding;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistForHydrantModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.PanelHydrantViewModel;

import java.util.ArrayList;
import java.util.List;

public class PanelHydrantActivity extends AppCompatActivity {
    private ActivityPanelHydrantBinding binding;
    private PanelHydrantViewModel viewModel;
    private ProgressDialog progressDialog;

    private ChecklistForHydrantModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPanelHydrantBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = ViewModelProviders.of(this).get(PanelHydrantViewModel.class);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String tanggal = getIntent().getStringExtra("TANGGAL");
        String lokasi = getIntent().getStringExtra("LOKASI");
        List<ChecklistForHydrantModel.DetailChecklistHydrant> systemPemipaan = (List<ChecklistForHydrantModel.DetailChecklistHydrant>) getIntent().getSerializableExtra("SYSTEM_PEMIPAAN");
        List<ChecklistForHydrantModel.DetailChecklistHydrant> jockeyPump = (List<ChecklistForHydrantModel.DetailChecklistHydrant>) getIntent().getSerializableExtra("JOCKEY_PUMP");
        List<ChecklistForHydrantModel.DetailChecklistHydrant> electricPump = (List<ChecklistForHydrantModel.DetailChecklistHydrant>) getIntent().getSerializableExtra("ELECTRIC_PUMP");
        List<ChecklistForHydrantModel.DetailChecklistHydrant> dieselHydrantPump = (List<ChecklistForHydrantModel.DetailChecklistHydrant>) getIntent().getSerializableExtra("DIESEL_HYDRANT_PUMP");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu Sebentar...");
        progressDialog.setCancelable(false);

        List<ChecklistForHydrantModel.DetailChecklistHydrant> list = new ArrayList<>();
        list.add(new ChecklistForHydrantModel.DetailChecklistHydrant(
                "Selector Switch",
                1
        ));
        list.add(new ChecklistForHydrantModel.DetailChecklistHydrant(
                "Lampu Indikator",
                1
        ));
        list.add(new ChecklistForHydrantModel.DetailChecklistHydrant(
                "Volt Meter",
                1
        ));
        list.add(new ChecklistForHydrantModel.DetailChecklistHydrant(
                "Ampere Meter",
                1
        ));
        list.add(new ChecklistForHydrantModel.DetailChecklistHydrant(
                "Konektor Panel",
                1
        ));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new ChecklistForHydrantAdapter(list));

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

                model = new ChecklistForHydrantModel(
                        AppPreference.getUser(v.getContext()).getIdUsers(),
                        idMapping,
                        tanggal,
                        lokasi,
                        binding.editTextCatatan.getText().toString().isEmpty() ? "-" : binding.editTextCatatan.getText().toString(),
                        systemPemipaan,
                        jockeyPump,
                        electricPump,
                        dieselHydrantPump,
                        ChecklistForHydrantAdapter.getList()
                );

                viewModel.postICH(
                        model
                ).observe(PanelHydrantActivity.this, new Observer<BaseResponse>() {
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