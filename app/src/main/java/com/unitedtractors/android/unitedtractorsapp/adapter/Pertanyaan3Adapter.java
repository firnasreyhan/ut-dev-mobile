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
import com.unitedtractors.android.unitedtractorsapp.model.Pertanyaan2Model;
import com.unitedtractors.android.unitedtractorsapp.model.Pertanyaan3Model;

import java.util.List;

public class Pertanyaan3Adapter extends RecyclerView.Adapter<Pertanyaan3Adapter.ViewHolder> {
    private static List<Pertanyaan3Model> list;

    public Pertanyaan3Adapter(List<Pertanyaan3Model> list) {
        Pertanyaan3Adapter.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pertanyaan_3, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewPertanyaan.setText(list.get(position).getPertanyaan());
        holder.textViewKeterangan.setText(list.get(position).getKeterangan());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewPertanyaan, textViewKeterangan;
        private RadioGroup radioGroup;
        private RadioButton radioButtonOK, radioButtonPerbaikan;
        private EditText editTextCatatan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewPertanyaan = itemView.findViewById(R.id.textViewPertanyaan);
            textViewKeterangan = itemView.findViewById(R.id.textViewKeterangan);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            radioButtonOK = itemView.findViewById(R.id.radioButtonOK);
            radioButtonPerbaikan = itemView.findViewById(R.id.radioButtonPerbaikan);
            editTextCatatan = itemView.findViewById(R.id.editTextCatatan);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radioButtonOK:
                            list.get(getAdapterPosition()).setStatus(true);
                            break;
                        case R.id.radioButtonPerbaikan:
                            list.get(getAdapterPosition()).setStatus(false);
                            break;
                    }
                }
            });

            editTextCatatan.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setCatatan(editTextCatatan.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }
}
