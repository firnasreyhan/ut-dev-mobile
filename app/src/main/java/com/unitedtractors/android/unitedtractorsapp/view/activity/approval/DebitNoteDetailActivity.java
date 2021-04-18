package com.unitedtractors.android.unitedtractorsapp.view.activity.approval;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
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

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.DetailDebitNoteResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityDebitNoteDetailBinding;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.DebitNoteDetailViewModel;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.TransactionDetailViewModel;

public class DebitNoteDetailActivity extends AppCompatActivity {
    private ActivityDebitNoteDetailBinding binding;
    private DebitNoteDetailViewModel viewModel;
    private String idDebitote, idUsers, idMapping;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDebitNoteDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        idDebitote = getIntent().getStringExtra("ID_DEBITNOTE");
        idUsers = getIntent().getStringExtra("ID_USERS");

        viewModel = ViewModelProviders.of(this).get(DebitNoteDetailViewModel.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu Sebentar...");
        progressDialog.setCancelable(false);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel.getDebitNoteDetail(
                AppPreference.getUser(this).getUserUsers(),
                idDebitote
        ).observe(this, new Observer<DetailDebitNoteResponse>() {
            @Override
            public void onChanged(DetailDebitNoteResponse detailDebitNoteResponse) {
                if (detailDebitNoteResponse != null) {
                    if (detailDebitNoteResponse.isStatus()) {
                        if (detailDebitNoteResponse.getData().getIsApproveApp() == null) {
                            binding.textViewStatus.setText("Menunggu Konfirmasi");
                            binding.textViewStatus.setTextColor(getResources().getColor(R.color.primary));
                            binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgPrimary));
                            binding.linearLayoutButton.setVisibility(View.VISIBLE);
                        } else if (detailDebitNoteResponse.getData().getIsApproveApp().equalsIgnoreCase("1")) {
                            binding.textViewStatus.setText("Disetujui");
                            binding.textViewStatus.setTextColor(getResources().getColor(R.color.approve));
                            binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgApprove));
                            binding.linearLayoutButton.setVisibility(View.INVISIBLE);
                        } else if (detailDebitNoteResponse.getData().getIsApproveApp().equalsIgnoreCase("0")) {
                            binding.textViewStatus.setText("Ditolak");
                            binding.textViewStatus.setTextColor(getResources().getColor(R.color.reject));
                            binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgReject));
                            binding.linearLayoutButton.setVisibility(View.INVISIBLE);
                        }

                        pdfView(detailDebitNoteResponse.getData().getPathDebitNote());
                    }
                }
            }
        });

        binding.materialButtonApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                approval(1);
            }
        });

        binding.materialButtonReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                approval(2);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void pdfView(String responseURL) {
        binding.webView.invalidate();
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.getSettings().setBuiltInZoomControls(true);
        binding.webView.getSettings().setAllowFileAccessFromFileURLs(true);
        binding.webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        binding.webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        binding.webView.getSettings().setDomStorageEnabled(true);
        binding.webView.getSettings().setLoadWithOverviewMode(true);
//        binding.webView.getSettings().setUseWideViewPort(true);
        String newUrl = "https://drive.google.com/viewerng/viewer?embedded=true&url=" + responseURL;
        binding.webView.loadUrl(newUrl);
        binding.webView.setWebChromeClient(new WebChromeClient());

        binding.webView.setWebViewClient(new WebViewClient() {
            boolean checkhasOnPageStarted = false;

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                checkhasOnPageStarted = true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (checkhasOnPageStarted) {
//                    binding.webView.loadUrl(url);
                } else {
                    pdfView(responseURL);
                }
            }
        });
    }

    private void approval(int isApprove) {
        progressDialog.show();
        viewModel.putConfirmDebitNote(
                AppPreference.getUser(this).getUserUsers(),
                idDebitote,
                isApprove
        ).observe(DebitNoteDetailActivity.this, new Observer<BaseResponse>() {
            @Override
            public void onChanged(BaseResponse baseResponse) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                if (baseResponse != null) {
                    if (baseResponse.isStatus()) {
                        binding.linearLayoutButton.setVisibility(View.GONE);

                        if (isApprove == 2) {
                            binding.linearLayoutStatus.setVisibility(View.VISIBLE);
                            binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgReject));
                            binding.textViewStatus.setTextColor(getResources().getColor(R.color.reject));
                            binding.textViewStatus.setText("Ditolak");
                        } else {
                            binding.linearLayoutStatus.setVisibility(View.VISIBLE);
                            binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgApprove));
                            binding.textViewStatus.setTextColor(getResources().getColor(R.color.approve));
                            binding.textViewStatus.setText("Disetujui");
                        }

                        new AlertDialog.Builder(DebitNoteDetailActivity.this)
                                .setTitle("Pesan")
                                .setMessage("Terimakasih telah melakukan konfirmasi form")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        onBackPressed();
                                    }
                                })
                                .create()
                                .show();
                    } else {
                        new AlertDialog.Builder(DebitNoteDetailActivity.this)
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
                    new AlertDialog.Builder(DebitNoteDetailActivity.this)
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
    }
}