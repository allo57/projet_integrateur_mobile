package com.example.zootopia_mobile.activite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.zootopia_mobile.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ActiviteAdapter extends RecyclerView.Adapter<ActiviteAdapter.ActiviteViewHolder> {

    private List<ActiviteModel> activites;

    // Constructor
    public ActiviteAdapter(List<ActiviteModel> activites) {
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
        String dateOriginale = activite.getDate();
        LocalDate date = LocalDate.parse(dateOriginale);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.FRENCH);
        String dateFormatee = date.format(formatter);
        holder.date.setText(dateFormatee);
        String heureDebutFormattee = activite.getHeure_debut().substring(0, 5);
        String heureFinFormattee = activite.getHeure_fin().substring(0, 5);
        holder.heure.setText(heureDebutFormattee + " - " + heureFinFormattee);
        holder.titre.setText(activite.getTitre());
        holder.description.setText(activite.getDescription());
        holder.tag.setText(activite.getTag());
        holder.infrastructure.setText(activite.getInfrastructure());
    }

    @Override
    public int getItemCount() {
        return activites.size();
    }

    static class ActiviteViewHolder extends RecyclerView.ViewHolder {

        TextView date;
        TextView heure;
        TextView titre;
        TextView description;
        TextView tag;
        TextView infrastructure;

        public ActiviteViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            heure = itemView.findViewById(R.id.heure);
            titre = itemView.findViewById(R.id.titre_activite);
            description = itemView.findViewById(R.id.description_animal);
            tag = itemView.findViewById(R.id.tag);
            infrastructure = itemView.findViewById(R.id.infrastructure);
        }
    }
}

