package com.unitedtractors.android.unitedtractorsapp.view.activity.form.syarat_legalitas_catering;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySyaratLegalitasCateringBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;

public class SyaratLegalitasCateringActivity extends AppCompatActivity {
    private ActivitySyaratLegalitasCateringBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySyaratLegalitasCateringBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SyaratLegatitasCateringLanjutanActivity.class);
                    startActivity(intent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

//    public boolean checkData()  {
//        if (!binding.editTextKeterangan1.getText().toString().isEmpty()
//                && !binding.editTextKeterangan2.getText().toString().isEmpty()
//                && !binding.editTextKeterangan3.getText().toString().isEmpty()
//        && !binding.editTextKeterangan4.getText().toString().isEmpty()
//        && !binding.editTextKeterangan5.getText().toString().isEmpty()
//        && !binding.editTextKeterangan6.getText().toString().isEmpty()) {
//            return true;
//        } else {
//            return false;
//        }
//    }

}