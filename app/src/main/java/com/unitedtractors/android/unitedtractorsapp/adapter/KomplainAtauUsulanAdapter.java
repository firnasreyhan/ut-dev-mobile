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
import com.unitedtractors.android.unitedtractorsapp.model.KomplainAtauUsulanModel;

import java.util.List;

public class KomplainAtauUsulanAdapter extends RecyclerView.Adapter<KomplainAtauUsulanAdapter.ViewHolder> {

    private static List<KomplainAtauUsulanModel.DetailKomplain> list;

    public KomplainAtauUsulanAdapter(List<KomplainAtauUsulanModel.DetailKomplain> list) {
        KomplainAtauUsulanAdapter.list = list;
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
    public KomplainAtauUsulanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_komplain_atau_usulan, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull KomplainAtauUsulanAdapter.ViewHolder holder, int position) {
        holder.textViewUrutan.setText("Komplain atau Usulan " + (position + 1));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageViewExpand;
        private final LinearLayout linearLayoutForm;
        private final TextView textViewUrutan;
        private final EditText editTextKomplainAtauUsulan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewExpand = itemView.findViewById(R.id.imageViewExpand);
            linearLayoutForm = itemView.findViewById(R.id.linearLayoutForm);
            textViewUrutan = itemView.findViewById(R.id.textViewUrutan);
            editTextKomplainAtauUsulan = itemView.findViewById(R.id.editTextKomplainAtauUsulan);

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

            editTextKomplainAtauUsulan.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setKomplainUsul(editTextKomplainAtauUsulan.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    public static List<KomplainAtauUsulanModel.DetailKomplain> getList() {
        return list;
    }
}
