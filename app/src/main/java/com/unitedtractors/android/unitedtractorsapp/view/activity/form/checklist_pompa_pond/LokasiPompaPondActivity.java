package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_pompa_pond;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityLokasiPompaPondBinding;

public class LokasiPompaPondActivity extends AppCompatActivity {
    private ActivityLokasiPompaPondBinding binding;

    private int pondA = 1;
    private int pondB = 1;
    private int pondC = 1;
    private int pondD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLokasiPompaPondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String tanggal = getIntent().getStringExtra("TANGGAL");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.radioGroupPondA.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonOKA:
                        pondA = 1;
                        break;
                    case R.id.radioButtonPerbaikanA:
                        pondA = 2;
                        break;
                    case R.id.radioButtonRusakA:
                        pondA = 3;
                        break;
                }
            }
        });

        binding.radioGroupPondB.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonOKB:
                        pondB = 1;
                        break;
                    case R.id.radioButtonPerbaikanB:
                        pondB = 2;
                        break;
                    case R.id.radioButtonRusakB:
                        pondB = 3;
                        break;
                }
            }
        });

        binding.radioGroupPondC.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonOKC:
                        pondC = 1;
                        break;
                    case R.id.radioButtonPerbaikanC:
                        pondC = 2;
                        break;
                    case R.id.radioButtonRusakC:
                        pondC = 3;
                        break;
                }
            }
        });

        binding.radioGroupPondD.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonOKD:
                        pondD = 1;
                        break;
                    case R.id.radioButtonPerbaikanD:
                        pondD = 2;
                        break;
                    case R.id.radioButtonRusakD:
                        pondD = 3;
                        break;
                }
            }
        });

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CheckingRunningTestActivity.class);
                intent.putExtra("ID_MAPPING", idMapping);
                intent.putExtra("TANGGAL", tanggal);
                intent.putExtra("POND_A", pondA);
                intent.putExtra("POND_B", pondB);
                intent.putExtra("POND_C", pondC);
                intent.putExtra("POND_D", pondD);
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