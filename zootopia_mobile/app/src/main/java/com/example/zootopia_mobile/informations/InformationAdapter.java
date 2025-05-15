package com.example.zootopia_mobile.informations;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zootopia_mobile.R;
import com.example.zootopia_mobile.informations.Information;
import com.example.zootopia_mobile.informations.InformationAdapter;

import java.util.ArrayList;
import java.util.List;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.InformationViewHolder>{
    private List<Information> infos;

    // Constructor
    public InformationAdapter(List<Information> infos) {
        this.infos = infos != null ? infos : new ArrayList<>();
    }

    @NonNull
    @Override
    public InformationAdapter.InformationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recycler_informations, parent, false);
        return new InformationAdapter.InformationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InformationAdapter.InformationViewHolder holder, int position) {
        Information info = infos.get(position);
        holder.nomInfo.setText(info.getNom());
        holder.description.setText(info.getDescription());

        Glide.with(holder.itemView.getContext())
                .load("http://10.0.2.2:8000/img/" + info.getImage())
                .placeholder(R.drawable.animal_default)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return infos.size();
    }

    static class InformationViewHolder extends RecyclerView.ViewHolder {

        TextView nomInfo;
        TextView description;
        ImageView imageView;


        public InformationViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.description);
            nomInfo = itemView.findViewById(R.id.nomInfo);
            imageView = itemView.findViewById(R.id.imageViewInfo);
        }
    }
}
