package com.unitedtractors.android.unitedtractorsapp.view.activity.approval;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.adapter.ApprovalAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.PembelianSnackAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.PembelianSnackResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityDetailApprovalPembelianSnackBinding;
import com.unitedtractors.android.unitedtractorsapp.model.PembelianSnackModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.DetailApprovalPembelianSnackViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DetailApprovalPembelianSnackActivity extends AppCompatActivity {
    private ActivityDetailApprovalPembelianSnackBinding binding;
    private DetailApprovalPembelianSnackViewModel viewModel;

    private String idTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailApprovalPembelianSnackBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        idTrans = getIntent().getStringExtra("ID_TRANS");

        viewModel = ViewModelProviders.of(this).get(DetailApprovalPembelianSnackViewModel.class);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getPembelianSnack(
                idTrans
        ).observe(this, new Observer<PembelianSnackResponse>() {
            @Override
            public void onChanged(PembelianSnackResponse pembelianSnackResponse) {
                if (pembelianSnackResponse.isStatus()) {
                    if (pembelianSnackResponse.getData().getStatTrans() == 0) {
                        binding.textViewStatus.setText("Menunggu Konfirmasi");
                        binding.textViewStatus.setTextColor(getResources().getColor(R.color.primary));
                        binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgPrimary));
                    } else if (pembelianSnackResponse.getData().getStatTrans() == 1) {
                        binding.textViewStatus.setText("Sedang Diproses");
                        binding.textViewStatus.setTextColor(getResources().getColor(R.color.primary));
                        binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgPrimary));
                    } else if (pembelianSnackResponse.getData().getStatTrans() == 2) {
                        binding.textViewStatus.setText("Approved");
                        binding.textViewStatus.setTextColor(getResources().getColor(R.color.approve));
                        binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgApprove));
                    } else if (pembelianSnackResponse.getData().getStatTrans() == 3) {
                        binding.textViewStatus.setText("Rejected");
                        binding.textViewStatus.setTextColor(getResources().getColor(R.color.reject));
                        binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgReject));
                    }

                    String nmyFormat = "dd MMMM yyyy"; //In which you need put here
                    SimpleDateFormat nsdf = new SimpleDateFormat(nmyFormat, new Locale("id", "ID"));
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    try {
                        Date date = format.parse(pembelianSnackResponse.getData().getTglSnack());
                        binding.textViewTanggal.setText(nsdf.format(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    binding.textViewDivisi.setText(pembelianSnackResponse.getData().getDivisiSnack());
                    binding.textViewKeperluan.setText(pembelianSnackResponse.getData().getKeperluanSnack());

                    binding.recyclerView.setAdapter(new PembelianSnackAdapter(setDetailSncak(pembelianSnackResponse.getData().getDteailSnack()), false));
                }
            }
        });

        if (AppPreference.getUser(this).getRoleUsers().equalsIgnoreCase("staff")) {
            binding.linearLayoutButton.setVisibility(View.GONE);
        } else {
            binding.linearLayoutButton.setVisibility(View.VISIBLE);

            binding.materialButtonApprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModel.putConfirm(
                            AppPreference.getUser(v.getContext()).getUserUsers(),
                            idTrans,
                            true
                    ).observe(DetailApprovalPembelianSnackActivity.this, new Observer<BaseResponse>() {
                        @Override
                        public void onChanged(BaseResponse baseResponse) {
                            binding.linearLayoutButton.setVisibility(View.GONE);

                            binding.linearLayoutStatus.setVisibility(View.VISIBLE);
                            binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgApprove));
                            binding.textViewStatus.setTextColor(getResources().getColor(R.color.approve));
                            binding.textViewStatus.setText("Approved");
                        }
                    });
                }
            });

            binding.materialButtonReject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModel.putConfirm(
                            AppPreference.getUser(v.getContext()).getUserUsers(),
                            "",
                            false
                    ).observe(DetailApprovalPembelianSnackActivity.this, new Observer<BaseResponse>() {
                        @Override
                        public void onChanged(BaseResponse baseResponse) {
                            binding.linearLayoutButton.setVisibility(View.GONE);

                            binding.linearLayoutStatus.setVisibility(View.VISIBLE);
                            binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgReject));
                            binding.textViewStatus.setTextColor(getResources().getColor(R.color.reject));
                            binding.textViewStatus.setText("Rejected");
                        }
                    });
                }
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public List<PembelianSnackModel.DetailPembelianSnackModel> setDetailSncak(List<PembelianSnackResponse.PembelianSnack.DetailSnack> list) {
        List<PembelianSnackModel.DetailPembelianSnackModel> data = new ArrayList<>();
        for (PembelianSnackResponse.PembelianSnack.DetailSnack snack : list) {
            data.add(new PembelianSnackModel.DetailPembelianSnackModel(snack.getJenisSnack(), snack.getJmlSnack()));
        }
        return data;
    }
}