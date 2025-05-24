/***************************************************
 *
 * Fichier : PanierAdapter.java
 * Auteur : Sarah-Maude Gagné
 * Fonctionnalité : adapter du panier pour le recyclerview
 * Date : 18 mai 2025
 *
 ***************************************************/
package com.example.zootopia_mobile.magasin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zootopia_mobile.R;
import com.example.zootopia_mobile.SQLiteManager;
import com.example.zootopia_mobile.billets.Billet;

import java.util.ArrayList;
import java.util.List;

public class PanierAdapter extends RecyclerView.Adapter<PanierAdapter.PanierViewHolder> {
    private static List<BilletPanier> billets;
    private SQLiteManager dbHelper;
    private long idTransaction;

    public PanierAdapter(List<BilletPanier> billets, SQLiteManager dbHelper, long idTransaction) {
        this.billets = billets != null ? billets : new ArrayList<>();
        this.dbHelper = dbHelper;
        this.idTransaction = idTransaction;
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
        BilletPanier billet = billets.get(position);
        holder.bind(billet, position);
    }

    public void removeItem(int position) {
        billets.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return billets.size();
    }

    class PanierViewHolder extends RecyclerView.ViewHolder {

        TextView nomProduit;
        TextView prix;
        TextView description;
        Button btnIncrease;
        Button btnDecrease;
        Button btnRemove;
        TextView quantite;
        int quantiteValue = 1;

        public PanierViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.description);
            prix = itemView.findViewById(R.id.prix);
            nomProduit = itemView.findViewById(R.id.nomProduit);
            btnRemove = itemView.findViewById(R.id.btn_remove);
            btnIncrease = itemView.findViewById(R.id.btn_increase);
            btnDecrease = itemView.findViewById(R.id.btn_decrease);
            quantite = itemView.findViewById(R.id.quantite);
        }
        public void bind(BilletPanier billetPanier, int position) {
            Billet billet = billetPanier.getBillet();
            nomProduit.setText("Nom du produit: " + billet.getNom());
            description.setText("Description: " + billet.getDescription());
            prix.setText("Prix: " + billet.getPrix() + "$");

            quantiteValue = billetPanier.getQuantite();
            quantite.setText(String.valueOf(quantiteValue));

            btnIncrease.setOnClickListener(v -> {
                quantiteValue++;
                quantite.setText(String.valueOf(quantiteValue));
                billetPanier.setQuantite(quantiteValue);
            });

            btnDecrease.setOnClickListener(v -> {
                if (quantiteValue > 1) {
                    quantiteValue--;
                    quantite.setText(String.valueOf(quantiteValue));
                    billetPanier.setQuantite(quantiteValue);
                }
            });

            btnRemove.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    dbHelper.supprimerBilletTransaction(idTransaction, billet.getId_billet());
                    removeItem(pos);
                }
            });
        }
    }
}
