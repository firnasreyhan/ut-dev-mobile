package com.unitedtractors.android.unitedtractorsapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.api.response.SignInResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySignInBinding;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.SignInViewModel;

public class SignInActivity extends AppCompatActivity {
    private ActivitySignInBinding binding;
    private SignInViewModel viewModel;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Mohon Tunggu Sebentar...");

        viewModel = ViewModelProviders.of(this).get(SignInViewModel.class);

        binding.materialButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cekUsername = true;
                boolean cekPassword = true;

                if (binding.textInputEditTextUsername.getText().toString().isEmpty()) {
                    binding.textInputEditTextUsername.setError("Mohon isi data berikut");
                    cekUsername = false;
                }

                if (binding.textInputEditTextPassword.getText().toString().isEmpty()) {
                    binding.textInputEditTextPassword.setError("Mohon isi data berikut");
                    cekPassword = false;
                }

                if (cekUsername && cekPassword) {
                    if (isConnectingToInternet()) {
                        signIn();
                    }
                }
            }
        });

        binding.textViewSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });
    }

    private void signIn() {
        progressDialog.show();
        viewModel.signIn(
                binding.textInputEditTextUsername.getText().toString(),
                binding.textInputEditTextPassword.getText().toString()
        ).observe(this, new Observer<SignInResponse>() {
            @Override
            public void onChanged(SignInResponse signInResponse) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                if (signInResponse != null) {
                    if (signInResponse.isStatus()) {
                        if (signInResponse.getData().getStatUsers() == 0) {
                            new AlertDialog.Builder(SignInActivity.this)
                                    .setTitle("Pesan")
                                    .setMessage("Akun anda sedang dalam proses validasi oleh Admin, segera hubungi Admin")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .create()
                                    .show();
                        } else {
                            AppPreference.saveUser(getApplicationContext(), signInResponse.getData());
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    } else {
                        new AlertDialog.Builder(SignInActivity.this)
                                .setTitle("Pesan")
                                .setMessage(signInResponse.getMessage())
                                .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .create()
                                .show();
                    }
                } else {
                    new AlertDialog.Builder(SignInActivity.this)
                            .setTitle("Pesan")
                            .setMessage("Terjadi kesalahan pada server, silahkan coba beberapa saat lagi")
                            .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
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

    private boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Tidak Ada Koneksi Internet")
                    .setMessage("Mohon periksa koneksi internet anda dan coba lagi")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();
            return false;
        }
    }
}