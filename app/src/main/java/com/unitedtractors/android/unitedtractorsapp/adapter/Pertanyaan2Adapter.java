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
import com.unitedtractors.android.unitedtractorsapp.model.PertanyaanModel;

import java.util.List;

public class Pertanyaan2Adapter extends RecyclerView.Adapter<Pertanyaan2Adapter.ViewHolder> {
    private static List<Pertanyaan2Model> list;

    public Pertanyaan2Adapter(List<Pertanyaan2Model> list) {
        Pertanyaan2Adapter.list = list;
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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pertanyaan_2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewPertanyaan.setText(list.get(position).getPertanyaan());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewPertanyaan;
        private RadioGroup radioGroup;
        private RadioButton radioButtonOK, radioButtonReplacement, radioButtonService;
        private EditText editTextCatatan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewPertanyaan = itemView.findViewById(R.id.textViewPertanyaan);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            radioButtonOK = itemView.findViewById(R.id.radioButtonOK);
            radioButtonReplacement = itemView.findViewById(R.id.radioButtonReplacement);
            radioButtonService = itemView.findViewById(R.id.radioButtonService);
            editTextCatatan = itemView.findViewById(R.id.editTextCatatan);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radioButtonOK:
                            list.get(getAdapterPosition()).setStatus(1);
                            break;
                        case R.id.radioButtonReplacement:
                            list.get(getAdapterPosition()).setStatus(2);
                            break;
                        case R.id.radioButtonService:
                            list.get(getAdapterPosition()).setStatus(3);
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

    public static List<Pertanyaan2Model> getList() {
        return list;
    }
}
