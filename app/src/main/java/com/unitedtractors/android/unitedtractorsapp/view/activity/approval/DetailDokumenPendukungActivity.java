package com.unitedtractors.android.unitedtractorsapp.view.activity.approval;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityDetailDokumenPendukungBinding;

public class DetailDokumenPendukungActivity extends AppCompatActivity {
    private ActivityDetailDokumenPendukungBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailDokumenPendukungBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String link = getIntent().getStringExtra("LINK");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        pdfView(link);
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