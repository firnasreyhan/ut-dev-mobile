package com.unitedtractors.android.unitedtractorsapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.view.model.PermintaanAssetModel;

import java.util.List;

public class PermintaanAssetAdapter extends RecyclerView.Adapter<PermintaanAssetAdapter.ViewHolder> {
    private List<PermintaanAssetModel> list;

    public PermintaanAssetAdapter(List<PermintaanAssetModel> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PermintaanAssetAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_permintaan_asset_form_input, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewUrutan.setText("Barang " + (position + 1));
        holder.imageViewExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.linearLayoutForm.getVisibility() == View.GONE) {
                    holder.linearLayoutForm.setVisibility(View.VISIBLE);
                    holder.imageViewExpand.setImageResource(R.drawable.ic_arrow_drop_up);
                } else if (holder.linearLayoutForm.getVisibility() == View.VISIBLE) {
                    holder.linearLayoutForm.setVisibility(View.GONE);
                    holder.imageViewExpand.setImageResource(R.drawable.ic_arrow_drop_down);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewExpand;
        private LinearLayout linearLayoutForm;
        private TextView textViewUrutan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewExpand = itemView.findViewById(R.id.imageViewExpand);
            linearLayoutForm = itemView.findViewById(R.id.linearLayoutForm);
            textViewUrutan = itemView.findViewById(R.id.textViewUrutan);
        }
    }
}
