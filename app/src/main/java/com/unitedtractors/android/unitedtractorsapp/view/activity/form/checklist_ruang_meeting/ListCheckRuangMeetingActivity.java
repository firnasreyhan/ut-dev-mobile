package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_ruang_meeting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListCheckRuangMeetingBinding;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistRuangMeetingModel;
import com.unitedtractors.android.unitedtractorsapp.model.PembelianSnackModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.pembelian_snack.KonfirmasiPembelianSnackActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.ChecklistRuangMeetingViewModel;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.PermintaanCateringRegulerViewModel;

public class ListCheckRuangMeetingActivity extends AppCompatActivity {
    private com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListCheckRuangMeetingBinding binding;
    public ChecklistRuangMeetingModel model;
    ChecklistRuangMeetingViewModel viewModel;

    private int lcd;
    private  int viewer;
    private int spidol;
    private int whiteBoard;
    private int screen;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListCheckRuangMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = ViewModelProviders.of(this).get(ChecklistRuangMeetingViewModel.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu Sebentar...");
        progressDialog.setCancelable(false);

        binding.materialButtonAjukan.setEnabled(true);
        binding.materialButtonAjukan.setBackgroundColor(getResources().getColor(R.color.primary));

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String tglCekView = getIntent().getStringExtra("TGL_CEK_VIEW");
        String tglCekServer = getIntent().getStringExtra("TGL_CEK_SERVER");
        String jamCek = getIntent().getStringExtra("JAM_CEK");
        String namaCek = getIntent().getStringExtra("NAMA_CEK");

        binding.radioGroupLcd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                lcd = group.getCheckedRadioButtonId();
            }
        });

        binding.radioGroupScreen.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                screen = group.getCheckedRadioButtonId();
            }
        });

        binding.radioGroupWhiteBoard.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                whiteBoard = group.getCheckedRadioButtonId();
            }
        });

        binding.radioGroupSpidol.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                spidol = group.getCheckedRadioButtonId();
            }
        });

        binding.radioGroupViewer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                viewer = group.getCheckedRadioButtonId();
            }
        });


        model = new ChecklistRuangMeetingModel(
                idMapping,
                AppPreference.getUser(this).getIdUsers(),
                namaCek,
                tglCekServer,
                jamCek,
                viewer,
                whiteBoard,
                lcd,
                screen,
                spidol);

        binding.materialButtonAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Mohon tunggu sebentar...");
                progressDialog.show();

                viewModel.postChecklistRuangMeeting(
                        model
                ).observe(ListCheckRuangMeetingActivity.this, new Observer<BaseResponse>() {
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

//    public boolean checkData()  {
//        if (!binding.r.getText().toString().isEmpty()
//                && !binding.editTextKeterangan2.getText().toString().isEmpty()
//                && !binding.editTextKeterangan3.getText().toString().isEmpty()
//                && !binding.editTextKeterangan4.getText().toString().isEmpty()
//                && !binding.editTextKeterangan5.getText().toString().isEmpty()
//                && !binding.editTextKeterangan6.getText().toString().isEmpty()) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}