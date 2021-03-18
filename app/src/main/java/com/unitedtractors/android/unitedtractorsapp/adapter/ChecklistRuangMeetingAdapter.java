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
import com.unitedtractors.android.unitedtractorsapp.model.Pertanyaan4Model;

import java.util.List;

public class ChecklistRuangMeetingAdapter extends RecyclerView.Adapter<ChecklistRuangMeetingAdapter.ViewHolder> {
    private static List<Pertanyaan4Model> list;

    public ChecklistRuangMeetingAdapter(List<Pertanyaan4Model> list) {
        ChecklistRuangMeetingAdapter.list = list;
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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checklist_ruang_meeting, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewPertanyaan.setText(list.get(position).getPertanyaan());
        if (list.get(position).getStatus() == 1) {
            holder.radioButtonAda.setChecked(true);
        } else if (list.get(position).getStatus() == 2) {
            holder.radioButtonTidakAda.setChecked(true);
        } else {
            holder.radioButtonBaik.setChecked(true);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewPertanyaan;
        private RadioGroup radioGroup;
        private RadioButton radioButtonAda, radioButtonTidakAda, radioButtonBaik;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewPertanyaan = itemView.findViewById(R.id.textViewPertanyaan);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            radioButtonAda = itemView.findViewById(R.id.radioButtonAda);
            radioButtonTidakAda = itemView.findViewById(R.id.radioButtonTidakAda);
            radioButtonBaik = itemView.findViewById(R.id.radioButtonBaik);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radioButtonAda:
                            list.get(getAdapterPosition()).setStatus(1);
                            break;
                        case R.id.radioButtonTidakAda:
                            list.get(getAdapterPosition()).setStatus(2);
                            break;
                        case R.id.radioButtonBaik:
                            list.get(getAdapterPosition()).setStatus(3);
                            break;
                    }
                }
            });
        }
    }

    public static List<Pertanyaan4Model> getList() {
        return list;
    }
}
