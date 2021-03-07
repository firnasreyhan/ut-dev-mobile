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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ExternalWorkOrderAdapter extends RecyclerView.Adapter<ExternalWorkOrderAdapter.ViewHolder> {
    private static List<ExternalWorkOrderModel.DetailExternalWorkOrder> list;
    private static boolean isEnable;

    public ExternalWorkOrderAdapter(List<ExternalWorkOrderModel.DetailExternalWorkOrder> list, boolean isEnable) {
        ExternalWorkOrderAdapter.list = list;
        ExternalWorkOrderAdapter.isEnable = isEnable;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_external_work_order_form_input, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewUrutan.setText("Temuan Pekerjaan " + (position + 1));
        holder.editTextItemPekerjaan.setText(list.get(position).getItemPekerjaan());
        holder.editTextLokasiDiv.setText(list.get(position).getLokasiDiv());
        holder.editTextTanggalDiminta.setText(list.get(position).getTanggalDimintaView());
        holder.editTextTroubleTicket.setText(list.get(position).getTroubleTicket());
        holder.editTextKeterangan.setText(list.get(position).getKeterangan());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout linearLayoutForm;
        private final TextView textViewUrutan;
        private final ImageView imageViewExpand;
        private final EditText editTextItemPekerjaan, editTextLokasiDiv, editTextTanggalDiminta, editTextTroubleTicket, editTextKeterangan;
        private final Calendar calendar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            calendar = Calendar.getInstance();
            linearLayoutForm = itemView.findViewById(R.id.linearLayoutForm);
            imageViewExpand = itemView.findViewById(R.id.imageViewExpand);
            textViewUrutan = itemView.findViewById(R.id.textViewUrutan);
            editTextItemPekerjaan = itemView.findViewById(R.id.editTextItemPekerjaan);
            editTextLokasiDiv = itemView.findViewById(R.id.editTextLokasiDiv);
            editTextTanggalDiminta = itemView.findViewById(R.id.editTextTanggalDiminta);
            editTextTroubleTicket = itemView.findViewById(R.id.editTextTroubleTicket);
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

            editTextItemPekerjaan.setEnabled(isEnable);
            editTextLokasiDiv.setEnabled(isEnable);
            editTextTanggalDiminta.setEnabled(isEnable);
            editTextTroubleTicket.setEnabled(isEnable);
            editTextKeterangan.setEnabled(isEnable);

            SimpleDateFormat simpleDateFormatView = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
            SimpleDateFormat simpleDateFormatServer = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    // TODO Auto-generated method stub
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    editTextTanggalDiminta.setText(simpleDateFormatView.format(calendar.getTime()));
                }
            };

            editTextTanggalDiminta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DatePickerDialog(itemView.getContext(), date, calendar
                            .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            });

            editTextItemPekerjaan.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setItemPekerjaan(editTextItemPekerjaan.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextLokasiDiv.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setLokasiDiv(editTextLokasiDiv.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextTanggalDiminta.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setTanggalDimintaServer(simpleDateFormatServer.format(calendar.getTime()));
                    list.get(getAdapterPosition()).setTanggalDimintaView(simpleDateFormatView.format(calendar.getTime()));
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextTroubleTicket.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setTroubleTicket(editTextTroubleTicket.getText().toString());
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
        }
    }

    public static List<ExternalWorkOrderModel.DetailExternalWorkOrder> getList() {
        return list;
    }
}
