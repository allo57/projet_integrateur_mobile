package com.example.zootopia_mobile.animaux;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zootopia_mobile.R;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {

    private List<Animal> animaux;

    public AnimalAdapter(List<Animal> animaux) {
        this.animaux = animaux;
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animal, parent, false);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        Animal animal = animaux.get(position);
        holder.nomAnimal.setText(animal.getNom());

        Glide.with(holder.imageAnimal.getContext())
                .load(animal.getImageUrl())
                .placeholder(R.drawable.lion)
                .into(holder.imageAnimal);
    }

    @Override
    public int getItemCount() {
        return animaux.size();
    }

    static class AnimalViewHolder extends RecyclerView.ViewHolder {
        ImageView imageAnimal;
        TextView nomAnimal;

        public AnimalViewHolder(@NonNull View itemView) {
            super(itemView);
            imageAnimal = itemView.findViewById(R.id.imageAnimal);
            nomAnimal = itemView.findViewById(R.id.nomAnimal);
        }
    }

    public void setAnimaux(List<Animal> newAnimaux) {
        this.animaux = newAnimaux;
        notifyDataSetChanged();
    }
}

