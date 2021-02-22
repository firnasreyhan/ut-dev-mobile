package com.unitedtractors.android.unitedtractorsapp.view.activity.form.kontrol_harian;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKomplainAtauUsulanBinding;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKontrolHarianBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;

public class KontrolHarianActivity extends AppCompatActivity {
    private ActivityKontrolHarianBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKontrolHarianBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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
                Intent intent = new Intent(v.getContext(), ScreenFeedbackActivity.class);
                    startActivity(intent);
            }
//                if (checkData()) {
//                    Intent intent = new Intent(v.getContext(), ScreenFeedbackActivity.class);
//                    startActivity(intent);
//                } else {
//                    new AlertDialog.Builder(v.getContext())
//                            .setTitle("Pesan")
//                            .setMessage("Terdapat data yang kosong, mohon untuk diisi.")
//                            .setCancelable(false)
//                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            })
//                            .create()
//                            .show();
//                }
//            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public boolean checkData()  {
        if (!binding.editTextKeterangan1.getText().toString().isEmpty()
                && !binding.editTextKeterangan2.getText().toString().isEmpty()
                && !binding.editTextKeterangan3.getText().toString().isEmpty()
        && !binding.editTextKeterangan4.getText().toString().isEmpty()
        && !binding.editTextKeterangan5.getText().toString().isEmpty()
        && !binding.editTextKeterangan6.getText().toString().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

}