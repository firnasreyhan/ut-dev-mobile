package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_pompa_pond;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.PostMobilResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityCheckingRunningTestBinding;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistPompaPondModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas.KonfirmasiPermintaanMobilDinasActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.CheckingRunningTestViewModel;

import java.util.ArrayList;
import java.util.List;

public class CheckingRunningTestActivity extends AppCompatActivity {
    private ActivityCheckingRunningTestBinding binding;
    private CheckingRunningTestViewModel viewModel;
    private ProgressDialog progressDialog;

    private ChecklistPompaPondModel model;

    private int checking1 = 1;
    private int checking2 = 1;
    private int checking3 = 1;
    private int checking4 = 1;
    private int checking5 = 1;

    private int running1 = 1;
    private int running2 = 1;
    private int running3 = 1;
    private int running4 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckingRunningTestBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = ViewModelProviders.of(this).get(CheckingRunningTestViewModel.class);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String tanggal = getIntent().getStringExtra("TANGGAL");
        String pondA = getIntent().getStringExtra("POND_A");
        String pondB = getIntent().getStringExtra("POND_B");
        String pondC = getIntent().getStringExtra("POND_C");
        String pondD = getIntent().getStringExtra("POND_D");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu Sebentar...");
        progressDialog.setCancelable(false);

        binding.radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton1:
                        checking1 = 1;
                        break;
                    case R.id.radioButton2:
                        checking1 = 2;
                        break;
                    case R.id.radioButton3:
                        checking1 = 3;
                        break;
                }
            }
        });

        binding.radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton4:
                        checking2 = 1;
                        break;
                    case R.id.radioButton5:
                        checking2 = 2;
                        break;
                    case R.id.radioButton6:
                        checking2 = 3;
                        break;
                }
            }
        });

        binding.radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton7:
                        checking3 = 1;
                        break;
                    case R.id.radioButton8:
                        checking3 = 2;
                        break;
                    case R.id.radioButton9:
                        checking3 = 3;
                        break;
                }
            }
        });

        binding.radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton10:
                        checking4 = 1;
                        break;
                    case R.id.radioButton11:
                        checking4 = 2;
                        break;
                    case R.id.radioButton12:
                        checking4 = 3;
                        break;
                }
            }
        });

        binding.radioGroup5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton13:
                        checking5 = 1;
                        break;
                    case R.id.radioButton14:
                        checking5 = 2;
                        break;
                    case R.id.radioButton15:
                        checking5 = 3;
                        break;
                }
            }
        });

        binding.radioGroup6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton16:
                        running1 = 1;
                        break;
                    case R.id.radioButton17:
                        running1 = 2;
                        break;
                    case R.id.radioButton18:
                        running1 = 3;
                        break;
                }
            }
        });

        binding.radioGroup7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton19:
                        running2 = 1;
                        break;
                    case R.id.radioButton20:
                        running2 = 2;
                        break;
                    case R.id.radioButton21:
                        running2 = 3;
                        break;
                }
            }
        });

        binding.radioGroup8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton22:
                        running3 = 1;
                        break;
                    case R.id.radioButton23:
                        running3 = 2;
                        break;
                    case R.id.radioButton24:
                        running3 = 3;
                        break;
                }
            }
        });

        binding.radioGroup9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton25:
                        running4 = 1;
                        break;
                    case R.id.radioButton26:
                        running4 = 2;
                        break;
                    case R.id.radioButton27:
                        running4 = 3;
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
                progressDialog.show();

                List<String> checking = new ArrayList<>();
                checking.add(String.valueOf(checking1));
                checking.add(String.valueOf(checking2));
                checking.add(String.valueOf(checking3));
                checking.add(String.valueOf(checking4));
                checking.add(String.valueOf(checking5));

                List<String> running = new ArrayList<>();
                running.add(String.valueOf(running1));
                running.add(String.valueOf(running2));
                running.add(String.valueOf(running3));
                running.add(String.valueOf(running4));

                model = new ChecklistPompaPondModel(
                        AppPreference.getUser(v.getContext()).getIdUsers(),
                        idMapping,
                        tanggal,
                        pondA,
                        pondB,
                        pondC,
                        pondD,
                        checking,
                        running,
                        binding.editTextCatatan.getText().toString().isEmpty() ? "-" : binding.editTextCatatan.getText().toString()
                );

                viewModel.postICGS(
                        model
                ).observe(CheckingRunningTestActivity.this, new Observer<BaseResponse>() {
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