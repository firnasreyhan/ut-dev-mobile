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
import com.unitedtractors.android.unitedtractorsapp.model.IdentifikasiModel;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanAssetModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class IdentifikasiAdapter extends RecyclerView.Adapter<IdentifikasiAdapter.ViewHolder> {
    private static List<IdentifikasiModel.DetailIdentifikasi> list;
    private static boolean isEnable;

    public IdentifikasiAdapter(List<IdentifikasiModel.DetailIdentifikasi> list, boolean isEnable) {
        IdentifikasiAdapter.list = list;
        IdentifikasiAdapter.isEnable = isEnable;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_identifikasi_form_input, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewUrutan.setText("Temuan Lapangan " + (position + 1));
        holder.editTextTemuanLapangan.setText(list.get(position).getTemuanLapangan());
        holder.editTextTanggal.setText(list.get(position).getTanggalView());
        holder.editTextKategoriTemuan.setText(list.get(position).getKatergoriTemuan());
        holder.editTextLokasi.setText(list.get(position).getLokasi());
        holder.editTextUser.setText(list.get(position).getUser());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout linearLayoutForm;
        private final TextView textViewUrutan;
        private final ImageView imageViewExpand;
        private final EditText editTextTemuanLapangan, editTextTanggal, editTextKategoriTemuan, editTextLokasi, editTextUser;
        private final Calendar calendar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            calendar = Calendar.getInstance();
            linearLayoutForm = itemView.findViewById(R.id.linearLayoutForm);
            imageViewExpand = itemView.findViewById(R.id.imageViewExpand);
            textViewUrutan = itemView.findViewById(R.id.textViewUrutan);
            editTextTemuanLapangan = itemView.findViewById(R.id.editTextTemuanLapangan);
            editTextTanggal = itemView.findViewById(R.id.editTextTanggal);
            editTextKategoriTemuan = itemView.findViewById(R.id.editTextKategoriTemuan);
            editTextLokasi = itemView.findViewById(R.id.editTextLokasi);
            editTextUser = itemView.findViewById(R.id.editTextUser);

            editTextTemuanLapangan.setEnabled(isEnable);
            editTextTanggal.setEnabled(isEnable);
            editTextKategoriTemuan.setEnabled(isEnable);
            editTextLokasi.setEnabled(isEnable);
            editTextUser.setEnabled(isEnable);

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

            editTextKategoriTemuan.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setTemuanLapangan(editTextTemuanLapangan.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextKategoriTemuan.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setKatergoriTemuan(editTextKategoriTemuan.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextLokasi.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setLokasi(editTextLokasi.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextUser.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setUser(editTextUser.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextTanggal.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setTanggalView(simpleDateFormatView.format(calendar.getTime()));
                    list.get(getAdapterPosition()).setTanggal(simpleDateFormatServer.format(calendar.getTime()));
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    public static List<IdentifikasiModel.DetailIdentifikasi> getList() {
        return list;
    }
}
