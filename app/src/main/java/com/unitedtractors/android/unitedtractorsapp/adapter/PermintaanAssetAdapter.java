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

import com.tobiasschuerg.prefixsuffix.PrefixSuffixEditText;
import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanAssetModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PermintaanAssetAdapter extends RecyclerView.Adapter<PermintaanAssetAdapter.ViewHolder> {
    private static List<PermintaanAssetModel> list;
    private static boolean isEditable;

    public PermintaanAssetAdapter(List<PermintaanAssetModel> list, boolean isEditable) {
        PermintaanAssetAdapter.list = list;
        PermintaanAssetAdapter.isEditable = isEditable;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_permintaan_asset_form_input, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewUrutan.setText("Barang " + (position + 1));
        holder.editTextNamaBarang.setText(list.get(position).getNamaBarang());
        holder.editTextAlasanFungsi.setText(list.get(position).getAlasanFungsi());
        holder.editTextManfaatBagiPerusahaan.setText(list.get(position).getManfaatBagiPerusahaan());
        holder.prefixSuffixEditTextQuantity.setText(list.get(position).getQuantity());
        holder.editTextTanggal.setText(list.get(position).getTanggal());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageViewExpand;
        private final LinearLayout linearLayoutForm;
        private final TextView textViewUrutan;
        private final EditText editTextNamaBarang, editTextAlasanFungsi, editTextManfaatBagiPerusahaan, editTextTanggal;
        private final PrefixSuffixEditText prefixSuffixEditTextQuantity;
        private final Calendar calendar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            calendar = Calendar.getInstance();

            imageViewExpand = itemView.findViewById(R.id.imageViewExpand);
            linearLayoutForm = itemView.findViewById(R.id.linearLayoutForm);
            textViewUrutan = itemView.findViewById(R.id.textViewUrutan);
            editTextNamaBarang = itemView.findViewById(R.id.editTextNamaBarang);
            editTextAlasanFungsi = itemView.findViewById(R.id.editTextAlasanFungsi);
            editTextManfaatBagiPerusahaan = itemView.findViewById(R.id.editTextManfaatBagiPerusahaan);
            prefixSuffixEditTextQuantity = itemView.findViewById(R.id.prefixSuffixEditTextQuantity);
            editTextTanggal = itemView.findViewById(R.id.editTextTanggal);

            editTextNamaBarang.setEnabled(isEditable);
            editTextAlasanFungsi.setEnabled(isEditable);
            editTextManfaatBagiPerusahaan.setEnabled(isEditable);
            prefixSuffixEditTextQuantity.setEnabled(isEditable);
            editTextTanggal.setEnabled(isEditable);

            imageViewExpand.setOnClickListener(new View.OnClickListener() {
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

            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    // TODO Auto-generated method stub
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    SimpleDateFormat simpleDateFormatView = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
                    editTextTanggal.setText(simpleDateFormatView.format(calendar.getTime()));
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

            editTextNamaBarang.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setNamaBarang(editTextNamaBarang.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextAlasanFungsi.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setAlasanFungsi(editTextAlasanFungsi.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextManfaatBagiPerusahaan.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setManfaatBagiPerusahaan(editTextManfaatBagiPerusahaan.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            prefixSuffixEditTextQuantity.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setQuantity(prefixSuffixEditTextQuantity.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextTanggal.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setTanggal(editTextTanggal.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    public static List<PermintaanAssetModel> getList() {
        return list;
    }
}
