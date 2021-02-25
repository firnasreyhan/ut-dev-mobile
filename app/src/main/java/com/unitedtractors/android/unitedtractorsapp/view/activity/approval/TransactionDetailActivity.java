package com.unitedtractors.android.unitedtractorsapp.view.activity.approval;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionDetailResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityTransactionDetailBinding;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.TransactionDetailViewModel;

public class TransactionDetailActivity extends AppCompatActivity {
    private ActivityTransactionDetailBinding binding;
    private TransactionDetailViewModel viewModel;
    private String idTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTransactionDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        idTrans = getIntent().getStringExtra("ID_TRANS");

        viewModel = ViewModelProviders.of(this).get(TransactionDetailViewModel.class);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.getSettings().setBuiltInZoomControls(true);

        viewModel.getTransactionDetail(
                AppPreference.getUser(this).getUserUsers(),
                idTrans
        ).observe(this, new Observer<TransactionDetailResponse>() {
            @Override
            public void onChanged(TransactionDetailResponse transactionDetailResponse) {
                if (transactionDetailResponse.isStatus()) {
                    if (AppPreference.getUser(TransactionDetailActivity.this).getRoleUsers().equalsIgnoreCase("staff") ? false : true) {
                        if (transactionDetailResponse.getData().getStatTrans() == null) {
                            binding.textViewStatus.setText("Menunggu Konfirmasi");
                            binding.textViewStatus.setTextColor(getResources().getColor(R.color.primary));
                            binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgPrimary));
                        } else if (transactionDetailResponse.getData().getStatTrans().equalsIgnoreCase("1")) {
                            binding.textViewStatus.setText("Approved");
                            binding.textViewStatus.setTextColor(getResources().getColor(R.color.approve));
                            binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgApprove));
                            binding.linearLayoutButton.setVisibility(View.GONE);
                        } else if (transactionDetailResponse.getData().getStatTrans().equalsIgnoreCase("0")) {
                            binding.textViewStatus.setText("Rejected");
                            binding.textViewStatus.setTextColor(getResources().getColor(R.color.reject));
                            binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgReject));
                            binding.linearLayoutButton.setVisibility(View.GONE);
                        }
                    } else {
                        binding.linearLayoutButton.setVisibility(View.GONE);
                        if (transactionDetailResponse.getData().getStatTrans().equalsIgnoreCase("0")) {
                            binding.textViewStatus.setText("Menunggu Konfirmasi");
                            binding.textViewStatus.setTextColor(getResources().getColor(R.color.primary));
                            binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgPrimary));
                        } else if (transactionDetailResponse.getData().getStatTrans().equalsIgnoreCase("1")) {
                            binding.textViewStatus.setText("Sedang Diproses");
                            binding.textViewStatus.setTextColor(getResources().getColor(R.color.primary));
                            binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgPrimary));
                        } else if (transactionDetailResponse.getData().getStatTrans().equalsIgnoreCase("2")) {
                            binding.textViewStatus.setText("Approved");
                            binding.textViewStatus.setTextColor(getResources().getColor(R.color.approve));
                            binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgApprove));
                        } else if (transactionDetailResponse.getData().getStatTrans().equalsIgnoreCase("3")) {
                            binding.textViewStatus.setText("Rejected");
                            binding.textViewStatus.setTextColor(getResources().getColor(R.color.reject));
                            binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgReject));
                        }
                    }

                    String url = "https://drive.google.com/viewerng/viewer?embedded=true&url=" + transactionDetailResponse.getData().getPathTrans();
                    String doc = "<iframe src='"+url+"' width='100%' height='100%' style='border: none;'></iframe>";
                    binding.webView.loadData(doc, "text/html", "UTF-8");

//                    binding.webView.getSettings().setJavaScriptEnabled(true);
//                    binding.webView.getSettings().setBuiltInZoomControls(true);
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                        // chromium, enable hardware acceleration
//                        binding.webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//                    } else {
//                        // older android version, disable hardware acceleration
//                        binding.webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//                    }
//                    binding.webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//                    binding.webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
//                    binding.webView.setWebViewClient(new WebViewClient() {
//                        @Override
//                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                            view.loadUrl(url);
//                            return false;
//                        }
//                    });
//
//                    binding.webView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=http://www.pdf995.com/samples/pdf.pdf");
                }
            }
        });

        binding.materialButtonApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.putConfirm(
                        AppPreference.getUser(v.getContext()).getUserUsers(),
                        idTrans,
                        1,
                        "-"
                ).observe(TransactionDetailActivity.this, new Observer<BaseResponse>() {
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
                final EditText input = new EditText(v.getContext());
                input.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                input.setHint("Keterangan");

                LinearLayout linearLayout = new LinearLayout(v.getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(layoutParams);
                linearLayout.addView(input);
                linearLayout.setPadding(60, 0, 60, 0);

                new AlertDialog.Builder(v.getContext())
                        .setTitle("Pesan")
                        .setMessage("Masukkan keterangan/alasan.")
                        .setView(linearLayout)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (input.getText().toString().isEmpty()) {
                                    input.setError("Mohon isi data berikut.");
                                } else {
                                    viewModel.putConfirm(
                                            AppPreference.getUser(v.getContext()).getUserUsers(),
                                            "",
                                            2,
                                            input.getText().toString()
                                    ).observe(TransactionDetailActivity.this, new Observer<BaseResponse>() {
                                        @Override
                                        public void onChanged(BaseResponse baseResponse) {
                                            binding.linearLayoutButton.setVisibility(View.GONE);

                                            binding.linearLayoutStatus.setVisibility(View.VISIBLE);
                                            binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgReject));
                                            binding.textViewStatus.setTextColor(getResources().getColor(R.color.reject));
                                            binding.textViewStatus.setText("Rejected");
                                            dialog.dismiss();
                                        }
                                    });
                                }
                            }
                        })
                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}