package com.unitedtractors.android.unitedtractorsapp.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.model.MaterialUsedSlipModel;
import java.util.List;

public class MaterialUsedSlipAdapter  extends RecyclerView.Adapter<MaterialUsedSlipAdapter.ViewHolder> {

    private static List<MaterialUsedSlipModel.DetailMaterialUsedSlipModel> list;

    public MaterialUsedSlipAdapter(List<MaterialUsedSlipModel.DetailMaterialUsedSlipModel> list) {
        MaterialUsedSlipAdapter.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public MaterialUsedSlipAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MaterialUsedSlipAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_material_used_slip, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialUsedSlipAdapter.ViewHolder holder, int position) {
        holder.editTextKeterangan.setText(list.get(position).getKeterangan());
        holder.editTextDipergunakan.setText(list.get(position).getDipergunakan());
        holder.editTextNamaBarang.setText(list.get(position).getNamaBarang());
        holder.editTextJumlahBarang.setText(list.get(position).getJumlahBarang());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final EditText editTextNamaBarang, editTextKeterangan, editTextJumlahBarang, editTextDipergunakan;
        int jumlahBarang;
        MaterialButton materialButtonTambahJumlahBarang, materialButtonKurangJumlahBarang;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            editTextNamaBarang = itemView.findViewById(R.id.editTextNamaBarang);
            editTextKeterangan = itemView.findViewById(R.id.editTextKeterangan);
            editTextJumlahBarang = itemView.findViewById(R.id.editTextJumlahBarang);
            editTextDipergunakan = itemView.findViewById(R.id.editTextDipergunakan);
            materialButtonKurangJumlahBarang = itemView.findViewById(R.id.materialButtonKurangJumlahBarang);
            materialButtonTambahJumlahBarang = itemView.findViewById(R.id.materialButtonTambahJumlahBarang);

            editTextNamaBarang.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setNamaBarang(editTextNamaBarang.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextDipergunakan.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setDipergunakan(editTextDipergunakan.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextKeterangan.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setKeterangan(editTextKeterangan.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            jumlahBarang = Integer.parseInt(String.valueOf(editTextJumlahBarang.getText().toString()));

            materialButtonTambahJumlahBarang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jumlahBarang++;
                    editTextJumlahBarang.setText(String.valueOf(jumlahBarang));
                }
            });

            materialButtonKurangJumlahBarang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (jumlahBarang > 0) {
                        jumlahBarang--;
                        editTextJumlahBarang.setText(String.valueOf(jumlahBarang));
                    }
                }
            });
        }
    }

    public static List<MaterialUsedSlipModel.DetailMaterialUsedSlipModel> getList() {
        return list;
    }
}
