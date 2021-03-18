package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_alat_komunikasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityChecklistAlatKomunikasiBinding;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPabxBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_ruang_meeting.ListCheckRuangMeetingActivity;

public class PabxActivity extends AppCompatActivity {
    private ActivityPabxBinding binding;

    String idMapping;
    String tgl;
    String lokasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        idMapping = getIntent().getStringExtra("ID_MAPPING");
        tgl = getIntent().getStringExtra("TGL");
        lokasi = getIntent().getStringExtra("LOKASI");

        binding = ActivityPabxBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RepeaterRadioActivity.class);
                intent.putExtra("ID_MAPPING", idMapping);
                intent.putExtra("TGL", tgl);
                intent.putExtra("LOKASI", lokasi);
                startActivity(intent);
            }
        });
    }
}