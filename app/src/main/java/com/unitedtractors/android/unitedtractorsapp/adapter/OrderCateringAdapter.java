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

import com.google.android.material.button.MaterialButton;
import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.model.OrderCateringModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class OrderCateringAdapter extends RecyclerView.Adapter<OrderCateringAdapter.ViewHolder>{
    private static List<OrderCateringModel.DetailOrder> list;

    public OrderCateringAdapter(List<OrderCateringModel.DetailOrder> list) {
        OrderCateringAdapter.list = list;
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
    public OrderCateringAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderCateringAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_catering, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderCateringAdapter.ViewHolder holder, int position) {
        holder.textViewUrutan.setText("Order " + (position + 1));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageViewExpand;
        private final LinearLayout linearLayoutForm;
        private final TextView textViewUrutan;
        private final EditText editTextTanggal, editTextJumlahPesanan;
        private final MaterialButton materialButtonKurangJumlahPesanan, materialButtonTambahJumlahPesanan;
        private Calendar calendar;
        private int jumlahPesanan = 0;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            calendar = Calendar.getInstance();

            imageViewExpand = itemView.findViewById(R.id.imageViewExpand);
            linearLayoutForm = itemView.findViewById(R.id.linearLayoutForm);
            textViewUrutan = itemView.findViewById(R.id.textViewUrutan);
            editTextTanggal = itemView.findViewById(R.id.editTextTanggal);
            editTextJumlahPesanan = itemView.findViewById(R.id.editTextJumlahPesanan);
            materialButtonKurangJumlahPesanan = itemView.findViewById(R.id.materialButtonKurangJumlahPesanan);
            materialButtonTambahJumlahPesanan = itemView.findViewById(R.id.materialButtonTambahJumlahPesanan);

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
                    list.get(getAdapterPosition()).setTgl(simpleDateFormatServer.format(calendar.getTime()));
                    list.get(getAdapterPosition()).setTglView(simpleDateFormatView.format(calendar.getTime()));
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

            materialButtonTambahJumlahPesanan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jumlahPesanan++;
                    editTextJumlahPesanan.setText(String.valueOf(jumlahPesanan));
                    list.get(getAdapterPosition()).setJml(jumlahPesanan);
                }
            });

            materialButtonKurangJumlahPesanan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (jumlahPesanan > 0) {
                        jumlahPesanan--;
                        editTextJumlahPesanan.setText(String.valueOf(jumlahPesanan));
                        list.get(getAdapterPosition()).setJml(jumlahPesanan);
                    }
                }
            });
        }
    }

    public static List<OrderCateringModel.DetailOrder> getList() {
        return list;
    }
}
