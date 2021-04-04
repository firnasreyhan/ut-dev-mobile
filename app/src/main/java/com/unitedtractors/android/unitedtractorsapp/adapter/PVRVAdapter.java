package com.unitedtractors.android.unitedtractorsapp.adapter;

import android.app.DatePickerDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.model.ExternalWorkOrderModel;
import com.unitedtractors.android.unitedtractorsapp.model.PVRVModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PVRVAdapter extends RecyclerView.Adapter<PVRVAdapter.ViewHolder> {
    private static List<PVRVModel.DetailPVRV> list;
    private static boolean isEditable;

    public PVRVAdapter(List<PVRVModel.DetailPVRV> list, boolean isEditable) {
        PVRVAdapter.list = list;
        PVRVAdapter.isEditable = isEditable;
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_p_v_r_v__form_input, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewUrutan.setText("Pembayaran " + (position + 1));
        if (!isEditable) {
            holder.editTextAccount.setText(list.get(position).getAcc());
            holder.editTextDescription.setText(list.get(position).getDesc());
            holder.editTextAllocation.setText(list.get(position).getAlloc());
            holder.editTextBussArea.setText(list.get(position).getbArea());
            holder.editTextCostCenter.setText(list.get(position).getCostCenter());
            holder.editTextAmount.setText(list.get(position).getAmount());
            holder.editTextKeterangan.setText(list.get(position).getKet().isEmpty() ? "-" : list.get(position).getKet());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout linearLayoutForm;
        private final TextView textViewUrutan;
        private final ImageView imageViewExpand;
        private final EditText editTextAccount, editTextDescription, editTextAllocation, editTextBussArea, editTextCostCenter, editTextAmount, editTextKeterangan;
        private final Calendar calendar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            calendar = Calendar.getInstance();
            linearLayoutForm = itemView.findViewById(R.id.linearLayoutForm);
            imageViewExpand = itemView.findViewById(R.id.imageViewExpand);
            textViewUrutan = itemView.findViewById(R.id.textViewUrutan);
            editTextAccount = itemView.findViewById(R.id.editTextAccount);
            editTextDescription = itemView.findViewById(R.id.editTextDescription);
            editTextAllocation = itemView.findViewById(R.id.editTextAllocation);
            editTextBussArea = itemView.findViewById(R.id.editTextBussArea);
            editTextCostCenter = itemView.findViewById(R.id.editTextCostCenter);
            editTextAmount = itemView.findViewById(R.id.editTextAmount);
            editTextKeterangan = itemView.findViewById(R.id.editTextKeterangan);

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

            editTextAccount.setEnabled(isEditable);
            editTextDescription.setEnabled(isEditable);
            editTextAllocation.setEnabled(isEditable);
            editTextBussArea.setEnabled(isEditable);
            editTextCostCenter.setEnabled(isEditable);
            editTextAmount.setEnabled(isEditable);
            editTextKeterangan.setEnabled(isEditable);

            editTextAccount.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setAcc(editTextAccount.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextDescription.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setDesc(editTextDescription.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextAllocation.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setAlloc(editTextAllocation.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextBussArea.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setbArea(editTextBussArea.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextCostCenter.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setCostCenter(editTextCostCenter.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextAmount.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setAmount(editTextAmount.getText().toString());
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
                    list.get(getAdapterPosition()).setKet(editTextKeterangan.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    public static List<PVRVModel.DetailPVRV> getList() {
        return list;
    }
}
