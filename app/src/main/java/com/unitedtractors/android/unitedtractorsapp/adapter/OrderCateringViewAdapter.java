package com.unitedtractors.android.unitedtractorsapp.adapter;

import android.app.DatePickerDialog;
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

public class OrderCateringViewAdapter extends RecyclerView.Adapter<OrderCateringViewAdapter.ViewHolder>{
    private List<OrderCateringModel.DetailOrder> list;

    public OrderCateringViewAdapter(List<OrderCateringModel.DetailOrder> list) {
        this.list = list;
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
    public OrderCateringViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderCateringViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_catering_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderCateringViewAdapter.ViewHolder holder, int position) {
        holder.textViewUrutan.setText("Order " + (position + 1));
        holder.textViewTanggal.setText(list.get(position).getTglView());
        holder.textViewJumlahPesanan.setText(list.get(position).getJml() + " Pesanan");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageViewExpand;
        private final LinearLayout linearLayoutForm;
        private final TextView textViewUrutan, textViewTanggal, textViewJumlahPesanan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewExpand = itemView.findViewById(R.id.imageViewExpand);
            linearLayoutForm = itemView.findViewById(R.id.linearLayoutForm);
            textViewUrutan = itemView.findViewById(R.id.textViewUrutan);
            textViewTanggal = itemView.findViewById(R.id.textViewTanggal);
            textViewJumlahPesanan = itemView.findViewById(R.id.textViewJumlahPesanan);

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
        }
    }
}
