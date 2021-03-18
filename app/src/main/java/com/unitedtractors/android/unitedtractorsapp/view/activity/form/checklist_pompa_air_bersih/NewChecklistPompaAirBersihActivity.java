package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_pompa_air_bersih;

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
import android.widget.Toast;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.adapter.MingguAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguEntity;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityNewChecklistPompaAirBersihBinding;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistPompaAirBersihModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.ChecklistPompaAirBersihViewModel;

import java.util.List;

public class NewChecklistPompaAirBersihActivity extends AppCompatActivity {
    private ActivityNewChecklistPompaAirBersihBinding binding;
    private ChecklistPompaAirBersihViewModel viewModel;
    private ProgressDialog progressDialog;

    private String idMapping;
    private boolean isValid;

    private ChecklistPompaAirBersihModel.DetailMinggu detailMinggu1, detailMinggu2, detailMinggu3, detailMinggu4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewChecklistPompaAirBersihBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        idMapping = getIntent().getStringExtra("ID_MAPPING");
        detailMinggu1 = new ChecklistPompaAirBersihModel.DetailMinggu();
        detailMinggu1.setTgl("2020-01-01");

        detailMinggu2 = new ChecklistPompaAirBersihModel.DetailMinggu();
        detailMinggu2.setTgl("2020-01-01");

        detailMinggu3 = new ChecklistPompaAirBersihModel.DetailMinggu();
        detailMinggu3.setTgl("2020-01-01");

        detailMinggu4 = new ChecklistPompaAirBersihModel.DetailMinggu();
        detailMinggu4.setTgl("2020-01-01");

        viewModel = ViewModelProviders.of(this).get(ChecklistPompaAirBersihViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu sebentar...");
        progressDialog.setCancelable(false);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(NewChecklistPompaAirBersihActivity.this));

