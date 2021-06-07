package com.unitedtractors.android.unitedtractorsapp.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Environment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.adapter.DokumenPVRVAdapter;

import java.io.File;
import java.util.ArrayList;

public class FileSelector {
    private Activity context;
    private String[] extensions;
    private ArrayList<SelectedFile> itemsData = new ArrayList<>();
    public static final String MP4 = ".mp4", MP3 = ".mp3", JPG = ".jpg", JPEG = ".jpeg", PNG = ".png", DOC = ".doc", DOCX = ".docx", XLS = ".xls", XLSX = ".xlsx", PDF = ".pdf";

    public FileSelector(Activity context, String[] extensions) {
        this.context = context;
        this.extensions = extensions;
    }

    public interface OnSelectListener {
        void onSelect(String nama, String path, String size);

    }

    public void selectFile(OnSelectListener listener) {
        String sdCard = Environment.getExternalStorageDirectory().getAbsolutePath();
        listOfFile(new File(sdCard));
        dialogFileList(listener);
    }

    private void listOfFile(File dir) {
        File[] list = dir.listFiles();

        for (File file : list) {
            if (file.isDirectory()) {
                if (!new File(file, ".nomedia").exists() && !file.getName().startsWith(".")) {
                    Log.w("LOG", "IS DIR " + file);
                    listOfFile(file);
                }
            } else {
                String path = file.getAbsolutePath();
                for (String ext : extensions) {
                    if (path.endsWith(ext)) {
                        SelectedFile selectedFile = new SelectedFile();

                        selectedFile.path = path;
                        selectedFile.size = String.valueOf(file.getTotalSpace());
                        String[] split = path.split("/");
                        selectedFile.name = split[split.length - 1];
                        itemsData.add(selectedFile);
                        Log.i("LOG", "ADD " + selectedFile.path + " " + selectedFile.name);
                    }
                }
            }
        }
        Log.d("LOG", itemsData.size() + " DONE");
    }

    private void dialogFileList(OnSelectListener listener) {
        LinearLayout lytMain = new LinearLayout(context);
        lytMain.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        lytMain.setOrientation(LinearLayout.VERTICAL);
        int p = convertToPixels(12);
        lytMain.setPadding(p, p, p, p);
        lytMain.setGravity(Gravity.CENTER);

//        TextView textView = new TextView(context);
//        textView.setLayoutParams(new LinearLayout.LayoutParams(screenWidth(), ViewGroup.LayoutParams.WRAP_CONTENT));
//        textView.setGravity(Gravity.CENTER);
//        textView.setText("~JDM File Selecor~");

        RecyclerView recyclerView = new RecyclerView(context);

//        lytMain.addView(textView);
        lytMain.addView(recyclerView);

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(lytMain);
        dialog.setCancelable(true);
        dialog.show();

        AdapterFile adapter = new AdapterFile(dialog, listener, itemsData);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(context);
        LayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(LayoutManager);
    }

    private int convertToPixels(int dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private int screenWidth() {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    private class SelectedFile {
        public String path = "";
        public String name = "";
        public String size = "";
    }

    private class AdapterFile extends RecyclerView.Adapter<AdapterFile.ViewHolder> {

        private ArrayList<SelectedFile> itemsData;
        private OnSelectListener listener;
        private Dialog dialog;

        public AdapterFile(Dialog dialog, OnSelectListener listener, ArrayList<SelectedFile> itemsData) {
            this.itemsData = itemsData;
            this.listener = listener;
            this.dialog = dialog;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//            LinearLayout linearLayout = new LinearLayout(context);
//            linearLayout.setOrientation(LinearLayout.VERTICAL);
//            TextView txtName = new TextView(context);
//            TextView txtPath = new TextView(context);
//            txtPath.setTypeface(txtPath.getTypeface(), Typeface.ITALIC);
//            txtPath.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
//
//            linearLayout.addView(txtName);
//            linearLayout.addView(txtPath);
//
//            ViewHolder viewHolder = new ViewHolder(linearLayout);
//            return viewHolder;

            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokumen_p_v_r_v, parent, false));
        }

        // inner class to hold a reference to each item of RecyclerView
        public class ViewHolder extends RecyclerView.ViewHolder {

//            public LinearLayout linearLayout;
//            public TextView txtName;
//            public TextView txtPath;
            public final TextView textViewNamaFile;

            public ViewHolder(View itemLayoutView) {
                super(itemLayoutView);

//                linearLayout = (LinearLayout) itemLayoutView;
//                txtName = (TextView) linearLayout.getChildAt(0);
//                txtPath = (TextView) linearLayout.getChildAt(1);

                textViewNamaFile = itemLayoutView.findViewById(R.id.textViewNamaFile);

                itemLayoutView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        listener.onSelect(itemsData.get(getAdapterPosition()).name, itemsData.get(getAdapterPosition()).path, itemsData.get(getAdapterPosition()).size);
                    }
                });
            }
        }


        @Override
        public int getItemCount() {
            return itemsData.size();
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {
            final SelectedFile selectedFile = itemsData.get(position);

            viewHolder.textViewNamaFile.setText(selectedFile.name);
//            viewHolder.txtName.setText(selectedFile.name);
//            viewHolder.txtPath.setText(selectedFile.path);

//            viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                    listener.onSelect(selectedFile.path);
//                }
//            });
        }
    }
}
