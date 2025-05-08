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

    public void setAnimaux(List<Animal> animaux) {
        this.animaux = animaux;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recyler_animaux, parent, false);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        Animal animal = animaux.get(position);
        holder.nomTextView.setText(animal.getNom());

        // Charger l'image avec Glide (si image est un nom de fichier, ajouter l’URL de base)
        String url = "https://ton_serveur/images/" + animal.getImage(); // à adapter
        Glide.with(holder.imageView.getContext())
                .load(url)
                .placeholder(R.drawable.lion) // image par défaut si vide
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return animaux.size();
    }

    static class AnimalViewHolder extends RecyclerView.ViewHolder {
        TextView nomTextView;
        ImageView imageView;

        AnimalViewHolder(@NonNull View itemView) {
            super(itemView);
            nomTextView = itemView.findViewById(R.id.textViewNomAnimal);
            imageView = itemView.findViewById(R.id.imageViewAnimal);
        }
    }
}


