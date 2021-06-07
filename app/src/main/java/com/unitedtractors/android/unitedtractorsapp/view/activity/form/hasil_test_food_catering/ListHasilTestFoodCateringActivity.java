package com.unitedtractors.android.unitedtractorsapp.view.activity.form.hasil_test_food_catering;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.IdTransResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListHasilTestFoodCateringBinding;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;
import com.unitedtractors.android.unitedtractorsapp.adapter.HasilTestFoodCateringAdapter;
import com.unitedtractors.android.unitedtractorsapp.model.HasilTestFoodCateringModel;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.HasilTestFoodCateringViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListHasilTestFoodCateringActivity extends AppCompatActivity {
    private ActivityListHasilTestFoodCateringBinding binding;
    private HasilTestFoodCateringViewModel viewModel;
    private ProgressDialog progressDialog;

    private Uri buktiCatering;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListHasilTestFoodCateringBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        int jumlahCatering = getIntent().getIntExtra("JUMLAH_CATERING", 0);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(HasilTestFoodCateringViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu Sebentar...");
        progressDialog.setCancelable(false);

        List<HasilTestFoodCateringModel.DetailTestFood> list = new ArrayList<>();
        for (int i = 0; i < jumlahCatering; i++) {
            list.add(new HasilTestFoodCateringModel.DetailTestFood(
                    "",
                    1,
                    1,
                    1,
                    1
            ));
        }

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new HasilTestFoodCateringAdapter(list));

        binding.materialCardViewBuktiCatering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(ListHasilTestFoodCateringActivity.this)
                        .crop()
                        .cameraOnly()
                        .start();
            }
        });

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
                if (checkData()) {
                    progressDialog.show();

                    HasilTestFoodCateringModel model = new HasilTestFoodCateringModel(
                            AppPreference.getUser(v.getContext()).getIdUsers(),
                            idMapping,
                            HasilTestFoodCateringAdapter.getList()
                    );

                    viewModel.postTestFood(
                            model
                    ).observe(ListHasilTestFoodCateringActivity.this, new Observer<IdTransResponse>() {
                        @Override
                        public void onChanged(IdTransResponse idTransResponse) {
                            if (idTransResponse != null) {
                                if (idTransResponse.isStatus()) {
                                    if (buktiCatering != null) {
                                        viewModel.postGambarTestFood(
                                                AppPreference.getUser(ListHasilTestFoodCateringActivity.this).getIdUsers(),
                                                idTransResponse.getIdTrans(),
                                                buktiCatering
                                        ).observe(ListHasilTestFoodCateringActivity.this, new Observer<BaseResponse>() {
                                            @Override
                                            public void onChanged(BaseResponse baseResponse) {
                                                if (baseResponse != null) {
                                                    if (baseResponse.isStatus()) {
                                                        if (progressDialog.isShowing()) {
                                                            progressDialog.dismiss();
                                                        }
                                                        startActivity(new Intent(v.getContext(), ScreenFeedbackActivity.class));
                                                    } else {
                                                        if (progressDialog.isShowing()) {
                                                            progressDialog.dismiss();
                                                        }
                                                        new AlertDialog.Builder(v.getContext())
                                                                .setTitle("Pesan")
                                                                .setMessage(baseResponse.getMessage())
                                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                        dialog.dismiss();
                                                                    }
                                                                })
                                                                .create()
                                                                .show();
                                                    }
                                                } else {
                                                    if (progressDialog.isShowing()) {
                                                        progressDialog.dismiss();
                                                    }
                                                    new AlertDialog.Builder(v.getContext())
                                                            .setTitle("Pesan")
                                                            .setMessage("Terjadi kesalahan pada server, silahkan coba beberapa saat lagi")
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
                                    } else {
                                        if (progressDialog.isShowing()) {
                                            progressDialog.dismiss();
                                        }
                                        startActivity(new Intent(v.getContext(), ScreenFeedbackActivity.class));
                                    }
                                } else {
                                    if (progressDialog.isShowing()) {
                                        progressDialog.dismiss();
                                    }
                                    new AlertDialog.Builder(v.getContext())
                                            .setTitle("Pesan")
                                            .setMessage(idTransResponse.getMessage())
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            })
                                            .create()
                                            .show();
                                }
                            } else {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                new AlertDialog.Builder(v.getContext())
                                        .setTitle("Pesan")
                                        .setMessage("Terjadi kesalahan pada server, silahkan coba beberapa saat lagi")
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
                } else {
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("Pesan")
                            .setMessage("Terdapat data yang kosong, mohon untuk diisi.")
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            Uri fileUri = data.getData();
            buktiCatering = fileUri;
            binding.imageViewBuktiCatering.setImageURI(fileUri);
        }
    }

    private boolean checkData() {
        for (HasilTestFoodCateringModel.DetailTestFood model : HasilTestFoodCateringAdapter.getList()) {
            if (model.checkData() == false) {
                return false;
            }
        }
        return true;
    }
}