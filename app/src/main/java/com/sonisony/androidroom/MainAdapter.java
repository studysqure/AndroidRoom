package com.sonisony.androidroom;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
//Initialize variable
    private List<MainData>dataList;
    private Activity context;
    private RoomDB database;

    //create constructor
    public MainAdapter(Activity context,List<MainData>dataList){
        this.context= context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_main,parent,false);
        return new MainAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         MainData data = dataList.get(position);
         //Initialize database
        database = RoomDB.getInstance(context);
        //set text on text view
        holder.textView.setText(data.getText());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainData d = dataList.get(holder.getAdapterPosition());
                //Get id
                int sID = d.getID();
                //Get text
                String sText = d.getText();
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                //Initialize height
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                //set Layout
                dialog.getWindow().setLayout(width,height);
                //show dialog
                dialog.show();
                EditText editText = dialog.findViewById(R.id.edit_update);
                Button btnUpdate = dialog.findViewById(R.id.update_btn);
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Dissmiss dialog
                        dialog.dismiss();
                        //Get Update taxt from edit text
                        String uText = editText.getText().toString().trim();
                        //update text in database
                        database.mainDao().update(sID,uText);
//                        notify when data is updated
                        dataList.clear();
                        dataList.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();
                    }
                });
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainData d = dataList.get(holder.getAdapterPosition());
                //Delete text from database
                database.mainDao().delete(d);
                //notify when data is deleted
                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,dataList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView btnEdit,btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txt_tv);
            btnDelete = itemView.findViewById(R.id.delete_btn);
            btnEdit = itemView.findViewById(R.id.edit_btn);
        }
    }
}
