/***************************************************
 *
 * Fichier : Billets.java
 * Auteur : Samuel Cloutier
 * Fonctionnalité : Code de l'affichage des billets
 * Date : 8 mai 2025
 *
 ***************************************************/
package com.example.zootopia_mobile.billets;

import android.content.Intent;
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
import com.example.zootopia_mobile.billets.Billets;
import com.example.zootopia_mobile.billets.BilletAdapter;
import com.example.zootopia_mobile.billets.Billet;
import com.example.zootopia_mobile.billets.BilletResponse;
import com.example.zootopia_mobile.activite.Activite;
import com.example.zootopia_mobile.animaux.AffichageAnimaux;
import com.example.zootopia_mobile.api.ApiService;
import com.example.zootopia_mobile.api.RetrofitInstance;
import com.example.zootopia_mobile.magasin.ListePanier;
import com.example.zootopia_mobile.menuNavigation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Billets extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private BilletAdapter adapter;
    private ImageButton boutonActivite;
    private ImageButton boutonMap;
    private ImageButton boutonAnimaux;
    private ImageButton boutonPanier;
    private ImageButton boutonReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_billets);

        ImageButton buttonNav = findViewById(R.id.imageButtonFermerNav);
        buttonNav.setOnClickListener(v -> {
            Intent intent = new Intent(Billets.this, menuNavigation.class);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BilletAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

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

        ApiService apiService = RetrofitInstance.getApi();
        Call<BilletResponse> call = apiService.getBillets();

        call.enqueue(new Callback<BilletResponse>() {
            @Override
            public void onResponse(Call<BilletResponse> call, Response<BilletResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Billet> liste = response.body().getBillets();
                    adapter = new BilletAdapter(liste);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("API", "Code d'erreur HTTP : " + response.code());
                    if (response.errorBody() != null) {
                        try {
                            Log.e("API", "Erreur body : " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.e("API", "Erreur body null");
                    }
                }
            }
            @Override
            public void onFailure(Call<BilletResponse> call, Throwable t) {
                Log.e("API", "Erreur : " + t.getMessage());
            }
        });
    }


    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v.getId() == R.id.btnPanier) {
            intent = new Intent(Billets.this, ListePanier.class);
            // startActivity(intent);
        } else if (v.getId() == R.id.btnActivite) {
            // Intent pour ouvrir la page Activité
            intent = new Intent(Billets.this, Activite.class);
        } else if (v.getId() == R.id.btnMap) {
            // Intent pour ouvrir la carte
            intent = new Intent(Billets.this, ZooLocation.class);
        } else if (v.getId() == R.id.btnPaw) {
            // Intent pour afficher les animaux
            intent = new Intent(Billets.this, AffichageAnimaux.class);
        } else if (v.getId() == R.id.btnReservation) {
            // intent = new Intent(Activite.this, Reservation.class);
            // startActivity(intent);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}