package com.example.zootopia_mobile.magasin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zootopia_mobile.R;
import com.example.zootopia_mobile.SQLiteManager;
import com.example.zootopia_mobile.ZooLocation;
import com.example.zootopia_mobile.activite.Activite;
import com.example.zootopia_mobile.activite.ActiviteAdapter;
import com.example.zootopia_mobile.animaux.AffichageAnimaux;
import com.example.zootopia_mobile.billets.Billet;
import com.example.zootopia_mobile.inscription;

import java.util.ArrayList;
import java.util.List;

public class ListePanier extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private ActiviteAdapter adapter;
    private ImageButton boutonActivite;
    private ImageButton boutonMap;
    private ImageButton boutonAnimaux;
    private ImageButton boutonPanier;
    private ImageButton boutonReservation;
    public static long idTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
        //idTransaction = prefs.getLong("id_transaction", 2);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_liste_panier);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

        SQLiteManager dbHelper = new SQLiteManager(this);
        idTransaction = prefs.getLong("id_transaction", 2);
        int currentUserId = inscription.userId;
        List<Billet> billets = dbHelper.getBilletsPourTransaction(idTransaction, currentUserId);
        Log.d("DEBUG_BILLETS", "Billets récupérés : " + billets.size());
        Log.d("DEBUG_ID", "idTransaction récupéré : " + idTransaction);
        Log.d("DEBUG_USER", "iduser récupéré : " + currentUserId);

        PanierAdapter adapter = new PanierAdapter(billets);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
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