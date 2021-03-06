package com.unitedtractors.android.unitedtractorsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.model.MonitoringLapanganModel;

import java.util.List;

public class MonitoringLapanganViewAdapter extends RecyclerView.Adapter<MonitoringLapanganViewAdapter.ViewHolder> {
    private List<MonitoringLapanganModel> list;

    public MonitoringLapanganViewAdapter(List<MonitoringLapanganModel> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_monitoring_lapangan, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewTanggal.setText(list.get(position).getTanggalView());
        holder.textViewJumlahPesanan.setText(list.get(position).getJumlahPesanan());
        holder.textViewActualBawa.setText(list.get(position).getActualBawa());
        holder.textViewActualKupon.setText(list.get(position).getActualKupon());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayoutForm;
        private TextView textViewUrutan, textViewTanggal, textViewJumlahPesanan, textViewActualBawa, textViewActualKupon;
        private ImageView imageViewExpand;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayoutForm = itemView.findViewById(R.id.linearLayoutForm);
            imageViewExpand = itemView.findViewById(R.id.imageViewExpand);
            textViewUrutan = itemView.findViewById(R.id.textViewUrutan);
            textViewTanggal = itemView.findViewById(R.id.textViewTanggal);
            textViewJumlahPesanan = itemView.findViewById(R.id.textViewJumlahPesanan);
            textViewActualBawa = itemView.findViewById(R.id.textViewActualBawa);
            textViewActualKupon = itemView.findViewById(R.id.textViewActualKupon);

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
        }
    }
}
