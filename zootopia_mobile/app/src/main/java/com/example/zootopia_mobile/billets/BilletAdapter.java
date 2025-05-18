package com.example.zootopia_mobile.billets;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.zootopia_mobile.R;
import com.example.zootopia_mobile.SQLiteManager;

import java.util.ArrayList;
import java.util.List;

public class BilletAdapter extends RecyclerView.Adapter<BilletAdapter.BilletViewHolder> {

    private List<Billet> billets;

    // Constructor
    public BilletAdapter(List<Billet> billets) {
        this.billets = billets != null ? billets : new ArrayList<>();
    }

    @NonNull
    @Override
    public BilletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recycler_billet2, parent, false);
        return new BilletViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BilletViewHolder holder, int position) {
        Billet billet = billets.get(position);
        holder.nomProduit.setText("Nom du produit: " + billet.getNom());
        holder.description.setText("Description: " + billet.getDescription());
        holder.prix.setText("Prix: " + billet.getPrix() + "$");

        holder.btnAjout.setOnClickListener(v -> {
            Billet currentBillet = billets.get(position);

            int userId = com.example.zootopia_mobile.inscription.userId;

            SQLiteManager dbHelper = SQLiteManager.instanceOfDatabase(v.getContext());
            dbHelper.ajoutBillet(billet.getId_billet(), billet.getNom(), billet.getDescription(), billet.getPrix());

            boolean success = dbHelper.ajouterBilletAuPanier(userId, currentBillet.getId_billet(), 1);

            if (success) {
                Toast.makeText(v.getContext(), "Billet ajouté au panier", Toast.LENGTH_SHORT).show();
            } else {
                if (userId == -1) {
                    Toast.makeText(v.getContext(), "Veuillez vous connecter pour ajouter au panier", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v.getContext(), "Erreur lors de l'ajout au panier", Toast.LENGTH_SHORT).show();
                }
            }
            Log.d("Panier", "Ajout au panier : " + success);

        });
    }

    @Override
    public int getItemCount() {
        return billets.size();
    }

    static class BilletViewHolder extends RecyclerView.ViewHolder {

        TextView nomProduit;
        TextView prix;
        TextView description;
        Button btnAjout;

        public BilletViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.description);
            prix = itemView.findViewById(R.id.prix);
            nomProduit = itemView.findViewById(R.id.nomProduit);
            btnAjout = itemView.findViewById(R.id.btnAjout);
        }
    }
}

