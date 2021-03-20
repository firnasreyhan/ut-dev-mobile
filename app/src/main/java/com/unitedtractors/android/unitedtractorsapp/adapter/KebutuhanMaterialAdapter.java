package com.unitedtractors.android.unitedtractorsapp.adapter;

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
import com.unitedtractors.android.unitedtractorsapp.model.InternalWorkOrderModel;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanMobilModel;

import java.util.List;

public class KebutuhanMaterialAdapter extends RecyclerView.Adapter<KebutuhanMaterialAdapter.ViewHolder> {

    private static List<InternalWorkOrderModel.DetailKebutuhan> list;
    private static boolean isEditable;

    public KebutuhanMaterialAdapter(List<InternalWorkOrderModel.DetailKebutuhan> list, boolean isEditable) {
        KebutuhanMaterialAdapter.list = list;
        KebutuhanMaterialAdapter.isEditable = isEditable;
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
    public KebutuhanMaterialAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_internal_work_order_form_input, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull KebutuhanMaterialAdapter.ViewHolder holder, int position) {
        holder.textViewUrutan.setText("Barang " + (position + 1));
        if (!isEditable) {
            holder.editTextNamaBarang.setText(list.get(position).getNama());
            holder.editTextQty.setText(list.get(position).getQuant() + " Unit");
            holder.editTextMusNo.setText(list.get(position).getMusNo());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewExpand;
        private final LinearLayout linearLayoutForm;
        private final TextView textViewUrutan;
        private final EditText editTextNamaBarang, editTextQty, editTextMusNo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewExpand = itemView.findViewById(R.id.imageViewExpand);
            linearLayoutForm = itemView.findViewById(R.id.linearLayoutForm);
            textViewUrutan = itemView.findViewById(R.id.textViewUrutan);
            editTextNamaBarang = itemView.findViewById(R.id.editTextNamaBarang);
            editTextQty = itemView.findViewById(R.id.editTextQty);
            editTextMusNo = itemView.findViewById(R.id.editTextMusNo);

            editTextNamaBarang.setEnabled(isEditable);
            editTextQty.setEnabled(isEditable);
            editTextMusNo.setEnabled(isEditable);

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

            if (isEditable) {
                editTextNamaBarang.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        list.get(getAdapterPosition()).setNama(editTextNamaBarang.getText().toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                editTextQty.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        list.get(getAdapterPosition()).setQuant(Integer.parseInt(editTextQty.getText().toString()));
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                editTextMusNo.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        list.get(getAdapterPosition()).setMusNo(editTextMusNo.getText().toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        }
    }

    public static List<InternalWorkOrderModel.DetailKebutuhan> getList() {
        return list;
    }
}
