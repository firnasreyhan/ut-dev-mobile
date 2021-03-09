package com.unitedtractors.android.unitedtractorsapp.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.model.KontrolHarianModel;
import com.unitedtractors.android.unitedtractorsapp.model.PertanyaanModel;

import java.util.List;

public class KontrolHarianAdapter extends RecyclerView.Adapter<KontrolHarianAdapter.ViewHolder> {
    private static List<KontrolHarianModel.DetailKontrolHarian> list;

    public KontrolHarianAdapter(List<KontrolHarianModel.DetailKontrolHarian> list) {
        KontrolHarianAdapter.list = list;
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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_form_kontrol_harian, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewJudul.setText(list.get(position).getJudul());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewJudul;
        private RadioGroup radioGroup;
        private RadioButton radioButtonBaikSekali, radioButtonBaik, radioButtonCukup, radioButtonKurang;
        private EditText editTextKeterangan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewJudul = itemView.findViewById(R.id.textViewJudul);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            radioButtonBaikSekali = itemView.findViewById(R.id.radioButtonBaikSekali);
            radioButtonBaik = itemView.findViewById(R.id.radioButtonBaik);
            radioButtonCukup = itemView.findViewById(R.id.radioButtonCukup);
            radioButtonKurang = itemView.findViewById(R.id.radioButtonKurang);
            editTextKeterangan = itemView.findViewById(R.id.editTextKeterangan);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radioButtonBaikSekali:
                            list.get(getAdapterPosition()).setStatus(1);
                            break;
                        case R.id.radioButtonBaik:
                            list.get(getAdapterPosition()).setStatus(2);
                            break;
                        case R.id.radioButtonCukup:
                            list.get(getAdapterPosition()).setStatus(3);
                            break;
                        case R.id.radioButtonKurang:
                            list.get(getAdapterPosition()).setStatus(4);
                            break;
                    }
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

    public static List<KontrolHarianModel.DetailKontrolHarian> getList() {
        return list;
    }
}
