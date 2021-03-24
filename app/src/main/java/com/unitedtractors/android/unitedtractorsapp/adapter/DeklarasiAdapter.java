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
import com.unitedtractors.android.unitedtractorsapp.model.DeklarasiModel;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanMobilModel;

import java.util.List;

public class DeklarasiAdapter extends RecyclerView.Adapter<DeklarasiAdapter.ViewHolder> {

    private static List<DeklarasiModel.DetKeperluan> list;

    public DeklarasiAdapter(List<DeklarasiModel.DetKeperluan> list) {
        DeklarasiAdapter.list = list;
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
    public DeklarasiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deklarasi_form_input, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DeklarasiAdapter.ViewHolder holder, int position) {
        holder.textViewUrutan.setText("Keperluan " + (position + 1));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageViewExpand;
        private final LinearLayout linearLayoutForm;
        private final TextView textViewUrutan;
        private final EditText editTextBBM, editTextTolParkir, editTextGrab, editTextLainLain, editTextJumlah;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewExpand = itemView.findViewById(R.id.imageViewExpand);
            linearLayoutForm = itemView.findViewById(R.id.linearLayoutForm);
            textViewUrutan = itemView.findViewById(R.id.textViewUrutan);
            editTextBBM = itemView.findViewById(R.id.editTextBBM);
            editTextTolParkir = itemView.findViewById(R.id.editTextTolParkir);
            editTextGrab = itemView.findViewById(R.id.editTextGrab);
            editTextLainLain = itemView.findViewById(R.id.editTextLainLain);
            editTextJumlah = itemView.findViewById(R.id.editTextJumlah);

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

            editTextBBM.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setBbm(Integer.parseInt(editTextBBM.getText().toString()));
                    editTextJumlah.setText(String.valueOf(list.get(getAdapterPosition()).getBbm() + list.get(getAdapterPosition()).getTol() + list.get(getAdapterPosition()).getGrab() + list.get(getAdapterPosition()).getLain()));
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextTolParkir.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setTol(Integer.parseInt(editTextTolParkir.getText().toString()));
                    editTextJumlah.setText(String.valueOf(list.get(getAdapterPosition()).getBbm() + list.get(getAdapterPosition()).getTol() + list.get(getAdapterPosition()).getGrab() + list.get(getAdapterPosition()).getLain()));
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextGrab.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setGrab(Integer.parseInt(editTextGrab.getText().toString()));
                    editTextJumlah.setText(String.valueOf(list.get(getAdapterPosition()).getBbm() + list.get(getAdapterPosition()).getTol() + list.get(getAdapterPosition()).getGrab() + list.get(getAdapterPosition()).getLain()));
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextLainLain.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setLain(Integer.parseInt(editTextLainLain.getText().toString()));
                    editTextJumlah.setText(String.valueOf(list.get(getAdapterPosition()).getBbm() + list.get(getAdapterPosition()).getTol() + list.get(getAdapterPosition()).getGrab() + list.get(getAdapterPosition()).getLain()));
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    public static List<DeklarasiModel.DetKeperluan> getList() {
        return list;
    }
}
