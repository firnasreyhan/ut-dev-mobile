package com.unitedtractors.android.unitedtractorsapp.adapter;

import android.app.DatePickerDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.model.NonAssetModel;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanMobilModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PermintaanNonAssetAdapter extends RecyclerView.Adapter<PermintaanNonAssetAdapter.ViewHolder> {

    private static List<NonAssetModel.DetNonAsset> list;
    private static boolean isEditable;

    public PermintaanNonAssetAdapter(List<NonAssetModel.DetNonAsset> list) {
        PermintaanNonAssetAdapter.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public PermintaanNonAssetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_permintaan_non_asset_form_input, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PermintaanNonAssetAdapter.ViewHolder holder, int position) {
        holder.textViewUrutan.setText("Permintaan " + (position + 1));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageViewExpand;
        private final LinearLayout linearLayoutForm;
        private final TextView textViewUrutan;
        private final EditText editTextTanggal, editTextJenisBarangJasaSpesifikasi, editTextAccount, editTextCostCenter, editTextJumlah, editTextKeterangan;
        private final Calendar calendar;
        private int jumlahPesanan;
        private final MaterialButton materialButtonKurang, materialButtonTambah;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            calendar = Calendar.getInstance();
            imageViewExpand = itemView.findViewById(R.id.imageViewExpand);
            linearLayoutForm = itemView.findViewById(R.id.linearLayoutForm);
            textViewUrutan = itemView.findViewById(R.id.textViewUrutan);
            editTextTanggal = itemView.findViewById(R.id.editTextTanggal);
            editTextJenisBarangJasaSpesifikasi = itemView.findViewById(R.id.editTextJenisBarangJasaSpesifikasi);
            editTextAccount = itemView.findViewById(R.id.editTextAccount);
            editTextCostCenter = itemView.findViewById(R.id.editTextCostCenter);
            editTextJumlah = itemView.findViewById(R.id.editTextJumlah);
            editTextKeterangan = itemView.findViewById(R.id.editTextKeterangan);
            materialButtonKurang = itemView.findViewById(R.id.materialButtonKurang);
            materialButtonTambah = itemView.findViewById(R.id.materialButtonTambah);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (linearLayoutForm.getVisibility() == View.GONE) {
                        linearLayoutForm.setVisibility(View.VISIBLE);
                        imageViewExpand.setImageResource(R.drawable.ic_arrow_drop_up);
                    } else if (linearLayoutForm.getVisibility() == View.VISIBLE) {
                        linearLayoutForm.setVisibility(View.GONE);
                        imageViewExpand.setImageResource(R.drawable.ic_arrow_drop_down);
                    }
                }
            });

            SimpleDateFormat simpleDateFormatView = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
            SimpleDateFormat simpleDateFormatServer = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    // TODO Auto-generated method stub
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    editTextTanggal.setText(simpleDateFormatView.format(calendar.getTime()));
                    list.get(getAdapterPosition()).setTgl(simpleDateFormatServer.format(calendar.getTime()));
                }
            };

            editTextTanggal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DatePickerDialog(itemView.getContext(), date, calendar
                            .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            });

            jumlahPesanan = Integer.parseInt(String.valueOf(editTextJumlah.getText().toString()));
            materialButtonTambah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jumlahPesanan++;
                    editTextJumlah.setText(String.valueOf(jumlahPesanan));
                    list.get(getAdapterPosition()).setJmlPesan(jumlahPesanan);
                }
            });

            materialButtonKurang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (jumlahPesanan > 0) {
                        jumlahPesanan--;
                        editTextJumlah.setText(String.valueOf(jumlahPesanan));
                        list.get(getAdapterPosition()).setJmlPesan(jumlahPesanan);
                    }
                }
            });

            editTextJenisBarangJasaSpesifikasi.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setJenBarang(editTextJenisBarangJasaSpesifikasi.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextAccount.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setAccount(editTextAccount.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextCostCenter.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setCost(editTextCostCenter.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextKeterangan.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setKeterangan(editTextKeterangan.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    public static List<NonAssetModel.DetNonAsset> getList() {
        return list;
    }
}
