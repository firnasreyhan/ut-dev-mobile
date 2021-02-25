package com.unitedtractors.android.unitedtractorsapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionResponse;
import com.unitedtractors.android.unitedtractorsapp.view.activity.approval.DetailApprovalPembelianSnackActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.approval.TransactionDetailActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ApprovalAdapter extends RecyclerView.Adapter<ApprovalAdapter.ViewHolder> {
    private static List<TransactionResponse.TransactionModel> list;
    private static boolean isPIC;

    public ApprovalAdapter(List<TransactionResponse.TransactionModel> list, boolean isPIC) {
        ApprovalAdapter.list = list;
        ApprovalAdapter.isPIC = isPIC;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_approval_list_pic, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewNamaForm.setText(list.get(position).getNamaForm());

        if (isPIC) {
            if (list.get(position).getStatTrans() == null) {
                holder.textViewStatusForm.setText("Menunggu Konfirmasi");
                holder.textViewStatusForm.setTextColor(holder.itemView.getResources().getColor(R.color.primary));
                holder.imageViewStatus.setImageResource(R.drawable.ic_process);
            } else if (list.get(position).getStatTrans().equalsIgnoreCase("1")) {
                holder.textViewStatusForm.setText("Approved");
                holder.textViewStatusForm.setTextColor(holder.itemView.getResources().getColor(R.color.approve));
                holder.imageViewStatus.setImageResource(R.drawable.ic_approve);
            } else if (list.get(position).getStatTrans().equalsIgnoreCase("0")) {
                holder.textViewStatusForm.setText("Rejected");
                holder.textViewStatusForm.setTextColor(holder.itemView.getResources().getColor(R.color.reject));
                holder.imageViewStatus.setImageResource(R.drawable.ic_reject);
            }
            holder.textViewApplicantForm.setText("Applicant: " + list.get(position).getNamaUsers());
        } else {
            if (list.get(position).getStatTrans().equalsIgnoreCase("0")) {
                holder.textViewStatusForm.setText("Menunggu Konfirmasi");
                holder.textViewStatusForm.setTextColor(holder.itemView.getResources().getColor(R.color.primary));
                holder.imageViewStatus.setImageResource(R.drawable.ic_process);
            } else if (list.get(position).getStatTrans().equalsIgnoreCase("1")) {
                holder.textViewStatusForm.setText("Sedang Diproses");
                holder.textViewStatusForm.setTextColor(holder.itemView.getResources().getColor(R.color.primary));
                holder.imageViewStatus.setImageResource(R.drawable.ic_process);
            } else if (list.get(position).getStatTrans().equalsIgnoreCase("2")) {
                holder.textViewStatusForm.setText("Approved");
                holder.textViewStatusForm.setTextColor(holder.itemView.getResources().getColor(R.color.approve));
                holder.imageViewStatus.setImageResource(R.drawable.ic_approve);
            } else if (list.get(position).getStatTrans().equalsIgnoreCase("3")) {
                holder.textViewStatusForm.setText("Rejected");
                holder.textViewStatusForm.setTextColor(holder.itemView.getResources().getColor(R.color.reject));
                holder.imageViewStatus.setImageResource(R.drawable.ic_reject);
            }
            holder.textViewApplicantForm.setVisibility(View.GONE);
        }

        String nmyFormat = "dd MMMM yyyy"; //In which you need put here
        SimpleDateFormat nsdf = new SimpleDateFormat(nmyFormat, new Locale("id", "ID"));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        try {
            Date date = format.parse(list.get(position).getTsTrans());
            holder.textViewTanggalForm.setText(nsdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
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
                    Intent intent = new Intent(itemView.getContext(), TransactionDetailActivity.class);
                    intent.putExtra("ID_TRANS", list.get(getAdapterPosition()).getIdTrans());
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
                    Intent intent = new Intent(itemView.getContext(), TransactionDetailActivity.class);
                    intent.putExtra("ID_TRANS", list.get(getAdapterPosition()).getIdTrans());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
