package com.unitedtractors.android.unitedtractorsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.model.TaskModel;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<TaskModel> list;

    public TaskAdapter(List<TaskModel> list) {
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewUrutanTask.setText(String.valueOf(position + 1));
        holder.textViewPersentaseTask.setText(list.get(position).getPersentase() + "%");
        holder.linearProgressIndicatorTask.setProgress(Integer.parseInt(list.get(position).getPersentase()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewUrutanTask, textViewPersentaseTask;
        private LinearProgressIndicator linearProgressIndicatorTask;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewUrutanTask = itemView.findViewById(R.id.textViewUrutanTask);
            textViewPersentaseTask = itemView.findViewById(R.id.textViewPersentaseTask);
            linearProgressIndicatorTask = itemView.findViewById(R.id.linearProgressIndicatorTask);
        }
    }
}
