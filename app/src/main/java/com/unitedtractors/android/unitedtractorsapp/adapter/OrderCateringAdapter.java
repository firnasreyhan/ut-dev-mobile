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
    private static List<OrderCateringModel> list;
    private static boolean isEditable;

    public OrderCateringAdapter(List<OrderCateringModel> list, boolean isEditable) {
        OrderCateringAdapter.list = list;
        OrderCateringAdapter.isEditable = isEditable;
    }

    @Override
    public int getItemViewType(int position) {
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
        holder.editTextDivisi.setText(list.get(position).getDivisi());
        holder.editTextTanggal.setText(list.get(position).getTanggal());
        holder.editTextJumlahPesanan.setText(list.get(position).getJumlahPesanan());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewExpand;
        private final LinearLayout linearLayoutForm;
        private final TextView textViewUrutan;
        private final EditText editTextTanggal, editTextDivisi, editTextJumlahPesanan;
        private final MaterialButton materialButtonKurangJumlahPesanan, materialButtonTambahJumlahPesanan;
        Calendar calendar;
        int jumlahPesanan = 0;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            calendar = Calendar.getInstance();

            imageViewExpand = itemView.findViewById(R.id.imageViewExpand);
            linearLayoutForm = itemView.findViewById(R.id.linearLayoutForm);
            textViewUrutan = itemView.findViewById(R.id.textViewUrutan);
            editTextTanggal = itemView.findViewById(R.id.editTextTanggal);
            editTextDivisi = itemView.findViewById(R.id.editTextDivisi);
            editTextJumlahPesanan = itemView.findViewById(R.id.editTextJumlahPesanan);
            materialButtonKurangJumlahPesanan = itemView.findViewById(R.id.materialButtonKurangJumlahPesanan);
            materialButtonTambahJumlahPesanan = itemView.findViewById(R.id.materialButtonTambahJumlahPesanan);

            editTextTanggal.setEnabled(isEditable);
            editTextDivisi.setEnabled(isEditable);
            editTextJumlahPesanan.setEnabled(isEditable);

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

            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    // TODO Auto-generated method stub
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    SimpleDateFormat simpleDateFormatView = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
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

            editTextJumlahPesanan.setText(jumlahPesanan +"");

            materialButtonTambahJumlahPesanan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jumlahPesanan++;
                    editTextJumlahPesanan.setText(String.valueOf(jumlahPesanan));
                }
            });

            materialButtonKurangJumlahPesanan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (jumlahPesanan > 0) {
                        jumlahPesanan--;
                        editTextJumlahPesanan.setText(String.valueOf(jumlahPesanan));
                    }
                }
            });

            editTextJumlahPesanan.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setJumlahPesanan(editTextJumlahPesanan.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    public static List<OrderCateringModel> getList() {
        return list;
    }
}
