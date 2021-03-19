package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_alat_komunikasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.unitedtractors.android.unitedtractorsapp.adapter.ChecklistAlatKomunikasiAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.Pertanyaan2Adapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityChecklistAlatKomunikasiBinding;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPabxBinding;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistAlatKomunikasiModel;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_ruang_meeting.ListCheckRuangMeetingActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PabxActivity extends AppCompatActivity {
    private ActivityPabxBinding binding;

    String idMapping;
    String tgl;
    String lokasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPabxBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        idMapping = getIntent().getStringExtra("ID_MAPPING");
        tgl = getIntent().getStringExtra("TGL");
        lokasi = getIntent().getStringExtra("LOKASI");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi> list = new ArrayList<>();
        list.add(new ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi(
                "Card Ext & Direct / LC CO",
                1
        ));
        list.add(new ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi(
                "Card Register",
                1
        ));
        list.add(new ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi(
                "Card dterm",
                1
        ));
        list.add(new ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi(
                "Card Consul",
                1
        ));
        list.add(new ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi(
                "Card Tieline TLTR",
                1
        ));
        list.add(new ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi(
                "Card switch change TDSW",
                1
        ));
        list.add(new ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi(
                "Card Interface",
                1
        ));
        list.add(new ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi(
                "Control Processing Unit CPU",
                1
        ));
        list.add(new ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi(
                "Alarm Control Card",
                1
        ));
        list.add(new ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi(
                "Billing System",
                1
        ));
        list.add(new ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi(
                "Voice Mail",
                1
        ));
        list.add(new ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi(
                "Incoming",
                1
        ));
        list.add(new ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi(
                "Out Going",
                1
        ));
        list.add(new ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi(
                "Vsat / Tie line",
                1
        ));
        list.add(new ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi(
                "Password",
                1
        ));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new ChecklistAlatKomunikasiAdapter(list));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RepeaterRadioActivity.class);
                intent.putExtra("ID_MAPPING", idMapping);
                intent.putExtra("TGL", tgl);
                intent.putExtra("LOKASI", lokasi);
                intent.putExtra("PABX", (Serializable) ChecklistAlatKomunikasiAdapter.getList());
                startActivity(intent);
            }
        });
    }
}