package com.unitedtractors.android.unitedtractorsapp.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
        private final LinearLayout linearLayoutExisting;
        private final Spinner spinnerFasilitasExisting, spinnerFasilitasBaru;
        private final EditText editTextNoExtension;
        private final RadioGroup radioGroup;
        private final CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8;
        private boolean cek1, cek2, cek3, cek4, cek5, cek6, cek7, cek8;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayoutExisting = itemView.findViewById(R.id.linearLayoutExisting);
            spinnerFasilitasExisting = itemView.findViewById(R.id.spinnerFasilitasExisting);
            spinnerFasilitasBaru = itemView.findViewById(R.id.spinnerFasilitasBaru);
            editTextNoExtension = itemView.findViewById(R.id.editTextNoExtension);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            checkBox1 = itemView.findViewById(R.id.checkBox1);
            checkBox2 = itemView.findViewById(R.id.checkBox2);
            checkBox3 = itemView.findViewById(R.id.checkBox3);
            checkBox4 = itemView.findViewById(R.id.checkBox4);
            checkBox5 = itemView.findViewById(R.id.checkBox5);
            checkBox6 = itemView.findViewById(R.id.checkBox6);
            checkBox7 = itemView.findViewById(R.id.checkBox7);
            checkBox8 = itemView.findViewById(R.id.checkBox8);

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
                            linearLayoutExisting.setVisibility(View.VISIBLE);
                            spinnerFasilitasExisting.setSelection(0);
                            list.get(getAdapterPosition()).setAksesExisting("I");
                            break;
                        case R.id.radioButtonAksesBaru:
                            linearLayoutExisting.setVisibility(View.GONE);
                            list.get(getAdapterPosition()).setAksesExisting("-");
                            break;
                    }
                }
            });

//            String[] ct = new String[] {"Principle", "UTHI", "Cabang/ Site", "Partner", "Customer", "Vendor", "Subcont", "Hotel"};
//            ArrayAdapter<String> ctAdapter = new ArrayAdapter<>(itemView.getContext(), R.layout.item_spinner, ct);

            String[] fasilitas = new String[] {"SLI, SLJJ, HP, LOKAL, INTERN, CABANG", "SLJJ, HP, LOKAL, INTERN, CABANG", "HP, LOKAL, INTERN, CABANG", "LOKAL, INTERN, CABANG", "INTERN CABANG"};
            ArrayAdapter<String> fasilitasAdapter = new ArrayAdapter<>(itemView.getContext(), R.layout.item_spinner, fasilitas);

            spinnerFasilitasExisting.setAdapter(fasilitasAdapter);
            spinnerFasilitasBaru.setAdapter(fasilitasAdapter);

            spinnerFasilitasExisting.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    list.get(getAdapterPosition()).setAksesExisting(s);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            spinnerFasilitasBaru.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    list.get(getAdapterPosition()).setAksesBaru(s);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    cek1 = isChecked;
                    list.get(getAdapterPosition()).setContactTo(contactTo(cek1, cek2, cek3, cek4, cek5, cek6, cek7, cek8));
                }
            });

            checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    cek2 = isChecked;
                    list.get(getAdapterPosition()).setContactTo(contactTo(cek1, cek2, cek3, cek4, cek5, cek6, cek7, cek8));
                }
            });

            checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    cek3 = isChecked;
                    list.get(getAdapterPosition()).setContactTo(contactTo(cek1, cek2, cek3, cek4, cek5, cek6, cek7, cek8));
                }
            });

            checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    cek4 = isChecked;
                    list.get(getAdapterPosition()).setContactTo(contactTo(cek1, cek2, cek3, cek4, cek5, cek6, cek7, cek8));
                }
            });

            checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    cek5 = isChecked;
                    list.get(getAdapterPosition()).setContactTo(contactTo(cek1, cek2, cek3, cek4, cek5, cek6, cek7, cek8));
                }
            });

            checkBox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    cek6 = isChecked;
                    list.get(getAdapterPosition()).setContactTo(contactTo(cek1, cek2, cek3, cek4, cek5, cek6, cek7, cek8));
                }
            });

            checkBox7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    cek7 = isChecked;
                    list.get(getAdapterPosition()).setContactTo(contactTo(cek1, cek2, cek3, cek4, cek5, cek6, cek7, cek8));
                }
            });

            checkBox8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    cek8 = isChecked;
                    list.get(getAdapterPosition()).setContactTo(contactTo(cek1, cek2, cek3, cek4, cek5, cek6, cek7, cek8));
                }
            });
        }
    }

    private static ExtensionDanAksesModel.DetExtension.ContactTo contactTo(boolean cek1, boolean cek2, boolean cek3, boolean cek4, boolean cek5, boolean cek6, boolean cek7, boolean cek8) {
        ExtensionDanAksesModel.DetExtension.ContactTo contactTo = new ExtensionDanAksesModel.DetExtension.ContactTo(
                cek1,
                cek2,
                cek3,
                cek4,
                cek5,
                cek6,
                cek7,
                cek8
        );
        return contactTo;
    }

    public static List<ExtensionDanAksesModel.DetExtension> getList() {
        return list;
    }
}
