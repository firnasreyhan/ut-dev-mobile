package com.unitedtractors.android.unitedtractorsapp.view.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.rm.freedrawview.FreeDrawView;
import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySignUpBinding;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.SignUpViewModel;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private SignUpViewModel viewModel;
    private ProgressDialog progressDialog;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu Sebentar...");
        progressDialog.setCancelable(false);

        String[] roles = new String[] {"Staff", "PIC Admin", "PIC Gudang", "PIC Kendaraan", "PIC Maintenance", "Section Head", "Department Head", "Division Head"};
        ArrayAdapter<String> roleAdapter = new ArrayAdapter<>(this, R.layout.item_dropdown_text, roles);
        binding.dropdownRole.setAdapter(roleAdapter);

        String[] departments = new String[] {"General Affairs", "Others"};
        ArrayAdapter<String> departmentAdapter = new ArrayAdapter<>(this, R.layout.item_dropdown_text, departments);
        binding.dropdownDepartment.setAdapter(departmentAdapter);

        String[] divisons = new String[] {"Project Management", "General Service & Maintenance Management", "Budget, Asset & Building Management", "Others"};
        ArrayAdapter<String> divisionAdapter = new ArrayAdapter<>(this, R.layout.item_dropdown_text, divisons);
        binding.dropdownDivision.setAdapter(divisionAdapter);

        binding.materialButtonHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.freeDrawView.clearDraw();
            }
        });

        binding.materialButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.freeDrawView.getDrawScreenshot(new FreeDrawView.DrawCreatorListener() {
                    @Override
                    public void onDrawCreated(Bitmap draw) {
                        Bitmap emptyBitmap = Bitmap.createBitmap(draw.getWidth(), draw.getHeight(), draw.getConfig());
                        if (!draw.sameAs(emptyBitmap)) {
                            bitmap = draw;
                        } else {
                            Toast.makeText(SignUpActivity.this, "Tanda tangan tidak boleh kosong.", Toast.LENGTH_SHORT).show();
                        }

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

                        if (cekUsername && cekDepartemen && cekDivisi && cekNamaLengkap && cekRole && cekPassword && cekConfirmPassword && bitmap != null) {
                            if (!password.equals(confirmPassword)) {
                                binding.textInputEditTextConfirmPassword.setError("Password tidak sama");
                                return;
                            }
                            if (isConnectingToInternet()) {
                                signUp();
                            }
                        }
                    }

                    @Override
                    public void onDrawCreationError() {

                    }
                });
            }
        });
    }

    private void signUp() {
        progressDialog.show();

        String role = "";
        if (binding.dropdownRole.getText().toString().equalsIgnoreCase("Staff")) {
            role = "Staff";
        } else if (binding.dropdownRole.getText().toString().equalsIgnoreCase("PIC Admin")) {
            role = "PICA";
        } else if (binding.dropdownRole.getText().toString().equalsIgnoreCase("PIC Gudang")) {
            role = "PICG";
        } else if (binding.dropdownRole.getText().toString().equalsIgnoreCase("PIC Kendaraan")) {
            role = "PICK";
        } else if (binding.dropdownRole.getText().toString().equalsIgnoreCase("PIC Maintenance")) {
            role = "PICM";
        } else if (binding.dropdownRole.getText().toString().equalsIgnoreCase("Section Head")) {
            role = "Section Head";
        } else if (binding.dropdownRole.getText().toString().equalsIgnoreCase("Department Head")) {
            role = "Department Head";
        } else if (binding.dropdownRole.getText().toString().equalsIgnoreCase("Division Head")) {
            role = "Division Head";
        }

        viewModel.signUp(
                binding.textInputEditTextUsername.getText().toString(),
                binding.textInputEditTexNamaLengkap.getText().toString(),
                role,
                binding.dropdownDepartment.getText().toString(),
                binding.dropdownDivision.getText().toString(),
                binding.textInputEditTextPassword.getText().toString(),
                bitmap
        ).observe(this, new Observer<BaseResponse>() {
            @Override
            public void onChanged(BaseResponse baseResponse) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                if (baseResponse != null) {
                    new AlertDialog.Builder(SignUpActivity.this)
                            .setTitle("Pesan")
                            .setMessage(baseResponse.getMessage())
                            .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    finish();
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