package com.unitedtractors.android.unitedtractorsapp.view.activity.form.order_catering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.adapter.OrderCateringAdapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListOrderCateringBinding;
import com.unitedtractors.android.unitedtractorsapp.model.OrderCateringModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ListOrderCateringActivity extends AppCompatActivity {
    private ActivityListOrderCateringBinding binding;
    private List<OrderCateringModel.DetailOrder> list;

    private String idMapping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListOrderCateringBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        idMapping = getIntent().getStringExtra("ID_MAPPING");
        int jumlahOrder = getIntent().getIntExtra("JUMLAH_ORDER", 0);

        binding.materialButtonSelanjutnya.setEnabled(true);
        binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.primary));

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        list = new ArrayList<>();
        for (int i = 0; i < jumlahOrder; i++) {
            list.add(new OrderCateringModel.DetailOrder(
                            "",
                            AppPreference.getUser(this).getDivUsers(),
                            0
                    )
            );
        }

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new OrderCateringAdapter(list));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), KonfrimasiOrderCateringActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
                    startActivity(intent);
                } else {
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("Pesan")
                            .setMessage("Terdapat data yang kosong, mohon untuk diisi")
                            .setCancelable(false)
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
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private boolean checkData() {
        for (OrderCateringModel.DetailOrder model : OrderCateringAdapter.getList()) {
            if (model.checkData() == false) {
                return false;
            }
        }
        return true;
    }
}