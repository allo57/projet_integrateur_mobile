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
    private static List<Billet> billets;
    private SQLiteManager dbHelper;
    private long idTransaction;

    public PanierAdapter(List<Billet> billets, SQLiteManager dbHelper, long idTransaction) {
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
        Billet billet = billets.get(position);
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
        public void bind(Billet billet, int position) {
            nomProduit.setText("Nom du produit: " + billet.getNom());
            description.setText("Description: " + billet.getDescription());
            prix.setText("Prix: " + billet.getPrix() + "$");

            quantiteValue = 1;
            quantite.setText(String.valueOf(quantiteValue));

            btnIncrease.setOnClickListener(v -> {
                quantiteValue++;
                quantite.setText(String.valueOf(quantiteValue));
            });

            btnDecrease.setOnClickListener(v -> {
                if (quantiteValue > 1) {
                    quantiteValue--;
                    quantite.setText(String.valueOf(quantiteValue));
                }
            });

            btnRemove.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    Billet billetASupprimer = billets.get(pos);
                    dbHelper.supprimerBilletTransaction(idTransaction, billetASupprimer.getId_billet());
                    removeItem(pos);
                }
            });
        }
    }
}
