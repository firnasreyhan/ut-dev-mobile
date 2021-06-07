package com.unitedtractors.android.unitedtractorsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaiselrahman.filepicker.model.MediaFile;
import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.model.MediaModel;

import java.util.List;

public class NewDokumenPVRVAdapter extends RecyclerView.Adapter<NewDokumenPVRVAdapter.ViewHolder> {
    private static List<MediaModel> mediaFiles;

    public NewDokumenPVRVAdapter(List<MediaModel> mediaFiles) {
        NewDokumenPVRVAdapter.mediaFiles = mediaFiles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokumen_p_v_r_v, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewNamaFile.setText(mediaFiles.get(position).getNama());
    }

    @Override
    public int getItemCount() {
        return mediaFiles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewNamaFile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNamaFile = itemView.findViewById(R.id.textViewNamaFile);
        }
    }

    public static List<MediaModel> getMediaFiles() {
        return mediaFiles;
    }
}
