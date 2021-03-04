package com.unitedtractors.android.unitedtractorsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.model.PertanyaanModel;

import java.util.List;

public class PertanyaanAdapter extends RecyclerView.Adapter<PertanyaanAdapter.ViewHolder> {
    private static List<PertanyaanModel> list;

    public PertanyaanAdapter(List<PertanyaanModel> list) {
        PertanyaanAdapter.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pertanyaan, parent, false));
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
        private RadioButton radioButtonIya, radioButtonTidak;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewPertanyaan = itemView.findViewById(R.id.textViewPertanyaan);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            radioButtonIya = itemView.findViewById(R.id.radioButtonIya);
            radioButtonTidak = itemView.findViewById(R.id.radioButtonTidak);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radioButtonIya:
                            list.get(getAdapterPosition()).setStatus(true);
                            break;
                        case R.id.radioButtonTidak:
                            list.get(getAdapterPosition()).setStatus(false);
                            break;
                    }
                }
            });
        }
    }
}
