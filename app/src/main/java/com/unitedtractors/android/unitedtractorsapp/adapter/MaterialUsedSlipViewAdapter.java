package com.unitedtractors.android.unitedtractorsapp.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.model.MaterialUsedSlipModel;

import java.util.List;

public class MaterialUsedSlipViewAdapter extends RecyclerView.Adapter<MaterialUsedSlipViewAdapter.ViewHolder> {

    private List<MaterialUsedSlipModel.DetailMaterialUsedSlipModel> list;

    public MaterialUsedSlipViewAdapter(List<MaterialUsedSlipModel.DetailMaterialUsedSlipModel> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public MaterialUsedSlipViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MaterialUsedSlipViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_material_used_slip_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialUsedSlipViewAdapter.ViewHolder holder, int position) {
        holder.textViewNamaBarang.setText(list.get(position).getNamaBarang());
        holder.textViewJumlahBarang.setText(list.get(position).getJumlahBarang() + " Unit");
        holder.textViewDipergunakan.setText(list.get(position).getDipergunakan());
        holder.textViewKeterangan.setText(list.get(position).getKeterangan());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNamaBarang, textViewJumlahBarang, textViewDipergunakan, textViewKeterangan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNamaBarang = itemView.findViewById(R.id.textViewNamaBarang);
            textViewJumlahBarang = itemView.findViewById(R.id.textViewJumlahBarang);
            textViewDipergunakan = itemView.findViewById(R.id.textViewDipergunakan);
            textViewKeterangan = itemView.findViewById(R.id.textViewKeterangan);
        }
    }
}
