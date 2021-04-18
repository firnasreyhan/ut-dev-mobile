package com.unitedtractors.android.unitedtractorsapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.api.response.DebitNoteResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionResponse;
import com.unitedtractors.android.unitedtractorsapp.view.activity.approval.DebitNoteDetailActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.approval.TransactionDetailActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DebitNoteAdapter extends RecyclerView.Adapter<DebitNoteAdapter.ViewHolder> implements Filterable {
    private static List<DebitNoteResponse.DetailDebitNote> list;
    private static List<DebitNoteResponse.DetailDebitNote> listFiltered;

    public DebitNoteAdapter(List<DebitNoteResponse.DetailDebitNote> list) {
        DebitNoteAdapter.list = list;
        DebitNoteAdapter.listFiltered = list;
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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_approval_list_pic, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewNamaForm.setText("Debit Note");

        if (list.get(position).getIsApproveApp() == null) {
            holder.textViewStatusForm.setText("Menunggu Konfirmasi");
            holder.textViewStatusForm.setTextColor(holder.itemView.getResources().getColor(R.color.primary));
            holder.imageViewStatus.setImageResource(R.drawable.ic_process);
        } else if (list.get(position).getIsApproveApp().equalsIgnoreCase("1")) {
            holder.textViewStatusForm.setText("Disetujui");
            holder.textViewStatusForm.setTextColor(holder.itemView.getResources().getColor(R.color.approve));
            holder.imageViewStatus.setImageResource(R.drawable.ic_approve);
        } else if (list.get(position).getIsApproveApp().equalsIgnoreCase("0")) {
            holder.textViewStatusForm.setText("Ditolak");
            holder.textViewStatusForm.setTextColor(holder.itemView.getResources().getColor(R.color.reject));
            holder.imageViewStatus.setImageResource(R.drawable.ic_reject);
        }
        holder.textViewApplicantForm.setText("Pemohon: " + list.get(position).getEmailDebitNote());

        String nmyFormat = "dd MMMM yyyy"; //In which you need put here
        SimpleDateFormat nsdf = new SimpleDateFormat(nmyFormat, new Locale("id", "ID"));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        try {
            Date date = format.parse(list.get(position).getTsApp());
            holder.textViewTanggalForm.setText(nsdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String s = constraint.toString();
                if (s.isEmpty()) {
                    listFiltered = list;
                } else {
                    List<DebitNoteResponse.DetailDebitNote> filterList = new ArrayList<>();
                    for (DebitNoteResponse.DetailDebitNote model : list) {
                        if (model.getEmailDebitNote().toLowerCase().contains(s.toLowerCase())) {
                            filterList.add(model);
                        }
                    }
                    listFiltered = filterList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listFiltered = (List<DebitNoteResponse.DetailDebitNote>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewNamaForm;
        private final TextView textViewTanggalForm;
        private final TextView textViewStatusForm;
        private final TextView textViewApplicantForm;
        private final ImageView imageViewStatus;
        private final MaterialButton materialButtonDetailForm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNamaForm = itemView.findViewById(R.id.textViewNamaForm);
            textViewTanggalForm = itemView.findViewById(R.id.textViewTanggalForm);
            textViewStatusForm = itemView.findViewById(R.id.textViewStatusForm);
            textViewApplicantForm = itemView.findViewById(R.id.textViewApplicantForm);
            imageViewStatus = itemView.findViewById(R.id.imageViewStatus);
            materialButtonDetailForm = itemView.findViewById(R.id.materialButtonDetailForm);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_e3afa323d691d218559593b2dd1d5935")) {
//                        Intent intent = new Intent(itemView.getContext(), DetailApprovalPembelianSnackActivity.class);
//                        intent.putExtra("ID_TRANS", list.get(getAdapterPosition()).getIdTrans());
//                        itemView.getContext().startActivity(intent);
//                    }
                    Intent intent = new Intent(itemView.getContext(), DebitNoteDetailActivity.class);
                    intent.putExtra("ID_DEBITNOTE", list.get(getAdapterPosition()).getIdDebitNote());
                    intent.putExtra("ID_USERS", list.get(getAdapterPosition()).getEmailDebitNote());
                    itemView.getContext().startActivity(intent);
                }
            });

            materialButtonDetailForm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_e3afa323d691d218559593b2dd1d5935")) {
//                        Intent intent = new Intent(itemView.getContext(), DetailApprovalPembelianSnackActivity.class);
//                        intent.putExtra("ID_TRANS", list.get(getAdapterPosition()).getIdTrans());
//                        itemView.getContext().startActivity(intent);
//                    }
                    Intent intent = new Intent(itemView.getContext(), DebitNoteDetailActivity.class);
                    intent.putExtra("ID_DEBITNOTE", list.get(getAdapterPosition()).getIdDebitNote());
                    intent.putExtra("ID_USERS", list.get(getAdapterPosition()).getEmailDebitNote());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
