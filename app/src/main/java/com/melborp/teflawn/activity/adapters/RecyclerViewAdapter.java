package com.melborp.teflawn.activity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.melborp.teflawn.R;
import com.melborp.teflawn.activity.models.Booking;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Booking> mList = new ArrayList<>();

    public RecyclerViewAdapter(Context mContext, ArrayList<Booking> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(mContext)
                .asBitmap()
                .load(mList.get(position).getImageUri())
                .into(holder.photoUpload);


        holder.name.setText(mList.get(position).getName());
        holder.service.setText(mList.get(position).getServiceName());
        holder.number.setText(mList.get(position).getNumber());
        holder.location.setText(mList.get(position).getLocation());
        holder.date.setText(mList.get(position).getDate());
        holder.time.setText(mList.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView photoUpload;
        TextView name, service, number, location, date, time;
        Button approve;
        RelativeLayout parentLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photoUpload = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.txtName);
            service = itemView.findViewById(R.id.txtService);
            number = itemView.findViewById(R.id.txtNumber);
            location = itemView.findViewById(R.id.txtLocation);
            date = itemView.findViewById(R.id.txtDate);
            time = itemView.findViewById(R.id.txtTime);

            //Button
            approve = itemView.findViewById(R.id.btnApprove);

            //parent root
            parentLayout = itemView.findViewById(R.id.bookRoot);

        }
    }
}
