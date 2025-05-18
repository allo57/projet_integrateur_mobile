package com.example.zootopia_mobile.magasin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zootopia_mobile.R;
import com.example.zootopia_mobile.SQLiteManager;
import com.example.zootopia_mobile.ZooLocation;
import com.example.zootopia_mobile.activite.Activite;
import com.example.zootopia_mobile.animaux.AffichageAnimaux;
import com.example.zootopia_mobile.inscription;
import com.example.zootopia_mobile.menuNavigation;

import java.util.List;

public class ListePanier extends AppCompatActivity implements View.OnClickListener{

    private ImageButton boutonActivite;
    private ImageButton boutonMap;
    private ImageButton boutonAnimaux;
    private ImageButton boutonPanier;
    private ImageButton boutonReservation;
    private Button paiement;
    private TextView panierVideTextView;
    public static long idTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_liste_panier);
        panierVideTextView = findViewById(R.id.panier_vide);
        SQLiteManager dbHelper = new SQLiteManager(this);
        ImageButton buttonNav = findViewById(R.id.imageButtonFermerNav);
        buttonNav.setOnClickListener(v -> {
            Intent intent = new Intent(ListePanier.this, menuNavigation.class);
            startActivity(intent);
        });

        //Différents buttons
        boutonActivite = findViewById(R.id.btnActivite);
        boutonAnimaux =findViewById(R.id.btnPaw);
        boutonPanier = findViewById(R.id.btnPanier);
        boutonMap = findViewById(R.id.btnMap);
        boutonReservation = findViewById(R.id.btnReservation);

        boutonActivite.setOnClickListener(this);
        boutonMap.setOnClickListener(this);
        boutonReservation.setOnClickListener(this);
        boutonPanier.setOnClickListener(this);
        boutonAnimaux.setOnClickListener(this);

        int currentUserId = inscription.userId;
        idTransaction = dbHelper.getTransactionUtilisateur(currentUserId);
        Log.d("ListePanier", "ID Transaction : " + idTransaction);
        List<BilletPanier> billetsPanier = dbHelper.getBilletsPourTransaction(idTransaction, currentUserId);
        Log.d("ListePanier", "Billets dans le panier : " + billetsPanier.size());
        if (billetsPanier.isEmpty()) {
            panierVideTextView.setVisibility(View.VISIBLE);
            if (currentUserId != -1) {
                panierVideTextView.setText("Votre panier est vide");
            } else {
                panierVideTextView.setText("Connectez-vous ou inscrivez-vous pour ajouter au panier");
            }
        } else {
            panierVideTextView.setVisibility(View.GONE);
        }

        PanierAdapter adapter = new PanierAdapter(billetsPanier, dbHelper, idTransaction);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        paiement = findViewById(R.id.paiement);
        paiement.setOnClickListener(v -> {
            for (BilletPanier billetPanier : billetsPanier) {
                int quantite = billetPanier.getQuantite();
                int idBillet = billetPanier.getBillet().getId_billet();
                dbHelper.ajouterTransaction(idTransaction, idBillet, quantite, currentUserId);
            }
            Intent intent = new Intent(ListePanier.this, Confirmation.class);
            startActivity(intent);
        });
    }
    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v.getId() == R.id.btnPanier) {
            intent = new Intent(ListePanier.this, ListePanier.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnActivite) {
            // Intent pour ouvrir la page Activité
            intent = new Intent(ListePanier.this, Activite.class);
        } else if (v.getId() == R.id.btnMap) {
            // Intent pour ouvrir la carte
            intent = new Intent(ListePanier.this, ZooLocation.class);
        } else if (v.getId() == R.id.btnPaw) {
            // Intent pour afficher les animaux
            intent = new Intent(ListePanier.this, AffichageAnimaux.class);
        } else if (v.getId() == R.id.btnReservation) {
            // intent = new Intent(Activite.this, Reservation.class);
            // startActivity(intent);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}