package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_ruang_meeting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.unitedtractors.android.unitedtractorsapp.adapter.ChecklistRuangMeetingAdapter;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguRuangMeetingEntity;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListCheckRuangMeetingBinding;
import com.unitedtractors.android.unitedtractorsapp.model.Pertanyaan4Model;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.ListChecklistRuangMeetingViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ListCheckRuangMeetingActivity extends AppCompatActivity {
    private ActivityListCheckRuangMeetingBinding binding;
    private ListChecklistRuangMeetingViewModel viewModel;

    private Calendar calendar;

    private int id;
    private String tanggal, tanggalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListCheckRuangMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        id = getIntent().getIntExtra("ID", 0);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(ListChecklistRuangMeetingViewModel.class);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);

        List<Pertanyaan4Model> list = new ArrayList<>();

        viewModel.getDetailMinggu(
                id
        ).observe(this, new Observer<List<DetailMingguRuangMeetingEntity>>() {
            @Override
            public void onChanged(List<DetailMingguRuangMeetingEntity> detailMingguEntities) {
                if (detailMingguEntities.isEmpty()) {
                    list.add(new Pertanyaan4Model(
                            "Viewer",
                            1
                    ));
                    list.add(new Pertanyaan4Model(
                            "White Board",
                            1
                    ));
                    list.add(new Pertanyaan4Model(
                            "LCD",
                            1
                    ));
                    list.add(new Pertanyaan4Model(
                            "Screen",
                            1
                    ));
                    list.add(new Pertanyaan4Model(
                            "Spidol",
                            1
                    ));
                } else {
                    for (DetailMingguRuangMeetingEntity entity : detailMingguEntities) {
                        list.add(model(entity));
                        binding.editTextTanggal.setText(entity.tanggalView);
                        binding.editTextJam.setText(entity.jam);
                    }
                }
                binding.recyclerView.setAdapter(new ChecklistRuangMeetingAdapter(list));
            }
        });

        calendar = Calendar.getInstance();

        SimpleDateFormat simpleDateFormatView = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
        SimpleDateFormat simpleDateFormatServer = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                binding.editTextTanggal.setText(simpleDateFormatView.format(calendar.getTime()));
                tanggalView = simpleDateFormatView.format(calendar.getTime());
                tanggal = simpleDateFormatServer.format(calendar.getTime());
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

        binding.editTextJam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int min = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePicker = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String hour = hourOfDay < 10 ? ("0"+ hourOfDay) : String.valueOf(hourOfDay);
                        String sMinute = minute < 10 ? ("0"+ minute) : String.valueOf(minute);
                        String time = hour + ":" + sMinute;
                        binding.editTextJam.setText(time);
                    }
                }, hour, min, true);

                timePicker.show();
            }
        });

        binding.materialButtonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    viewModel.deleteDetailMinggu(id);
                    for (Pertanyaan4Model model : ChecklistRuangMeetingAdapter.getList()) {
                        viewModel.insertDetailMinggu(entity(model));
                    }
                    viewModel.updateMinggu(id, true);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private DetailMingguRuangMeetingEntity entity(Pertanyaan4Model model) {
        DetailMingguRuangMeetingEntity entity = new DetailMingguRuangMeetingEntity();
        entity.mingguKe = id;
        entity.pertanyaan = model.getPertanyaan();
        entity.status = model.getStatus();
        entity.tanggal = tanggal;
        entity.tanggalView = tanggalView;
        entity.jam = binding.editTextJam.getText().toString() + ":00";
        return entity;
    }

    private Pertanyaan4Model model(DetailMingguRuangMeetingEntity entity) {
        Pertanyaan4Model model = new Pertanyaan4Model(
                entity.pertanyaan,
                entity.status
        );
        return model;
    }

    private boolean checkData() {
        boolean cek1 = true;
        boolean cek2 = true;

        if (binding.editTextTanggal.getText().toString().isEmpty()) {
            binding.editTextTanggal.setError("Mohon isi data berikut");
            cek1 = false;
        }

        if (binding.editTextJam.getText().toString().isEmpty()) {
            binding.editTextJam.setError("Mohon isi data berikut");
            cek2 = false;
        }

        return cek1 && cek2;
    }
}