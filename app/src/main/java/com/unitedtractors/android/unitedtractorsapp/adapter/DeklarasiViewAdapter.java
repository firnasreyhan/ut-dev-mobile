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
import com.unitedtractors.android.unitedtractorsapp.model.DeklarasiModel;
import com.unitedtractors.android.unitedtractorsapp.model.MaterialUsedSlipModel;

import java.util.List;

public class DeklarasiViewAdapter extends RecyclerView.Adapter<DeklarasiViewAdapter.ViewHolder> {

    private List<DeklarasiModel.DetKeperluan> list;

    public DeklarasiViewAdapter(List<DeklarasiModel.DetKeperluan> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public DeklarasiViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DeklarasiViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deklarasi_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DeklarasiViewAdapter.ViewHolder holder, int position) {
        holder.textViewUrutan.setText("Keperluan " + (position + 1));
        holder.textViewBBM.setText(String.valueOf(list.get(position).getBbm()));
        holder.textViewTolParkir.setText(String.valueOf(list.get(position).getTol()));
        holder.textViewGrab.setText(String.valueOf(list.get(position).getGrab()));
        holder.textViewLainLain.setText(String.valueOf(list.get(position).getLain()));
        holder.textViewJumlah.setText(String.valueOf(list.get(position).getBbm() + list.get(position).getTol() + list.get(position).getGrab() + list.get(position).getLain()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageViewExpand;
        private final LinearLayout linearLayoutForm;
        private final TextView textViewUrutan;
        private final TextView textViewBBM, textViewTolParkir, textViewGrab, textViewLainLain, textViewJumlah;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewExpand = itemView.findViewById(R.id.imageViewExpand);
            linearLayoutForm = itemView.findViewById(R.id.linearLayoutForm);
            textViewUrutan = itemView.findViewById(R.id.textViewUrutan);
            textViewBBM = itemView.findViewById(R.id.textViewBBM);
            textViewTolParkir = itemView.findViewById(R.id.textViewTolParkir);
            textViewGrab = itemView.findViewById(R.id.textViewGrab);
            textViewLainLain = itemView.findViewById(R.id.textViewLainLain);
            textViewJumlah = itemView.findViewById(R.id.textViewJumlah);

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
