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
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistForHydrantModel;

import java.util.List;

public class ChecklistForHydrantAdapter extends RecyclerView.Adapter<ChecklistForHydrantAdapter.ViewHolder> {
    private static List<ChecklistForHydrantModel.DetailChecklistHydrant> list;

    public ChecklistForHydrantAdapter(List<ChecklistForHydrantModel.DetailChecklistHydrant> list) {
        ChecklistForHydrantAdapter.list = list;
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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checklist_for_hydrant_form_input, parent, false));
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
        private final TextView textViewPertanyaan;
        private final RadioGroup radioGroup;
        private final RadioButton radioButtonOK;
        private final RadioButton radioButtonPerbaikan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewPertanyaan = itemView.findViewById(R.id.textViewPertanyaan);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            radioButtonOK = itemView.findViewById(R.id.radioButtonOK);
            radioButtonPerbaikan = itemView.findViewById(R.id.radioButtonPerbaikan);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radioButtonOK:
                            list.get(getAdapterPosition()).setStatus(1);
                            break;
                        case R.id.radioButtonPerbaikan:
                            list.get(getAdapterPosition()).setStatus(0);
                            break;
                    }
                }
            });
        }
    }

    public static List<ChecklistForHydrantModel.DetailChecklistHydrant> getList() {
        return list;
    }
}
