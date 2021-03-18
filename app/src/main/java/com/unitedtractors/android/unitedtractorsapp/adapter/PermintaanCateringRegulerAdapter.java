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
import com.unitedtractors.android.unitedtractorsapp.model.CateringRegulerModel;
import com.unitedtractors.android.unitedtractorsapp.model.ExternalWorkOrderModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PermintaanCateringRegulerAdapter extends RecyclerView.Adapter<PermintaanCateringRegulerAdapter.ViewHolder> {
    private static List<CateringRegulerModel.DetailCatering> list;
    private static boolean isEnable;

    public PermintaanCateringRegulerAdapter(List<CateringRegulerModel.DetailCatering> list, boolean isEnable) {
        PermintaanCateringRegulerAdapter.list = list;
        PermintaanCateringRegulerAdapter.isEnable = isEnable;
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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_permintaan_catering_reguler_form_input, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewUrutan.setText("Permintaan " + (position + 1));
        if (isEnable) {
            holder.editTextJumlahOrang.setText(list.get(position).getJumlahOrang());
        } else {
            holder.editTextJumlahOrang.setText(list.get(position).getJumlahOrang() + " Orang");
        }
        holder.editTextTanggal.setText(list.get(position).getTanggalView());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout linearLayoutForm;
        private final TextView textViewUrutan;
        private final ImageView imageViewExpand;
        private final EditText editTextTanggal, editTextJumlahOrang;
        private final Calendar calendar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            calendar = Calendar.getInstance();
            linearLayoutForm = itemView.findViewById(R.id.linearLayoutForm);
            imageViewExpand = itemView.findViewById(R.id.imageViewExpand);
            textViewUrutan = itemView.findViewById(R.id.textViewUrutan);
            editTextTanggal = itemView.findViewById(R.id.editTextTanggal);
            editTextJumlahOrang = itemView.findViewById(R.id.editTextJumlahOrang);

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

            editTextTanggal.setEnabled(isEnable);
            editTextJumlahOrang.setEnabled(isEnable);

            SimpleDateFormat simpleDateFormatView = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
            SimpleDateFormat simpleDateFormatServer = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    // TODO Auto-generated method stub
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    editTextTanggal.setText(simpleDateFormatView.format(calendar.getTime()));
                }
            };

            editTextTanggal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DatePickerDialog(itemView.getContext(), date, calendar
                            .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            });

            editTextTanggal.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setTanggal(simpleDateFormatServer.format(calendar.getTime()));
                    list.get(getAdapterPosition()).setTanggalView(simpleDateFormatView.format(calendar.getTime()));
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            if (isEnable) {
                editTextJumlahOrang.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        list.get(getAdapterPosition()).setJumlahOrang(editTextJumlahOrang.getText().toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        }
    }

    public static List<CateringRegulerModel.DetailCatering> getList() {
        return list;
    }
}
