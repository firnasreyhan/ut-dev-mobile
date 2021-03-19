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
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistAlatKomunikasiModel;
import com.unitedtractors.android.unitedtractorsapp.model.PertanyaanModel;

import java.util.List;

public class ChecklistAlatKomunikasiAdapter extends RecyclerView.Adapter<ChecklistAlatKomunikasiAdapter.ViewHolder> {
    private static List<ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi> list;

    public ChecklistAlatKomunikasiAdapter(List<ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi> list) {
        ChecklistAlatKomunikasiAdapter.list = list;
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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checklist_alat_komunikasi, parent, false));
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewPertanyaan = itemView.findViewById(R.id.textViewPertanyaan);
            radioGroup = itemView.findViewById(R.id.radioGroup);

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
        }
    }

    public static List<ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi> getList() {
        return list;
    }
}
