package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_for_genset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.Pertanyaan2Adapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityControlSystemBinding;
import com.unitedtractors.android.unitedtractorsapp.model.Pertanyaan2Model;

import java.util.ArrayList;
import java.util.List;

public class ControlSystemActivity extends AppCompatActivity {
    private ActivityControlSystemBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityControlSystemBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<Pertanyaan2Model> list = new ArrayList<>();
        list.add(new Pertanyaan2Model(
                "Ampere Meter AC",
                0,
                ""
        ));
        list.add(new Pertanyaan2Model(
                "Freq. Meter (RPM)",
                0,
                ""
        ));
        list.add(new Pertanyaan2Model(
                "Volt Meter AC",
                0,
                ""
        ));
        list.add(new Pertanyaan2Model(
                "Relay",
                0,
                ""
        ));
        list.add(new Pertanyaan2Model(
                "MCB",
                0,
                ""
        ));
        list.add(new Pertanyaan2Model(
                "Terminal",
                0,
                ""
        ));
        list.add(new Pertanyaan2Model(
                "Terminal",
                0,
                ""
        ));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new Pertanyaan2Adapter(list));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), K5Activity.class);
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