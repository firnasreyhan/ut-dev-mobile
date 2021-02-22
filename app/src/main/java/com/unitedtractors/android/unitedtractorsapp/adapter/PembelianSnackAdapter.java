package com.unitedtractors.android.unitedtractorsapp.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.model.PembelianSnackModel;

import java.util.List;

public class PembelianSnackAdapter extends RecyclerView.Adapter<PembelianSnackAdapter.ViewHolder> {
    private static List<PembelianSnackModel> list;
    private static boolean isEnable;

    public PembelianSnackAdapter(List<PembelianSnackModel> list, boolean isEnable) {
        PembelianSnackAdapter.list = list;
        PembelianSnackAdapter.isEnable = isEnable;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pembelian_snack_form_input, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (!list.get(position).getJenisSnack().isEmpty() && !list.get(position).getJumlah().isEmpty()) {
            holder.editTextJenisSnack.setText(list.get(position).getJenisSnack());
            holder.editTextJumlah.setText(list.get(position).getJumlah());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private EditText editTextJenisSnack, editTextJumlah;
        private MaterialButton materialButtonKurang, materialButtonTambah;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            editTextJenisSnack = itemView.findViewById(R.id.editTextJenisSnack);
            editTextJumlah = itemView.findViewById(R.id.editTextJumlah);
            materialButtonKurang = itemView.findViewById(R.id.materialButtonKurang);
            materialButtonTambah = itemView.findViewById(R.id.materialButtonTambah);

            editTextJenisSnack.setEnabled(isEnable);

            if (isEnable) {
                materialButtonKurang.setVisibility(View.VISIBLE);
                materialButtonTambah.setVisibility(View.VISIBLE);

                materialButtonTambah.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int jumlah = Integer.parseInt(editTextJumlah.getText().toString());
                        jumlah++;
                        editTextJumlah.setText(String.valueOf(jumlah));
                    }
                });

                materialButtonKurang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int jumlah = Integer.parseInt(editTextJumlah.getText().toString());
                        if (jumlah > 0) {
                            jumlah--;
                            editTextJumlah.setText(String.valueOf(jumlah));
                        }
                    }
                });

                editTextJenisSnack.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        list.get(getAdapterPosition()).setJenisSnack(editTextJenisSnack.getText().toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                editTextJumlah.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        list.get(getAdapterPosition()).setJumlah(editTextJumlah.getText().toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            } else {
                materialButtonKurang.setVisibility(View.GONE);
                materialButtonTambah.setVisibility(View.GONE);
            }
        }
    }

    public static List<PembelianSnackModel> getList() {
        return list;
    }

    public void addData(int jumlah) {
        for (int i = 0; i < jumlah; i++) {
            list.add(new PembelianSnackModel("",""));
        }
        notifyDataSetChanged();
    }
}
