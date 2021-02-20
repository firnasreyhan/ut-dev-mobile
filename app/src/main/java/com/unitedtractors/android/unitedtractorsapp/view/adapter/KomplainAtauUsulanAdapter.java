package com.unitedtractors.android.unitedtractorsapp.view.adapter;

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
import com.unitedtractors.android.unitedtractorsapp.view.model.TujuanMobilDinasModel;

import java.util.List;

public class TujuanPermintaanMobilDinasAdapter extends RecyclerView.Adapter<TujuanPermintaanMobilDinasAdapter.ViewHolder> {

    private static List<TujuanMobilDinasModel> list;
    private static boolean isEditable;

    public TujuanPermintaanMobilDinasAdapter(List<TujuanMobilDinasModel> list, boolean isEditable) {
        TujuanPermintaanMobilDinasAdapter.list = list;
        TujuanPermintaanMobilDinasAdapter.isEditable = isEditable;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public TujuanPermintaanMobilDinasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new TujuanPermintaanMobilDinasAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tujuan_permintaan_mobil_dinas, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TujuanPermintaanMobilDinasAdapter.ViewHolder holder, int position) {
        holder.textViewUrutan.setText("Tujuan " + (position + 1));
        holder.editTextTujuan.setText(list.get(position).getTujuan());
        holder.editTextKeperluan.setText(list.get(position).getKeperluan());
        holder.editTextCatatan.setText(list.get(position).getCatatan());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewExpand;
        private final LinearLayout linearLayoutForm;
        private final TextView textViewUrutan;
        private final EditText editTextTujuan, editTextKeperluan, editTextCatatan;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewExpand = itemView.findViewById(R.id.imageViewExpand);
            linearLayoutForm = itemView.findViewById(R.id.linearLayoutForm);
            textViewUrutan = itemView.findViewById(R.id.textViewUrutan);
            editTextTujuan = itemView.findViewById(R.id.editTextTujuan);
            editTextKeperluan = itemView.findViewById(R.id.editTextKeperluan);
            editTextCatatan = itemView.findViewById(R.id.editTextCatatan);

            editTextTujuan.setEnabled(isEditable);
            editTextKeperluan.setEnabled(isEditable);
            editTextCatatan.setEnabled(isEditable);

            imageViewExpand.setOnClickListener(new View.OnClickListener() {
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

            editTextTujuan.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setTujuan(editTextTujuan.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextKeperluan.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setKeperluan(editTextKeperluan.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextCatatan.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setCatatan(editTextCatatan.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    public static List<TujuanMobilDinasModel> getList() {
        return list;
    }
}
