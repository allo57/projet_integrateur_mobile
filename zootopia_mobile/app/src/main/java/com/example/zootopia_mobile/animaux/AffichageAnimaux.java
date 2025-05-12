package com.example.zootopia_mobile.animaux;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zootopia_mobile.R;
import com.example.zootopia_mobile.ZooLocation;
import com.example.zootopia_mobile.activite.Activite;
import com.example.zootopia_mobile.api.ApiClient;
import com.example.zootopia_mobile.api.ApiService;
import com.example.zootopia_mobile.menuNavigation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AffichageAnimaux extends AppCompatActivity implements View.OnClickListener{

    private ImageButton boutonActivite;
    private ImageButton boutonMap;
    private ImageButton boutonAnimaux;
    private ImageButton boutonPanier;
    private ImageButton boutonReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_animaux);

        //header
        ImageButton buttonNav = findViewById(R.id.imageButtonFermerNav);
        buttonNav.setOnClickListener(v -> {
            Intent intent = new Intent(AffichageAnimaux.this, menuNavigation.class);
            startActivity(intent);
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerViewAnimaux);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        AnimalAdapter adapter = new AnimalAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(adapter);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.getAnimaux().enqueue(new Callback<ReponseAnimaux>() {
            @Override
            public void onResponse(Call<ReponseAnimaux> call, Response<ReponseAnimaux> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setAnimaux(response.body().getData());
                } else {
                    Toast.makeText(AffichageAnimaux.this, "Erreur de réponse", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ReponseAnimaux> call, Throwable t) {
                Toast.makeText(AffichageAnimaux.this, "Erreur API : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //Différents buttons
        boutonActivite = findViewById(R.id.btnActivite);
        boutonAnimaux =findViewById(R.id.btnPaw);
        boutonPanier = findViewById(R.id.btnPanier);
        boutonMap = findViewById(R.id.btnMap);
        boutonReservation = findViewById(R.id.btnReservation);

        boutonActivite.setOnClickListener((View.OnClickListener) this);
        boutonMap.setOnClickListener((View.OnClickListener) this);
        boutonReservation.setOnClickListener((View.OnClickListener) this);
        boutonPanier.setOnClickListener((View.OnClickListener) this);
        boutonAnimaux.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v.getId() == R.id.btnPanier) {
            // intent = new Intent(Activite.this, Panier.class);
            // startActivity(intent);
        } else if (v.getId() == R.id.btnActivite) {
            // Intent pour ouvrir la page Activité
            intent = new Intent(AffichageAnimaux.this, Activite.class);
        } else if (v.getId() == R.id.btnMap) {
            // Intent pour ouvrir la carte
            intent = new Intent(AffichageAnimaux.this, ZooLocation.class);
        } else if (v.getId() == R.id.btnPaw) {
            // Intent pour afficher les animaux
            intent = new Intent(AffichageAnimaux.this, AffichageAnimaux.class);
        } else if (v.getId() == R.id.btnReservation) {
            // intent = new Intent(Activite.this, Reservation.class);
            // startActivity(intent);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
