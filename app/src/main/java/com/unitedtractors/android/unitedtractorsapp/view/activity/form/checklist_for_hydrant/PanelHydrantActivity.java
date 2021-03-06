package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_for_hydrant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.Pertanyaan3Adapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPanelHydrantBinding;
import com.unitedtractors.android.unitedtractorsapp.model.Pertanyaan3Model;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;

import java.util.ArrayList;
import java.util.List;

public class PanelHydrantActivity extends AppCompatActivity {
    private ActivityPanelHydrantBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPanelHydrantBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<Pertanyaan3Model> list = new ArrayList<>();
        list.add(new Pertanyaan3Model(
                "Selector Switch",
                true,
                "*Standard: Harus selalu pada posisi ‘AUTO’",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Lampu Indikator",
                true,
                "*Standard: Sesuai dengan operation",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Volt Meter",
                true,
                "*Standard: Putar selector switch u/ Volt meter",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Ampere Meter",
                true,
                "*Standard: Seimbang dengan daya motor pompa masing-masing",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Konektor Panel",
                true,
                "*Standard: Pengencangan terminasi MCB, MCCB, kontaktor, relay, terminal bar (setiap 2 bulan sekali)",
                ""
        ));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new Pertanyaan3Adapter(list));

        binding.materialButtonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ScreenFeedbackActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}