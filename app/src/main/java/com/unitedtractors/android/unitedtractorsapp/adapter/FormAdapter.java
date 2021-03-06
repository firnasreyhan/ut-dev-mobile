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
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_for_hydrant.ChecklistForHydrantActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_pompa_air_bersih.ChecklistPompaAirBersihActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_pompa_pond.ChecklistPompaPondActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_ruang_meeting.ChecklistRuangMeetingActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.deklarasi.DeklarasiActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.evaluasi_pekerjaan_vendor.EvaluasiPekerjaanVendorActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.external_work_order.ExternalWorkOrderActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.hasil_test_food_catering.HasilTestFoodCateringActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.identifikasi.IdentifikasiActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.internal_work_order.InternalWorkOrderActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.komplain_atau_usulan.KomplainAtauUsulanActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.kontrol_catering_harian.KontrolCateringHarianActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.laporan_perbaikan.LaporanPerbaikanActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.material_used_slip.MaterialUsedSlipActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.monitoring_lapangan.MonitoringLapanganActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.order_catering.OrderCateringActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.pembelian_snack.PembelianSnackActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.perbaikan.PerbaikanActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_catering_reguler.NewPermintaanCateringRegulerActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_extension_dan_akses.PermintaanExtensionDanAksesActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_layout_acara.PermintaanLayoutAcaraActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas.PermintaanMobilDinasActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_pribadi.PermintaanMobilPribadiActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_non_asset.PermintaanNonAssetActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permohonan_pv_rv.PermohonanPVRVActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.sidak_catering.SidakCateringActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.surat_jalan.SuratJalanActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.survery_kepuasan_pelanggan.DetailSurveyKepuasanPelangganActivity;
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
                        intent = new Intent(v.getContext(), KontrolCateringHarianActivity.class);
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
                        intent = new Intent(v.getContext(), ChecklistForGensetActivity.class);
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
                    //Form Inspection Check List Pompa Air Bersih
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_d5e8dc39bd0ab682ac54d305059c47ab")) {
                        intent = new Intent(v.getContext(), ChecklistPompaAirBersihActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Survey Kepuasan Pelanggan
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_91d115ecfb647d098a42074a12e36f53")) {
                        intent = new Intent(v.getContext(), DetailSurveyKepuasanPelangganActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Permintaan Catering Reguler
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_ad87a2b2fe39467ddf6639b36bf22579")) {
                        intent = new Intent(v.getContext(), NewPermintaanCateringRegulerActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Checklist Ruang Meeting
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_4316e329225c7df1b08b8fcb435dfd65")) {
                        intent = new Intent(v.getContext(), ChecklistRuangMeetingActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Inspection Checklist Alat Komunikasi
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_cfcceb6652a530555081d0b0883739a0")) {
                        intent = new Intent(v.getContext(), ChecklistAlatKomunikasiActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Internal Work Order
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_0c1e8b60c22ca5d4e433e0cecaafe6d6")) {
                        intent = new Intent(v.getContext(), InternalWorkOrderActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Material Used Slip
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_949aaa4c11a8a6cf1b5497ac3ffa6135")) {
                        intent = new Intent(v.getContext(), MaterialUsedSlipActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Order Catering
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_5b8a427ac935b9d2a907330fea0e2d9b")) {
                        intent = new Intent(v.getContext(), OrderCateringActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Evaluasi Pekerjaan Vendor
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_a7b3e6e04d0e2b7eb485d3f6c2ed459d")) {
                        intent = new Intent(v.getContext(), EvaluasiPekerjaanVendorActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Hasil Test Food Catering
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_f060d9b26e6dbe48b4bb84fd94b64bcb")) {
                        intent = new Intent(v.getContext(), HasilTestFoodCateringActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Komplain Usulan
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_ebaebfa4bdce66436c0b7d342cdcbe0c")) {
                        intent = new Intent(v.getContext(), KomplainAtauUsulanActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Surat Jalan
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_10cc1e7a907e144f5fb1d6a2d5e288e8")) {
                        intent = new Intent(v.getContext(), SuratJalanActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Deklarasi
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_0e328100091ecc17f48f72d220a9358a")) {
                        intent = new Intent(v.getContext(), DeklarasiActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Permintaan Non Asset
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_efbf21fa60f0b05edceb7908955354bd")) {
                        intent = new Intent(v.getContext(), PermintaanNonAssetActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Permintaan Extension dan Akses
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_e8ae59c0c9b3f7e674f794cc7276ec7b")) {
                        intent = new Intent(v.getContext(), PermintaanExtensionDanAksesActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Monitoring
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_bb0ac8481270d3bb20c332076a749c24")) {
                        intent = new Intent(v.getContext(), MonitoringLapanganActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //Permintaan Layout Acara
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_f88a488e782a3190c3b4604314066024")) {
                        intent = new Intent(v.getContext(), PermintaanLayoutAcaraActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    //PERMOHONAN PEMBUATAN PVRV
                    else if (list.get(getAdapterPosition()).getIdMapping().equals("MAPP_f396fb92a8cd60dc3edfabf349321882")) {
                        intent = new Intent(v.getContext(), PermohonanPVRVActivity.class);
                        intent.putExtra("ID_MAPPING", list.get(getAdapterPosition()).getIdMapping());
                    }
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
