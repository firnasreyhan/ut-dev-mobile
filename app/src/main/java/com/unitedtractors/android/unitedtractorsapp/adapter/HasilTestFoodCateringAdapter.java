package com.unitedtractors.android.unitedtractorsapp.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.model.HasilTestFoodCateringModel;

import java.util.List;

public class HasilTestFoodCateringAdapter extends RecyclerView.Adapter<HasilTestFoodCateringAdapter.ViewHolder> {

    private static List<HasilTestFoodCateringModel> list;
    private static boolean isEditable;

    public HasilTestFoodCateringAdapter(List<HasilTestFoodCateringModel> list, boolean isEditable) {
        HasilTestFoodCateringAdapter.list = list;
        HasilTestFoodCateringAdapter.isEditable = isEditable;
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
    public HasilTestFoodCateringAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new HasilTestFoodCateringAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hasil_test_food_catering, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HasilTestFoodCateringAdapter.ViewHolder holder, int position) {
        holder.textViewUrutan.setText("Catering " + (position + 1));
        holder.editTextNamaCatering.setText(list.get(position).getNamaCatering());
        holder.radioGroupRasa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                group.check(list.get(position).getRasa());
            }
        });
        holder.radioGroupKebersihan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                group.check(list.get(position).getKebersihan());
            }
        });
        holder.radioGroupAroma.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                group.check(list.get(position).getAroma());
            }
        });
        holder.radioGroupKualitas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                group.check(list.get(position).getKualitas());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewExpand;
        private final LinearLayout linearLayoutForm;
        private final TextView textViewUrutan;
        private final EditText editTextNamaCatering;
        private final RadioGroup radioGroupRasa, radioGroupAroma, radioGroupKebersihan, radioGroupKualitas;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewExpand = itemView.findViewById(R.id.imageViewExpand);
            linearLayoutForm = itemView.findViewById(R.id.linearLayoutForm);
            textViewUrutan = itemView.findViewById(R.id.textViewUrutan);
            editTextNamaCatering = itemView.findViewById(R.id.editTextNamaCatering);
            radioGroupRasa = itemView.findViewById(R.id.radioGroupRasa);
            radioGroupAroma = itemView.findViewById(R.id.radioGroupAroma);
            radioGroupKebersihan = itemView.findViewById(R.id.radioGroupKebersihan);
            radioGroupKualitas = itemView.findViewById(R.id.radioGroupKualitas);

            editTextNamaCatering.setEnabled(isEditable);
            radioGroupRasa.setEnabled(isEditable);
            radioGroupAroma.setEnabled(isEditable);
            radioGroupKebersihan.setEnabled(isEditable);
            radioGroupKualitas.setEnabled(isEditable);

            imageViewExpand.setOnClickListener(new View.OnClickListener() {
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

            editTextNamaCatering.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setNamaCatering(editTextNamaCatering.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            radioGroupRasa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.rb_1_1:
                            list.get(getAdapterPosition()).setRasa(4);
                            break;
                    }

                }
            });

            radioGroupAroma.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    list.get(getAdapterPosition()).setAroma(group.getCheckedRadioButtonId());
                }
            });

            radioGroupKebersihan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    list.get(getAdapterPosition()).setKebersihan(group.getCheckedRadioButtonId());
                }
            });

            radioGroupKualitas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    list.get(getAdapterPosition()).setKualitas(group.getCheckedRadioButtonId());
                }
            });


        }
    }

    public static List<HasilTestFoodCateringModel> getList() {
        return list;
    }
}
