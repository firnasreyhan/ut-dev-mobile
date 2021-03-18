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
import com.unitedtractors.android.unitedtractorsapp.model.SurveyKepuasanPelangganModel;

import java.util.List;

public class SurveyKepuasanPelangganAdapter extends RecyclerView.Adapter<SurveyKepuasanPelangganAdapter.ViewHolder> {
    private static List<SurveyKepuasanPelangganModel.DetailSurveyKepuasanPelangan> list;

    public SurveyKepuasanPelangganAdapter(List<SurveyKepuasanPelangganModel.DetailSurveyKepuasanPelangan> list) {
        SurveyKepuasanPelangganAdapter.list = list;
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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_survey_kepuasan_pelanggan, parent, false));
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
                        case R.id.radioButtonSangatPuas:
                            list.get(getAdapterPosition()).setStatus(5);
                            break;
                        case R.id.radioButtonPuas:
                            list.get(getAdapterPosition()).setStatus(4);
                            break;
                        case R.id.radioButtonCukupPuas:
                            list.get(getAdapterPosition()).setStatus(3);
                            break;
                        case R.id.radioButtonTidakPuas:
                            list.get(getAdapterPosition()).setStatus(2);
                            break;
                        case R.id.radioButtonSangatTidakPuas:
                            list.get(getAdapterPosition()).setStatus(1);
                            break;
                    }
                }
            });
        }
    }

    public static List<SurveyKepuasanPelangganModel.DetailSurveyKepuasanPelangan> getList() {
        return list;
    }
}
