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
    private static List<HasilTestFoodCateringModel.DetailTestFood> list;

    public HasilTestFoodCateringAdapter(List<HasilTestFoodCateringModel.DetailTestFood> list) {
        HasilTestFoodCateringAdapter.list = list;
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
       return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hasil_test_food_catering, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HasilTestFoodCateringAdapter.ViewHolder holder, int position) {
        holder.textViewUrutan.setText("Catering " + (position + 1));
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
                        case R.id.rb_1_2:
                            list.get(getAdapterPosition()).setRasa(3);
                            break;
                        case R.id.rb_1_3:
                            list.get(getAdapterPosition()).setRasa(2);
                            break;
                        case R.id.rb_1_4:
                            list.get(getAdapterPosition()).setRasa(1);
                            break;
                    }

                }
            });

            radioGroupAroma.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.rb_2_1:
                            list.get(getAdapterPosition()).setAroma(4);
                            break;
                        case R.id.rb_2_2:
                            list.get(getAdapterPosition()).setAroma(3);
                            break;
                        case R.id.rb_2_3:
                            list.get(getAdapterPosition()).setAroma(2);
                            break;
                        case R.id.rb_2_4:
                            list.get(getAdapterPosition()).setAroma(1);
                            break;
                    }

                }
            });

            radioGroupKebersihan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.rb_3_1:
                            list.get(getAdapterPosition()).setKebersihan(4);
                            break;
                        case R.id.rb_3_2:
                            list.get(getAdapterPosition()).setKebersihan(3);
                            break;
                        case R.id.rb_3_3:
                            list.get(getAdapterPosition()).setKebersihan(2);
                            break;
                        case R.id.rb_3_4:
                            list.get(getAdapterPosition()).setKebersihan(1);
                            break;
                    }

                }
            });

            radioGroupKualitas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.rb_4_1:
                            list.get(getAdapterPosition()).setKualitas(4);
                            break;
                        case R.id.rb_4_2:
                            list.get(getAdapterPosition()).setKualitas(3);
                            break;
                        case R.id.rb_4_3:
                            list.get(getAdapterPosition()).setKualitas(2);
                            break;
                        case R.id.rb_4_4:
                            list.get(getAdapterPosition()).setKualitas(1);
                            break;
                    }

                }
            });
        }
    }

    public static List<HasilTestFoodCateringModel.DetailTestFood> getList() {
        return list;
    }
}
