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
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_for_genset.ChecklistForGensetActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_for_hydrant.ChecklistForHydrantActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_pompa_air_bersih.NewChecklistPompaAirBersihActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_pompa_pond.ChecklistPompaPondActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.external_work_order.ExternalWorkOrderActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.hasil_test_food_catering.HasilTestFoodCateringActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.identifikasi.IdentifikasiActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.komplain_atau_usulan.KomplainAtauUsulanActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.kontrol_harian.KontrolHarianActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.laporan_perbaikan.LaporanPerbaikanActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.order_catering.OrderCateringActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.pembelian_snack.PembelianSnackActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.perbaikan.PerbaikanActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_catering_reguler.PermintaanCateringRegulerActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas.PermintaanMobilDinasActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_pribadi.PermintaanMobilPribadiActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.sidak_catering.SidakCateringActivity;
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

    @Override
    public long getItemId(int position) {
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
                    //Permohonan Perbaikan
                    if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_1484d4415442b0cd9cf7995e786e6142")) {
                        intent = new Intent(v.getContext(), PerbaikanActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Permintaan Mobil Pribadi
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_4a1f600ad300b633f2f0c4a0b7b4acc6")) {
                        intent = new Intent(v.getContext(), PermintaanMobilPribadiActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Control Harian
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_628426fdf153e5637a5c3970696d2ad2")) {
                        intent = new Intent(v.getContext(), KontrolHarianActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Permintaan Mobil Dinas
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_e5302aac81de91ac1d48b2cf8bf438f8")) {
                        intent = new Intent(v.getContext(), PermintaanMobilDinasActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Komplain / Usulan
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_ebaebfa4bdce66436c0b7d342cdcbe0c")) {
                        intent = new Intent(v.getContext(), KomplainAtauUsulanActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Form Inspection Checklist for Genset
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_347f0e1d0950dd8fd36f23d90b293ea9")) {
                        intent = new Intent(v.getContext(), NewChecklistPompaAirBersihActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Laporan Perbaikan
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_ce2d8c52f4e18e8a5a8df940c611d1d2")) {
                        intent = new Intent(v.getContext(), LaporanPerbaikanActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //External Work Order
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_120469065bef687d484e715365332000")) {
                        intent = new Intent(v.getContext(), ExternalWorkOrderActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //FORM SIDAK CATERING
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_2641049dccfd7f3d0d232a3a420e2948")) {
                        intent = new Intent(v.getContext(), SidakCateringActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Syarat-Syarat Legalitas Catering Yang Disurvey
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_9101a9689d1e9b937047799b09a68b8f")) {
                        intent = new Intent(v.getContext(), SyaratLegalitasCateringActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Pembelian Snack
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_e3afa323d691d218559593b2dd1d5935")) {
                        intent = new Intent(v.getContext(), PembelianSnackActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Hasil Test Food Catering
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_f060d9b26e6dbe48b4bb84fd94b64bcb")) {
                        intent = new Intent(v.getContext(), HasilTestFoodCateringActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Identifikasi
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_fca21f3d1ef1ff4a32978f579e584954")) {
                        intent = new Intent(v.getContext(), IdentifikasiActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Form Check List Pompa POND A,B,C & D
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_46dcc2031b229755961affc8c2095314")) {
                        intent = new Intent(v.getContext(), ChecklistPompaPondActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Inspection Checklist for Hydrant
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_50b7e945745a5d053e64f3a1026a032a")) {
                        intent = new Intent(v.getContext(), ChecklistForHydrantActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
//                    if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_e5302aac81de91ac1d48b2cf8bf438f8")) {
//                        intent = new Intent(v.getContext(), PermintaanMobilDinasActivity.class);
//                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
//                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_e3afa323d691d218559593b2dd1d5935")) {
//                        intent = new Intent(v.getContext(), PembelianSnackActivity.class);
//                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
//                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_4a1f600ad300b633f2f0c4a0b7b4acc6")) {
//                        intent = new Intent(v.getContext(), PermintaanMobilPribadiActivity.class);
//                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
//                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_628426fdf153e5637a5c3970696d2ad2")) {
//                        intent = new Intent(v.getContext(), KontrolHarianActivity.class);
//                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
//                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_ebaebfa4bdce66436c0b7d342cdcbe0c")) {
//                        intent = new Intent(v.getContext(), KomplainAtauUsulanActivity.class);
//                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
//                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_fca21f3d1ef1ff4a32978f579e584954")) {
//                        intent = new Intent(v.getContext(), IdentifikasiActivity.class);
//                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
//                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_fca21f3d1ef1ff4a32978f579e584954")) {
//                        intent = new Intent(v.getContext(), IdentifikasiActivity.class);
//                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
//                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_120469065bef687d484e715365332000")) {
//                        intent = new Intent(v.getContext(), ExternalWorkOrderActivity.class);
//                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
//                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_2641049dccfd7f3d0d232a3a420e2948")) {
//                        intent = new Intent(v.getContext(), SidakCateringActivity.class);
//                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
//                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_9101a9689d1e9b937047799b09a68b8f")) {
//                        intent = new Intent(v.getContext(), SyaratLegalitasCateringActivity.class);
//                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
//                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_f060d9b26e6dbe48b4bb84fd94b64bcb")) {
//                        intent = new Intent(v.getContext(), HasilTestFoodCateringActivity.class);
//                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
//                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_347f0e1d0950dd8fd36f23d90b293ea9")) {
//                        intent = new Intent(v.getContext(), ChecklistForGensetActivity.class);
//                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
//                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_ce2d8c52f4e18e8a5a8df940c611d1d2")) {
//                        intent = new Intent(v.getContext(), LaporanPerbaikanActivity.class);
//                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
//                    } else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_1484d4415442b0cd9cf7995e786e6142")) {
//                        intent = new Intent(v.getContext(), PerbaikanActivity.class);
//                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
//                    }
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
