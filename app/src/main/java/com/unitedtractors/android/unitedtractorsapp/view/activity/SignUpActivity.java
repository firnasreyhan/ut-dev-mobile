package com.unitedtractors.android.unitedtractorsapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.api.response.SignInResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.SignUpResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySignUpBinding;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.SignInViewModel;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.SignUpViewModel;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private SignUpViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);

        String[] roles = new String[] {"Head", "Section head", "Staff"};
        ArrayAdapter<String> roleAdapter = new ArrayAdapter<>(this, R.layout.item_dropdown_text, roles);
        binding.dropdownRole.setAdapter(roleAdapter);

        String[] departments = new String[] {"General", "Affairs", "Staff"};
        ArrayAdapter<String> departmentAdapter = new ArrayAdapter<>(this, R.layout.item_dropdown_text, departments);
        binding.dropdownDepartment.setAdapter(departmentAdapter);

        String[] divisons = new String[] {"Project Management", "General Service & Maintenance Management", "Budget, Asset & Building Management"};
        ArrayAdapter<String> divisionAdapter = new ArrayAdapter<>(this, R.layout.item_dropdown_text, divisons);
        binding.dropdownDivision.setAdapter(divisionAdapter);

        binding.materialButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cekUsername = true;
                boolean cekPassword = true;
                boolean cekConfirmPassword = true;
                boolean cekNamaLengkap = true;
                boolean cekRole = true;
                boolean cekDivisi = true;
                boolean cekDepartemen = true;

                if (binding.textInputEditTextUsername.getText().toString().isEmpty()) {
                    binding.textInputEditTextUsername.setError("Mohon isi data berikut");
                    cekUsername = false;
                }

                if (binding.textInputEditTextPassword.getText().toString().isEmpty()) {
                    binding.textInputEditTextPassword.setError("Mohon isi data berikut");
                    cekPassword = false;
                }

                if (binding.textInputEditTextConfirmPassword.getText().toString().isEmpty()) {
                    binding.textInputEditTextConfirmPassword.setError("Mohon isi data berikut");
                    cekConfirmPassword = false;
                }

                if (binding.textInputEditTexNamaLengkap.getText().toString().isEmpty()) {
                    binding.textInputEditTexNamaLengkap.setError("Mohon isi data berikut");
                    cekNamaLengkap = false;
                }

                if (binding.dropdownDepartment.getText().toString().isEmpty()) {
                    binding.dropdownDepartment.setError("Mohon pilih salah satu");
                    cekDepartemen = false;
                }

                if (binding.dropdownDivision.getText().toString().isEmpty()) {
                    binding.dropdownDivision.setError("Mohon pilih salah satu");
                    cekDivisi = false;
                }

                if (binding.dropdownRole.getText().toString().isEmpty()) {
                    binding.dropdownRole.setError("Mohon pilih salah satu");
                    cekRole = false;
                }

                String password = binding.textInputEditTextPassword.getText().toString().trim();
                String confirmPassword = binding.textInputEditTextConfirmPassword.getText().toString().trim();

                if (cekUsername && cekDepartemen && cekDivisi && cekNamaLengkap && cekRole && cekPassword && cekConfirmPassword) {
                    if (!password.equals(confirmPassword)) {
                        binding.textInputEditTextConfirmPassword.setError("Password tidak sama");
                        return;
                    }

                    signUp();
                }
            }
        });

    }

    private void signUp() {
        viewModel.signUp(
                binding.textInputEditTexNamaLengkap.getText().toString(),
                binding.textInputEditTextUsername.getText().toString(),
                binding.dropdownRole.getText().toString(),
                binding.dropdownDepartment.getText().toString(),
                binding.dropdownDivision.getText().toString(),
                binding.textInputEditTextPassword.getText().toString()
        ).observe(this, new Observer<SignUpResponse>() {
            @Override
            public void onChanged(SignUpResponse signUpResponse) {
                if (signUpResponse.isStatus()) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    new AlertDialog.Builder(SignUpActivity.this)
                            .setTitle("Pesan")
                            .setMessage(signUpResponse.getMessage())
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