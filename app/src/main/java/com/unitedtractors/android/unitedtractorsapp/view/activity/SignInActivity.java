package com.unitedtractors.android.unitedtractorsapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.api.response.SignInResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySignInBinding;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.SignInViewModel;

public class SignInActivity extends AppCompatActivity {
    private ActivitySignInBinding binding;
    private SignInViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

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
                    signIn();
                }
            }
        });
    }

    private void signIn() {
        viewModel.signIn(
                binding.textInputEditTextUsername.getText().toString(),
                binding.textInputEditTextPassword.getText().toString()
        ).observe(this, new Observer<SignInResponse>() {
            @Override
            public void onChanged(SignInResponse signInResponse) {
                if (signInResponse.isStatus()) {
                    AppPreference.saveUser(getApplicationContext(), signInResponse.getData());
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    new AlertDialog.Builder(SignInActivity.this)
                            .setTitle("Pesan")
                            .setMessage(signInResponse.getMessage())
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