        viewModel.getMinggu().observe(this, new Observer<List<MingguEntity>>() {
            @Override
            public void onChanged(List<MingguEntity> mingguEntities) {
                if (mingguEntities.isEmpty()) {
                    viewModel.insertMinggu(mingguEntity("Minggu Ke 1", false));
                    viewModel.insertMinggu(mingguEntity("Minggu Ke 2", false));
                    viewModel.insertMinggu(mingguEntity("Minggu Ke 3", false));
                    viewModel.insertMinggu(mingguEntity("Minggu Ke 4", false));
                } else {
                    binding.recyclerView.setAdapter(new MingguAdapter(mingguEntities));

                    isValid = mingguEntities.get(0).status && mingguEntities.get(1).status && mingguEntities.get(2).status && mingguEntities.get(3).status;
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

        detailMinggu(1);
        detailMinggu(2);
        detailMinggu(3);
        detailMinggu(4);

        binding.materialButtonAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid) {
                    progressDialog.show();

                    detailMinggu(1);
                    detailMinggu(2);
                    detailMinggu(3);
                    detailMinggu(4);

                    ChecklistPompaAirBersihModel model = new ChecklistPompaAirBersihModel(
                            AppPreference.getUser(v.getContext()).getIdUsers(),
                            idMapping,
                            "2020-01-01",
                            binding.editTextLokasi.getText().toString(),
                            detailMinggu1,
                            detailMinggu2,
                            detailMinggu3,
                            detailMinggu4
                    );

                    viewModel.postICPAB(
                            model
                    ).observe(NewChecklistPompaAirBersihActivity.this, new Observer<BaseResponse>() {
                        @Override
                        public void onChanged(BaseResponse baseResponse) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            if (baseResponse != null) {
                                if (baseResponse.isStatus()) {
                                    viewModel.deleteDetaiAlllMinggu();
                                    viewModel.updateAllMinggu(false);
                                    binding.checkBox.setChecked(false);

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

//                    viewModel.deleteDetaiAlllMinggu();
//                    viewModel.updateAllMinggu(false);
//                    binding.checkBox.setChecked(false);
                } else {
                    Toast.makeText(NewChecklistPompaAirBersihActivity.this, "Mohon isi semua data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private MingguEntity mingguEntity(String mingguKe, boolean status) {
        MingguEntity entity = new MingguEntity();
        entity.mingguKe = mingguKe;
        entity.status = status;
        return entity;
    }

    private void detailMinggu(int mingguKe) {
        viewModel.getDetailMinggu(
                mingguKe
        ).observe(this, new Observer<List<DetailMingguEntity>>() {
            @Override
            public void onChanged(List<DetailMingguEntity> detailMingguEntities) {
                if (detailMingguEntities != null) {
                    if (!detailMingguEntities.isEmpty()) {
                        if (mingguKe == 1) {
                            detailMinggu1.setKondAir(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(0).status == 1 ? true : false, detailMingguEntities.get(0).catatan));
                            detailMinggu1.setAirPancingan(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(1).status == 1 ? true : false, detailMingguEntities.get(1).catatan));
                            detailMinggu1.setIndikator(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(2).status == 1 ? true : false, detailMingguEntities.get(2).catatan));
                            detailMinggu1.setTekUdara(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(3).status == 1 ? true : false, detailMingguEntities.get(3).catatan));
                            detailMinggu1.setFlowMeter(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(4).status == 1 ? true : false, detailMingguEntities.get(4).catatan));
                            detailMinggu1.setSupplyAir(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(5).status == 1 ? true : false, detailMingguEntities.get(5).catatan));
                            detailMinggu1.setManSupply(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(6).status == 1 ? true : false, detailMingguEntities.get(6).catatan));
                            detailMinggu1.setFungsiPanel(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(7).status == 1 ? true : false, detailMingguEntities.get(7).catatan));
                        } else if (mingguKe == 2) {
                            detailMinggu2.setKondAir(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(0).status == 1 ? true : false, detailMingguEntities.get(0).catatan));
                            detailMinggu2.setAirPancingan(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(1).status == 1 ? true : false, detailMingguEntities.get(1).catatan));
                            detailMinggu2.setIndikator(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(2).status == 1 ? true : false, detailMingguEntities.get(2).catatan));
                            detailMinggu2.setTekUdara(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(3).status == 1 ? true : false, detailMingguEntities.get(3).catatan));
                            detailMinggu2.setFlowMeter(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(4).status == 1 ? true : false, detailMingguEntities.get(4).catatan));
                            detailMinggu2.setSupplyAir(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(5).status == 1 ? true : false, detailMingguEntities.get(5).catatan));
                            detailMinggu2.setManSupply(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(6).status == 1 ? true : false, detailMingguEntities.get(6).catatan));
                            detailMinggu2.setFungsiPanel(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(7).status == 1 ? true : false, detailMingguEntities.get(7).catatan));
                        } else if (mingguKe == 3) {
                            detailMinggu3.setKondAir(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(0).status == 1 ? true : false, detailMingguEntities.get(0).catatan));
                            detailMinggu3.setAirPancingan(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(1).status == 1 ? true : false, detailMingguEntities.get(1).catatan));
                            detailMinggu3.setIndikator(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(2).status == 1 ? true : false, detailMingguEntities.get(2).catatan));
                            detailMinggu3.setTekUdara(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(3).status == 1 ? true : false, detailMingguEntities.get(3).catatan));
                            detailMinggu3.setFlowMeter(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(4).status == 1 ? true : false, detailMingguEntities.get(4).catatan));
                            detailMinggu3.setSupplyAir(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(5).status == 1 ? true : false, detailMingguEntities.get(5).catatan));
                            detailMinggu3.setManSupply(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(6).status == 1 ? true : false, detailMingguEntities.get(6).catatan));
                            detailMinggu3.setFungsiPanel(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(7).status == 1 ? true : false, detailMingguEntities.get(7).catatan));
                        } else if (mingguKe == 4) {
                            detailMinggu4.setKondAir(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(0).status == 1 ? true : false, detailMingguEntities.get(0).catatan));
                            detailMinggu4.setAirPancingan(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(1).status == 1 ? true : false, detailMingguEntities.get(1).catatan));
                            detailMinggu4.setIndikator(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(2).status == 1 ? true : false, detailMingguEntities.get(2).catatan));
                            detailMinggu4.setTekUdara(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(3).status == 1 ? true : false, detailMingguEntities.get(3).catatan));
                            detailMinggu4.setFlowMeter(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(4).status == 1 ? true : false, detailMingguEntities.get(4).catatan));
                            detailMinggu4.setSupplyAir(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(5).status == 1 ? true : false, detailMingguEntities.get(5).catatan));
                            detailMinggu4.setManSupply(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(6).status == 1 ? true : false, detailMingguEntities.get(6).catatan));
                            detailMinggu4.setFungsiPanel(new ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist(detailMingguEntities.get(7).status == 1 ? true : false, detailMingguEntities.get(7).catatan));
                        }
                    }
                }
            }
        });
    }
}