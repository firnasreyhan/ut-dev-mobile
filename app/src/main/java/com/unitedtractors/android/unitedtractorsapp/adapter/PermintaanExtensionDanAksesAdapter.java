package com.unitedtractors.android.unitedtractorsapp.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.model.ExtensionDanAksesModel;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanMobilModel;

import java.util.List;

public class PermintaanExtensionDanAksesAdapter extends RecyclerView.Adapter<PermintaanExtensionDanAksesAdapter.ViewHolder> {

    private static List<ExtensionDanAksesModel.DetExtension> list;
    private static boolean isEditable;

    public PermintaanExtensionDanAksesAdapter(List<ExtensionDanAksesModel.DetExtension> list) {
        PermintaanExtensionDanAksesAdapter.list = list;
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
    public PermintaanExtensionDanAksesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_permintaan_extension_dan_akses, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PermintaanExtensionDanAksesAdapter.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final Spinner spinnerKontakKe, spinnerFasilitas;
        private final EditText editTextNoExtension;
        private final RadioGroup radioGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            spinnerKontakKe = itemView.findViewById(R.id.spinnerKontakKe);
            spinnerFasilitas = itemView.findViewById(R.id.spinnerFasilitas);
            editTextNoExtension = itemView.findViewById(R.id.editTextNoExtension);
            radioGroup = itemView.findViewById(R.id.radioGroup);

            editTextNoExtension.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setNoExtension(editTextNoExtension.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radioButtonAksesExisting:
                            list.get(getAdapterPosition()).setJenPermintaan("1");
                            break;
                        case R.id.radioButtonAksesBaru:
                            list.get(getAdapterPosition()).setJenPermintaan("2");
                            break;
                    }
                }
            });

            String[] ct = new String[] {"Principle", "UTHI", "Cabang/ Site", "Partner", "Customer", "Vendor", "Subcont", "Hotel"};
            ArrayAdapter<String> ctAdapter = new ArrayAdapter<>(itemView.getContext(), R.layout.item_spinner, ct);

            String[] fasilitas = new String[] {"SLI, SLJJ, HP, LOKAL, INTERN, CABANG", "SLJJ, HP, LOKAL, INTERN, CABANG", "HP, LOKAL, INTERN, CABANG", "LOKAL, INTERN, CABANG", "INTERN CABANG"};
            ArrayAdapter<String> fasilitasAdapter = new ArrayAdapter<>(itemView.getContext(), R.layout.item_spinner, fasilitas);

            spinnerKontakKe.setAdapter(ctAdapter);
            spinnerFasilitas.setAdapter(fasilitasAdapter);

            spinnerKontakKe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    list.get(getAdapterPosition()).setCt(String.valueOf(position + 1));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            spinnerFasilitas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String s = "";
                    if (position == 0) {
                        s = "I";
                    } else if (position == 1) {
                        s = "II";
                    } else if (position == 2) {
                        s = "III";
                    } else if (position == 3) {
                        s = "IV";
                    } else if (position == 4) {
                        s = "V";
                    }
                    list.get(getAdapterPosition()).setFasilitas(s);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    public static List<ExtensionDanAksesModel.DetExtension> getList() {
        return list;
    }
}
