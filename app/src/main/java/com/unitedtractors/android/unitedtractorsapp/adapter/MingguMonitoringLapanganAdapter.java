package com.unitedtractors.android.unitedtractorsapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguMonitoringCateringEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguPompaAirBersihEntity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_pompa_air_bersih.ListChecklistPompaAirBersihActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.monitoring_lapangan.ListMonitoringLapanganActivity;

import java.util.List;

public class MingguMonitoringLapanganAdapter extends RecyclerView.Adapter<MingguMonitoringLapanganAdapter.ViewHolder> {
    private List<MingguMonitoringCateringEntity> list;

    public MingguMonitoringLapanganAdapter(List<MingguMonitoringCateringEntity> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_minggu, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewMingguKe.setText(list.get(position).mingguKe);
        holder.textViewStatus.setText(list.get(position).status ? "Selesai Diisi" : "Belum Diisi");

        if (list.get(position).status) {
            holder.textViewStatus.setTextColor(holder.itemView.getResources().getColor(R.color.approve));
            holder.imageViewStatus.setImageResource(R.drawable.ic_approve);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewMingguKe, textViewStatus;
        private ImageView imageViewStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMingguKe = itemView.findViewById(R.id.textViewMingguKe);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            imageViewStatus = itemView.findViewById(R.id.imageViewStatus);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ListMonitoringLapanganActivity.class);
                    intent.putExtra("ID", list.get(getAdapterPosition()).id);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
