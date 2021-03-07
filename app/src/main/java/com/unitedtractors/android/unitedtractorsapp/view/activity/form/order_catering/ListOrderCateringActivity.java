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
    ActivityListOrderCateringBinding binding;
    List<OrderCateringModel> list;

    String idMapping;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListOrderCateringBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        idMapping = getIntent().getStringExtra("ID_MAPPING");
        int jumlahOrder = getIntent().getIntExtra("JUMLAH_ORDER", 0);

        calendar = Calendar.getInstance();

        binding.materialButtonSelanjutnya.setEnabled(true);
        binding.materialButtonSelanjutnya.setBackgroundColor(getResources().getColor(R.color.primary));

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        list = new ArrayList<>();

        if (jumlahOrder > 0) {
            for (int i = 0; i < jumlahOrder; i++) {
                list.add(new OrderCateringModel(
                        idMapping,
                        AppPreference.getUser(this).getIdUsers(),
                        "",
                        "",
                        ""
                        ));
            }
        }

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new OrderCateringAdapter(list, true));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), KonfrimasiOrderCateringActivity.class);
                    intent.putExtra("ID_MAPPING", idMapping);
                    intent.putExtra("JUMLAH_ORDER", jumlahOrder);
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
        for (OrderCateringModel model : OrderCateringAdapter.getList()) {
            if (model.checkData() == false) {
                return false;
            }
        }
        return true;
    }
}