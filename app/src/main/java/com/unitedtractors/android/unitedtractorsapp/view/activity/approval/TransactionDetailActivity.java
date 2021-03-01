package com.unitedtractors.android.unitedtractorsapp.view.activity.approval;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTransactionDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        idTrans = getIntent().getStringExtra("ID_TRANS");

        viewModel = ViewModelProviders.of(this).get(TransactionDetailViewModel.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu Sebentar...");
        progressDialog.setCancelable(false);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu Sebentar...");
        progressDialog.setCancelable(false);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        binding.webView.getSettings().setJavaScriptEnabled(true);
//        binding.webView.getSettings().setBuiltInZoomControls(true);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // chromium, enable hardware acceleration
//            binding.webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//        } else {
//            // older android version, disable hardware acceleration
//            binding.webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        }
//        binding.webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//        binding.webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
//        binding.webView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                if (newProgress < 100) {
//                    progressDialog.show();
//                }
//                if (newProgress == 100) {
//                    progressDialog.dismiss();
//                }
//            }
//        });

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
                            binding.linearLayoutButton.setVisibility(View.VISIBLE);
                        } else if (transactionDetailResponse.getData().getStatTrans().equalsIgnoreCase("1")) {
                            binding.textViewStatus.setText("Approved");
                            binding.textViewStatus.setTextColor(getResources().getColor(R.color.approve));
                            binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgApprove));
                            binding.linearLayoutButton.setVisibility(View.INVISIBLE);
                        } else if (transactionDetailResponse.getData().getStatTrans().equalsIgnoreCase("0")) {
                            binding.textViewStatus.setText("Rejected");
                            binding.textViewStatus.setTextColor(getResources().getColor(R.color.reject));
                            binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgReject));
                            binding.linearLayoutButton.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        binding.linearLayoutButton.setVisibility(View.INVISIBLE);
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

                    if (transactionDetailResponse.getData().getKeteranganTrans() != null) {
                        if (!transactionDetailResponse.getData().getKeteranganTrans().isEmpty()) {
                            binding.linearLayoutKeterangan.setVisibility(View.VISIBLE);
                            binding.textViewKeterangan.setText(transactionDetailResponse.getData().getKeteranganTrans());
                        }
                    }

                    pdfView(transactionDetailResponse.getData().getPathTrans());

//                    binding.webView.invalidate();
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
//                    binding.webView.setWebChromeClient(new WebChromeClient() {
//                        @Override
//                        public void onProgressChanged(WebView view, int newProgress) {
//                            if (newProgress < 100) {
//                                progressDialog.show();
//                            }
//                            if (newProgress == 100) {
//                                progressDialog.dismiss();
//                            }
//                        }
//                    });
//                    String url = "https://drive.google.com/viewerng/viewer?embedded=true&url=" + transactionDetailResponse.getData().getPathTrans();
//                    String doc = "<iframe src='"+url+"' width='100%' height='100%' style='border: none;'></iframe>";
////                    binding.webView.loadData(doc, "text/html", "UTF-8");
//                    binding.webView.loadUrl(url);
//                    binding.webView.setWebViewClient(new WebViewClient() {
//                        boolean checkhasOnPageStarted = false;
//
//                        @Override
//                        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                            checkhasOnPageStarted = true;
//                        }
//
//                        @Override
//                        public void onPageFinished(WebView view, String url) {
//                            if (checkhasOnPageStarted) {
//                                binding.webView.loadUrl(url);
//                            } else {
//
//                            }
//                        }
//                    });
//                    Log.e("url", transactionDetailResponse.getData().getPathTrans());
//                    binding.webView.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            binding.webView.loadData(doc, "text/html", "UTF-8");
//                        }
//                    }, 3000);

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
                progressDialog.show();
                viewModel.putConfirm(
                        AppPreference.getUser(v.getContext()).getUserUsers(),
                        idTrans,
                        1,
                        "-"
                ).observe(TransactionDetailActivity.this, new Observer<BaseResponse>() {
                    @Override
                    public void onChanged(BaseResponse baseResponse) {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        if (baseResponse != null) {
                            if (baseResponse.isStatus()) {
                                binding.linearLayoutButton.setVisibility(View.GONE);

                                binding.linearLayoutStatus.setVisibility(View.VISIBLE);
                                binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgApprove));
                                binding.textViewStatus.setTextColor(getResources().getColor(R.color.approve));
                                binding.textViewStatus.setText("Approved");

                                new AlertDialog.Builder(TransactionDetailActivity.this)
                                        .setTitle("Pesan")
                                        .setMessage("Terima kasih telah melakukan konfirmasi form.")
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
                                new AlertDialog.Builder(TransactionDetailActivity.this)
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
                        }
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
                                    dialog.dismiss();
                                    progressDialog.show();
                                    viewModel.putConfirm(
                                            AppPreference.getUser(v.getContext()).getUserUsers(),
                                            idTrans,
                                            2,
                                            input.getText().toString()
                                    ).observe(TransactionDetailActivity.this, new Observer<BaseResponse>() {
                                        @Override
                                        public void onChanged(BaseResponse baseResponse) {
                                            if (progressDialog.isShowing()) {
                                                progressDialog.dismiss();
                                            }

                                            if (baseResponse != null) {
                                                if (baseResponse.isStatus()) {
                                                    binding.linearLayoutButton.setVisibility(View.GONE);

                                                    binding.linearLayoutStatus.setVisibility(View.VISIBLE);
                                                    binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgReject));
                                                    binding.textViewStatus.setTextColor(getResources().getColor(R.color.reject));
                                                    binding.textViewStatus.setText("Rejected");

                                                    new AlertDialog.Builder(TransactionDetailActivity.this)
                                                            .setTitle("Pesan")
                                                            .setMessage("Terima kasih telah melakukan konfirmasi form.")
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
                                                    new AlertDialog.Builder(TransactionDetailActivity.this)
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
                                            }
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

}