package com.example.zootopia_mobile.magasin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zootopia_mobile.R;
import com.example.zootopia_mobile.billets.Billet;

import java.util.ArrayList;
import java.util.List;

public class PanierAdapter extends RecyclerView.Adapter<PanierAdapter.PanierViewHolder> {
    private List<Billet> billets;

    public PanierAdapter(List<Billet> billets) {
        this.billets = billets != null ? billets : new ArrayList<>();
    }

    @NonNull
    @Override
    public PanierAdapter.PanierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recycler_panier, parent, false);
        return new PanierAdapter.PanierViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PanierViewHolder holder, int position) {
        Billet billet = billets.get(position);
        holder.nomProduit.setText("Nom du produit: " + billet.getNom());
        holder.description.setText("Description: " + billet.getDescription());
        holder.prix.setText("Prix: " + billet.getPrix() + "$");
    }

    @Override
    public int getItemCount() {
        return billets.size();
    }

    static class PanierViewHolder extends RecyclerView.ViewHolder {

        TextView nomProduit;
        TextView prix;
        TextView description;
        Button btnIncrease;
        Button btnDecrease;
        Button btnRemove;
        TextView quantite;
        private int quantiteValue = 1;

        public PanierViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.description);
            prix = itemView.findViewById(R.id.prix);
            nomProduit = itemView.findViewById(R.id.nomProduit);
            btnRemove = itemView.findViewById(R.id.btn_remove);
        }
    }
}
