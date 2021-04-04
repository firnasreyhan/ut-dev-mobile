package com.unitedtractors.android.unitedtractorsapp.adapter;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.api.response.DokumenPendukungResponse;
import com.unitedtractors.android.unitedtractorsapp.model.InternalWorkOrderModel;
import com.unitedtractors.android.unitedtractorsapp.view.activity.approval.DetailDokumenPendukungActivity;

import java.util.List;

public class DokumenPendukungAdapter extends RecyclerView.Adapter<DokumenPendukungAdapter.ViewHolder> {

    private List<DokumenPendukungResponse.DetailDokumenPendukung> list;

    public DokumenPendukungAdapter(List<DokumenPendukungResponse.DetailDokumenPendukung> list) {
        this.list = list;
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
    public DokumenPendukungAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokumen_pendukung, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DokumenPendukungAdapter.ViewHolder holder, int position) {
        holder.textViewNamaDokumen.setText(list.get(position).getFileName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewNamaDokumen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNamaDokumen = itemView.findViewById(R.id.textViewNamaDokumen);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailDokumenPendukungActivity.class);
                    intent.putExtra("LINK", list.get(getAdapterPosition()).getLink());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
