package com.unitedtractors.android.unitedtractorsapp.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.model.LaporanPerbaikanModel;

import java.util.List;

public class LaporanPerbaikanAdapter extends RecyclerView.Adapter<LaporanPerbaikanAdapter.ViewHolder> {
    private static List<LaporanPerbaikanModel.DetailLaporanPerbaikan> list;
    private static boolean isEnable;

    public LaporanPerbaikanAdapter(List<LaporanPerbaikanModel.DetailLaporanPerbaikan> list, boolean isEnable) {
        LaporanPerbaikanAdapter.list = list;
        LaporanPerbaikanAdapter.isEnable = isEnable;
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_laporan_perbaikan_form_input, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewUrutan.setText("Laporan  " + (position + 1));
        holder.editTextLaporanPerbaikan.setText(list.get(position).getLaporanPerbaikan());
        holder.editTextKategori.setText(list.get(position).getKategoriPerbaikan());
        holder.editTextLokasi.setText(list.get(position).getLokasiPerbaikan());
        holder.editTextUser.setText(list.get(position).getUserPerbaikan());
        holder.editTextPIC.setText(list.get(position).getPicPerbaikan());
        holder.editTextDurasi.setText(list.get(position).getDurasiPerbaikan());
        holder.editTextStatus.setText(list.get(position).getStatusPerbaikan());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageViewExpand;
        private final LinearLayout linearLayoutForm;
        private final TextView textViewUrutan;
        private final EditText editTextLaporanPerbaikan, editTextKategori, editTextLokasi, editTextUser, editTextPIC, editTextDurasi, editTextStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewExpand = itemView.findViewById(R.id.imageViewExpand);
            linearLayoutForm = itemView.findViewById(R.id.linearLayoutForm);
            textViewUrutan = itemView.findViewById(R.id.textViewUrutan);
            editTextLaporanPerbaikan = itemView.findViewById(R.id.editTextLaporanPerbaikan);
            editTextKategori = itemView.findViewById(R.id.editTextKategori);
            editTextLokasi = itemView.findViewById(R.id.editTextLokasi);
            editTextUser = itemView.findViewById(R.id.editTextUser);
            editTextPIC = itemView.findViewById(R.id.editTextPIC);
            editTextDurasi = itemView.findViewById(R.id.editTextDurasi);
            editTextStatus = itemView.findViewById(R.id.editTextStatus);

            editTextLaporanPerbaikan.setEnabled(isEnable);
            editTextKategori.setEnabled(isEnable);
            editTextLokasi.setEnabled(isEnable);
            editTextUser.setEnabled(isEnable);
            editTextPIC.setEnabled(isEnable);
            editTextDurasi.setEnabled(isEnable);
            editTextStatus.setEnabled(isEnable);

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

            editTextLaporanPerbaikan.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setLaporanPerbaikan(editTextLaporanPerbaikan.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextKategori.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setKategoriPerbaikan(editTextKategori.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextLokasi.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setLokasiPerbaikan(editTextLokasi.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextUser.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setUserPerbaikan(editTextUser.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextPIC.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setPicPerbaikan(editTextPIC.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextDurasi.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setDurasiPerbaikan(editTextDurasi.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextStatus.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setStatusPerbaikan(editTextStatus.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    public static List<LaporanPerbaikanModel.DetailLaporanPerbaikan> getList() {
        return list;
    }
}
