package com.unitedtractors.android.unitedtractorsapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.api.response.FormResponse;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_alat_komunikasi.ChecklistAlatKomunikasiActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_for_genset.ChecklistForGensetActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.external_work_order.ExternalWorkOrderActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.hasil_test_food_catering.HasilTestFoodCateringActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.identifikasi.IdentifikasiActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.komplain_atau_usulan.KomplainAtauUsulanActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.kontrol_harian.KontrolHarianActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.laporan_perbaikan.LaporanPerbaikanActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.material_used_slip.MaterialUsedSlipActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.order_catering.OrderCateringActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_catering_reguler.PermintaanCateringRegulerActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas.PermintaanMobilDinasActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_pribadi.PermintaanMobilPribadiActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.sidak_catering.SidakCateringActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.survery_kepuasan_pelanggan.SurveryKepuasanPelangganActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.syarat_legalitas_catering.SyaratLegalitasCateringActivity;

import java.util.List;

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.ViewHolder> {
    private static List<FormResponse.FormModel> list;

    public FormAdapter(List<FormResponse.FormModel> list) {
        FormAdapter.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_form, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewNamaForm.setText(list.get(position).getNamaForm());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewNamaForm;
        private final ImageView imageViewForm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNamaForm = itemView.findViewById(R.id.textViewNamaForm);
            imageViewForm = itemView.findViewById(R.id.imageViewForm);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = null;
                    if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_e5302aac81de91ac1d48b2cf8bf438f8")) {
                        intent = new Intent(v.getContext(), PermintaanMobilDinasActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_e3afa323d691d218559593b2dd1d5935")) {
                        intent = new Intent(v.getContext(), OrderCateringActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_4a1f600ad300b633f2f0c4a0b7b4acc6")) {
                        intent = new Intent(v.getContext(), PermintaanMobilPribadiActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_628426fdf153e5637a5c3970696d2ad2")) {
                        intent = new Intent(v.getContext(), KontrolHarianActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_ebaebfa4bdce66436c0b7d342cdcbe0c")) {
                        intent = new Intent(v.getContext(), KomplainAtauUsulanActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_fca21f3d1ef1ff4a32978f579e584954")) {
                        intent = new Intent(v.getContext(), IdentifikasiActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_fca21f3d1ef1ff4a32978f579e584954")) {
                        intent = new Intent(v.getContext(), IdentifikasiActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_120469065bef687d484e715365332000")) {
                        intent = new Intent(v.getContext(), ExternalWorkOrderActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_2641049dccfd7f3d0d232a3a420e2948")) {
                        intent = new Intent(v.getContext(), SidakCateringActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_9101a9689d1e9b937047799b09a68b8f")) {
                        intent = new Intent(v.getContext(), SyaratLegalitasCateringActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_f060d9b26e6dbe48b4bb84fd94b64bcb")) {
                        intent = new Intent(v.getContext(), HasilTestFoodCateringActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_347f0e1d0950dd8fd36f23d90b293ea9")) {
                        intent = new Intent(v.getContext(), ChecklistForGensetActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_ce2d8c52f4e18e8a5a8df940c611d1d2")) {
                        intent = new Intent(v.getContext(), LaporanPerbaikanActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
