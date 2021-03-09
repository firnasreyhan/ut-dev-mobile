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
import com.unitedtractors.android.unitedtractorsapp.model.MonitoringLapanganModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MonitoringLapanganAdapter extends RecyclerView.Adapter<MonitoringLapanganAdapter.ViewHolder> {
    private static List<MonitoringLapanganModel> list;

    public MonitoringLapanganAdapter(List<MonitoringLapanganModel> list) {
        MonitoringLapanganAdapter.list = list;
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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_monitoring_lapangan_form_input, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayoutForm;
        private TextView textViewUrutan;
        private ImageView imageViewExpand;
        private EditText editTextTanggal, editTextJumlahPesanan, editTextActualBawa, editTextActualKupon;
        private MaterialButton materialButtonKurangJumlahPesanan, materialButtonTambahJumlahPesanan, materialButtonKurangActualBawa, materialButtonTambahActualBawa, materialButtonKurangActualKupon, materialButtonKTambahActualKupon;
        int jumlahPesanan, actualBawa, actualKupon;
        private Calendar calendar;
        private String tanggal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            calendar = Calendar.getInstance();
            linearLayoutForm = itemView.findViewById(R.id.linearLayoutForm);
            imageViewExpand = itemView.findViewById(R.id.imageViewExpand);
            textViewUrutan = itemView.findViewById(R.id.textViewUrutan);
            editTextTanggal = itemView.findViewById(R.id.editTextTanggal);
            editTextJumlahPesanan = itemView.findViewById(R.id.editTextJumlahPesanan);
            editTextActualBawa = itemView.findViewById(R.id.editTextActualBawa);
            editTextActualKupon = itemView.findViewById(R.id.editTextActualKupon);
            materialButtonKurangJumlahPesanan = itemView.findViewById(R.id.materialButtonKurangJumlahPesanan);
            materialButtonTambahJumlahPesanan = itemView.findViewById(R.id.materialButtonTambahJumlahPesanan);
            materialButtonKurangActualBawa = itemView.findViewById(R.id.materialButtonKurangActualBawa);
            materialButtonTambahActualBawa = itemView.findViewById(R.id.materialButtonTambahActualBawa);
            materialButtonKurangActualKupon = itemView.findViewById(R.id.materialButtonKurangActualKupon);
            materialButtonKTambahActualKupon = itemView.findViewById(R.id.materialButtonKTambahActualKupon);

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

            jumlahPesanan = Integer.parseInt(editTextJumlahPesanan.getText().toString());
            actualBawa = Integer.parseInt(editTextActualBawa.getText().toString());
            actualKupon = Integer.parseInt(editTextActualKupon.getText().toString());

            SimpleDateFormat simpleDateFormatView = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
            SimpleDateFormat simpleDateFormatServer = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());


            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    // TODO Auto-generated method stub
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    tanggal = simpleDateFormatServer.format(calendar.getTime());
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

            materialButtonTambahActualBawa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actualBawa++;
                    editTextActualBawa.setText(String.valueOf(actualBawa));
                }
            });

            materialButtonKurangActualBawa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (actualBawa > 0) {
                        actualBawa--;
                        editTextActualBawa.setText(String.valueOf(actualBawa));
                    }
                }
            });

            materialButtonKTambahActualKupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actualKupon++;
                    editTextActualKupon.setText(String.valueOf(actualKupon));
                }
            });

            materialButtonKurangActualKupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (actualKupon > 0) {
                        actualKupon--;
                        editTextActualKupon.setText(String.valueOf(actualKupon));
                    }
                }
            });

            editTextTanggal.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setTangalServer(tanggal);
                    list.get(getAdapterPosition()).setTanggalView(editTextTanggal.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

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

            editTextActualBawa.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setActualBawa(editTextActualBawa.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editTextActualKupon.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setActualKupon(editTextActualKupon.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    public static List<MonitoringLapanganModel> getList() {
        return list;
    }
}
