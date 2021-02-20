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
import com.unitedtractors.android.unitedtractorsapp.view.model.KomplainAtauUsulanModel;
import com.unitedtractors.android.unitedtractorsapp.view.model.TujuanMobilDinasModel;

import java.util.List;

public class KomplainAtauUsulanAdapter extends RecyclerView.Adapter<KomplainAtauUsulanAdapter.ViewHolder> {

    private static List<KomplainAtauUsulanModel> list;
    private static boolean isEditable;

    public KomplainAtauUsulanAdapter(List<KomplainAtauUsulanModel> list, boolean isEditable) {
        KomplainAtauUsulanAdapter.list = list;
        KomplainAtauUsulanAdapter.isEditable = isEditable;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public KomplainAtauUsulanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new KomplainAtauUsulanAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_komplain_atau_usulan, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull KomplainAtauUsulanAdapter.ViewHolder holder, int position) {
        holder.textViewUrutan.setText("Komplain atau Usulan " + (position + 1));
        holder.editTextNama.setText(list.get(position).getNama());
        holder.editTextDivisi.setText(list.get(position).getDivisi());
        holder.editTextKomplainAtauUsulan.setText(list.get(position).getKomplainAtauUsulan());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewExpand;
        private final LinearLayout linearLayoutForm;
        private final TextView textViewUrutan;
        private final EditText editTextNama, editTextDivisi, editTextKomplainAtauUsulan;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewExpand = itemView.findViewById(R.id.imageViewExpand);
            linearLayoutForm = itemView.findViewById(R.id.linearLayoutForm);
            textViewUrutan = itemView.findViewById(R.id.textViewUrutan);
            editTextNama = itemView.findViewById(R.id.editTextNama);
            editTextDivisi = itemView.findViewById(R.id.editTextDivisi);
            editTextKomplainAtauUsulan = itemView.findViewById(R.id.editTextKomplainAtauUsulan);

            editTextNama.setEnabled(isEditable);
            editTextDivisi.setEnabled(isEditable);
            editTextKomplainAtauUsulan.setEnabled(isEditable);

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

            editTextNama.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setNama(editTextNama.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextDivisi.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setDivisi(editTextDivisi.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextKomplainAtauUsulan.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setKomplainAtauUsulan(editTextKomplainAtauUsulan.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    public static List<KomplainAtauUsulanModel> getList() {
        return list;
    }
}
