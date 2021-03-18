package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_ruang_meeting;

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

import com.github.dewinjm.monthyearpicker.MonthFormat;
import com.github.dewinjm.monthyearpicker.MonthYearPickerDialog;
import com.github.dewinjm.monthyearpicker.MonthYearPickerDialogFragment;
import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.adapter.MingguRuangMeetingAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguRuangMeetingEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguRuangMeetingEntity;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityChecklistRuangMeetingBinding;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistRuangMeetingModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.ChecklistRuangMeetingViewModel;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChecklistRuangMeetingActivity extends AppCompatActivity {
    private ActivityChecklistRuangMeetingBinding binding;
    private ChecklistRuangMeetingViewModel viewModel;
    private ProgressDialog progressDialog;
    private Calendar calendar;

    private String idMapping, tanggal;
    private boolean isValid;
    private int currentYear;
    private int yearSelected;
    private int monthSelected;

    private ChecklistRuangMeetingModel.DetailChecklistRuangMeeting detailMinggu1, detailMinggu2, detailMinggu3, detailMinggu4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChecklistRuangMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        idMapping = getIntent().getStringExtra("ID_MAPPING");
        detailMinggu1 = new ChecklistRuangMeetingModel.DetailChecklistRuangMeeting();
        detailMinggu2 = new ChecklistRuangMeetingModel.DetailChecklistRuangMeeting();
        detailMinggu3 = new ChecklistRuangMeetingModel.DetailChecklistRuangMeeting();
        detailMinggu4 = new ChecklistRuangMeetingModel.DetailChecklistRuangMeeting();

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(ChecklistRuangMeetingViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu sebentar...");
        progressDialog.setCancelable(false);

        calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        yearSelected = currentYear;
        monthSelected = calendar.get(Calendar.MONTH);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(ChecklistRuangMeetingActivity.this));

        viewModel.getMinggu().observe(this, new Observer<List<MingguRuangMeetingEntity>>() {
            @Override
            public void onChanged(List<MingguRuangMeetingEntity> mingguEntities) {
                if (mingguEntities.isEmpty()) {
                    viewModel.insertMinggu(mingguEntity("Minggu Ke 1", false));
                    viewModel.insertMinggu(mingguEntity("Minggu Ke 2", false));
                    viewModel.insertMinggu(mingguEntity("Minggu Ke 3", false));
                    viewModel.insertMinggu(mingguEntity("Minggu Ke 4", false));
                } else {
                    binding.recyclerView.setAdapter(new MingguRuangMeetingAdapter(mingguEntities));

                    isValid = mingguEntities.get(0).status && mingguEntities.get(1).status && mingguEntities.get(2).status && mingguEntities.get(3).status;
                }
            }
        });

        binding.editTextBulanTahun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayMonthYearPickerDialogFragment();
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

                    ChecklistRuangMeetingModel model = new ChecklistRuangMeetingModel(
                            AppPreference.getUser(v.getContext()).getIdUsers(),
                            idMapping,
                            binding.editTextLokasi.getText().toString(),
                            tanggal,
                            detailMinggu1,
                            detailMinggu2,
                            detailMinggu3,
                            detailMinggu4
                    );

                    viewModel.postChecklistRuangMeeting(
                            model
                    ).observe(ChecklistRuangMeetingActivity.this, new Observer<BaseResponse>() {
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
                    Toast.makeText(ChecklistRuangMeetingActivity.this, "Mohon isi semua data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private MingguRuangMeetingEntity mingguEntity(String mingguKe, boolean status) {
        MingguRuangMeetingEntity entity = new MingguRuangMeetingEntity();
        entity.mingguKe = mingguKe;
        entity.status = status;
        return entity;
    }

    private void detailMinggu(int mingguKe) {
        viewModel.getDetailMinggu(
                mingguKe
        ).observe(this, new Observer<List<DetailMingguRuangMeetingEntity>>() {
            @Override
            public void onChanged(List<DetailMingguRuangMeetingEntity> detailMingguEntities) {
                if (detailMingguEntities != null) {
                    if (!detailMingguEntities.isEmpty()) {
                        if (detailMingguEntities.size() == 5) {
                            if (mingguKe == 1) {
                                detailMinggu1.setTanggal(detailMingguEntities.get(0).tanggal);
                                List<Integer> list =  new ArrayList<>();
                                for (DetailMingguRuangMeetingEntity entity : detailMingguEntities) {
                                    list.add(entity.status);
                                }
                                detailMinggu1.setList(list);
                            } else if (mingguKe == 2) {
                                detailMinggu2.setTanggal(detailMingguEntities.get(0).tanggal);
                                List<Integer> list =  new ArrayList<>();
                                for (DetailMingguRuangMeetingEntity entity : detailMingguEntities) {
                                    list.add(entity.status);
                                }
                                detailMinggu2.setList(list);
                            } else if (mingguKe == 3) {
                                detailMinggu3.setTanggal(detailMingguEntities.get(0).tanggal);
                                List<Integer> list =  new ArrayList<>();
                                for (DetailMingguRuangMeetingEntity entity : detailMingguEntities) {
                                    list.add(entity.status);
                                }
                                detailMinggu3.setList(list);
                            } else if (mingguKe == 4) {
                                detailMinggu4.setTanggal(detailMingguEntities.get(0).tanggal);
                                List<Integer> list =  new ArrayList<>();
                                for (DetailMingguRuangMeetingEntity entity : detailMingguEntities) {
                                    list.add(entity.status);
                                }
                                detailMinggu4.setList(list);
                            }
                        }
                    }
                }
            }
        });
    }

    private void displayMonthYearPickerDialogFragment() {
        MonthYearPickerDialogFragment dialogFragment =  createDialog();

        dialogFragment.setOnDateSetListener(new MonthYearPickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(int year, int monthOfYear) {
                monthSelected = monthOfYear;
                yearSelected = year;
                updateViews();
            }
        });

        dialogFragment.show(getSupportFragmentManager(), null);
    }

    private MonthYearPickerDialogFragment createDialog() {
        return MonthYearPickerDialogFragment
                .getInstance(monthSelected,
                        yearSelected,
                        "Pilih Bulan dan Tahun",
                        MonthFormat.LONG);
    }

    private void updateViews() {
        String month = new DateFormatSymbols().getMonths()[monthSelected];
        if (monthSelected == 0) {
            month = "Januari";
        } else if (monthSelected == 1) {
            month = "Febuari";
        } else if (monthSelected == 2) {
            month = "Maret";
        } else if (monthSelected == 3) {
            month = "April";
        } else if (monthSelected == 4) {
            month = "Mei";
        } else if (monthSelected == 5) {
            month = "Juni";
        } else if (monthSelected == 6) {
            month = "Juli";
        } else if (monthSelected == 7) {
            month = "Agustus";
        } else if (monthSelected == 8) {
            month = "September";
        } else if (monthSelected == 9) {
            month = "Oktober";
        } else if (monthSelected == 10) {
            month = "November";
        } else if (monthSelected == 11) {
            month = "Desember";
        }
        binding.editTextBulanTahun.setText(String.format("%s / %s", month, yearSelected));
        tanggal = yearSelected + "-" + (monthSelected + 1) + "-01";
    }
}