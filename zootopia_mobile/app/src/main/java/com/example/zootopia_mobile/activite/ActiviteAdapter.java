package com.example.zootopia_mobile.activite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.zootopia_mobile.R;

import java.util.ArrayList;
import java.util.List;

public class ActiviteAdapter extends RecyclerView.Adapter<ActiviteAdapter.ActiviteViewHolder> {

    private List<ActiviteModel> activites;

    // Constructor
    public ActiviteAdapter(List<ActiviteModel> activites) {
        // Si la liste est null, utilise une liste vide
        this.activites = activites != null ? activites : new ArrayList<>();
    }

    @NonNull
    @Override
    public ActiviteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recycler_activite, parent, false);
        return new ActiviteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ActiviteViewHolder holder, int position) {
        ActiviteModel activite = activites.get(position);
        holder.titre.setText(activite.getTitre());
        holder.description.setText(activite.getDescription());
        holder.tag.setText(activite.getTag());
        holder.image.setImageResource(R.drawable.lion); // Image par défaut
    }

    @Override
    public int getItemCount() {
        return activites.size();
    }

    static class ActiviteViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView titre;
        TextView description;
        TextView tag;

        public ActiviteViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_activite);
            titre = itemView.findViewById(R.id.titre_activite);
            description = itemView.findViewById(R.id.description_activite);
            tag = itemView.findViewById(R.id.tag);
        }
    }
}

