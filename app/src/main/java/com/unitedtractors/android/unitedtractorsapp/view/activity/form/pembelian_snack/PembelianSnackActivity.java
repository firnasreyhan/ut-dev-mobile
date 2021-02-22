package com.unitedtractors.android.unitedtractorsapp.view.activity.form.pembelian_snack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.adapter.PembelianSnackAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.PermintaanAssetAdapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPembelianSnackBinding;
import com.unitedtractors.android.unitedtractorsapp.model.PembelianSnackModel;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanAssetModel;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_asset.ListPermintaanAssetActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PembelianSnackActivity extends AppCompatActivity {
    private ActivityPembelianSnackBinding binding;

    private String idMapping;
    private int jumlahPembelianSnack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPembelianSnackBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        idMapping = getIntent().getStringExtra("ID_MAPPING");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        jumlahPembelianSnack = Integer.parseInt(binding.editTextJumlahPermintaanPembelianSnack.getText().toString());

        binding.materialButtonTambahJumlahPembelianSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahPembelianSnack++;
                binding.editTextJumlahPermintaanPembelianSnack.setText(String.valueOf(jumlahPembelianSnack));
                if (jumlahPembelianSnack > 0) {
                    binding.materialButtonSelanjutnya.setEnabled(true);
                    binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.primary));
                }
            }
        });

        binding.materialButtonKurangJumlahPembelianSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumlahPembelianSnack > 0) {
                    jumlahPembelianSnack--;
                    binding.editTextJumlahPermintaanPembelianSnack.setText(String.valueOf(jumlahPembelianSnack));
                    if (jumlahPembelianSnack == 0) {
                        binding.materialButtonSelanjutnya.setEnabled(false);
                        binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.button_disable));
                    }
                }
            }
        });

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListPembelianSnackActivity.class);
                intent.putExtra("JUMLAH_PEMBELIAN_SNACK", jumlahPembelianSnack);
                intent.putExtra("ID_MAPPING", idMapping);
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