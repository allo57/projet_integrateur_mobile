/***************************************************
 *
 * Fichier : ListeReservation
 * Auteur : Jacob Perreault
 * Fonctionnalité : Récupère les données du serveur web et les insère dans la base de données locale
 * Date : 9 mai 2025
 *
 ***************************************************/
package com.example.zootopia_mobile.reservation;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zootopia_mobile.NetworkConnection;
import com.example.zootopia_mobile.R;
import com.example.zootopia_mobile.SQLiteManager;
import com.example.zootopia_mobile.ZooLocation;
import com.example.zootopia_mobile.activite.Activite;
import com.example.zootopia_mobile.animaux.AffichageAnimaux;
import com.example.zootopia_mobile.api.ApiService;
import com.example.zootopia_mobile.api.RetrofitInstance;
import com.example.zootopia_mobile.informations.Informations;
import com.example.zootopia_mobile.magasin.ListeItem;
import com.example.zootopia_mobile.menuNavigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListeReservation extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    RecyclerReservation adapter;

    Context context = this;
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_liste_reservation);

        recyclerView = (RecyclerView) findViewById(R.id.liste_reservation);
        adapter = new RecyclerReservation(context, new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent data = getIntent();
        this.user_id = data.getIntExtra("id_utilisateur", 0);


        NetworkConnection network = new NetworkConnection();
        if (network.isConnected(context)) {
            ApiService apiService = RetrofitInstance.getApi();
            Call<ResponseReservation> call = apiService.getUserReservations(String.valueOf(user_id));

            call.enqueue(new Callback<ResponseReservation>() {
                @Override
                public void onResponse(Call<ResponseReservation> call, Response<ResponseReservation> response) {
                    SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(context);
                    if (response.isSuccessful() && response.body() != null) {
                        List<Reservation> liste = response.body().getUserReservations();
                        adapter = new RecyclerReservation(context, liste);
                        recyclerView.setAdapter(adapter);

                        if (liste.isEmpty()) {
                            TextView text = findViewById(R.id.vide);
                            text.setText("Veuillez ajouter une réservation");
                            ArrayList<Reservation> old_list = sqLiteManager.getReservations(user_id);

                            if (old_list != null) {
                                if (!old_list.isEmpty()) {
                                    for (int i = 0; i < old_list.size(); i++) {
                                        sqLiteManager.deleteReservation(old_list.get(i).get_id_reservation());
                                    }
                                }

                            }
                        }
                        else {

                            for (int i = 0; i < liste.size(); i++) {
                                Reservation reservation = sqLiteManager.getReservation(liste.get(i).get_id_reservation(), liste.get(i).get_id_utilisateur());
                                if (reservation == null) {
                                    sqLiteManager.ajoutReservation(liste.get(i));
                                }
                                else if (!reservation.equals(liste.get(i))) {
                                    sqLiteManager.updateReservations(liste.get(i));
                                }
                            }

                            ArrayList<Reservation> old_list = sqLiteManager.getReservations(user_id);
                            if (old_list != null) {
                                if (old_list.size() > liste.size()) {
                                    for (int i = liste.size(); i < old_list.size(); i++) {
                                        sqLiteManager.deleteReservation(old_list.get(i).get_id_reservation());
                                    }
                                }
                            }
                        }

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
                        // Vérifie si la base de données contient des réservations
                        ArrayList<Reservation> reservations = sqLiteManager.getReservations(user_id);
                        if (reservations != null) {
                            if (reservations.isEmpty()) {
                                TextView text = findViewById(R.id.vide);
                                text.setText("Veuillez ajouter une réservation");
                            }
                        }
                        else {
                            adapter = new RecyclerReservation(context, reservations);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseReservation> call, Throwable t) {
                    Log.e("API", "Erreur : " + t.getMessage());
                }
            });
        }
        else {
            SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(context);
            ArrayList<Reservation> reservations = sqLiteManager.getReservations(1);
            if (reservations.isEmpty()) {
                TextView text = findViewById(R.id.vide);
                text.setText("Veuillez ajouter une réservation");
            }
            else {
                adapter = new RecyclerReservation(context, reservations);
                recyclerView.setAdapter(adapter);
            }
        }



        ImageButton retour = findViewById(R.id.retour_liste_reservation);
        retour.setOnClickListener(this);

        AppCompatButton ajouter = findViewById(R.id.ajouter);
        ajouter.setOnClickListener(this);

        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.menu_animaux) {
                    Intent intent = new Intent(ListeReservation.this, AffichageAnimaux.class);
                    startActivity(intent);
                }
                else if (id == R.id.menu_activites) {
                    Intent intent = new Intent(ListeReservation.this, Activite.class);
                    startActivity(intent);
                }
                else if (id == R.id.menu_panier) {
                    Intent intent = new Intent(ListeReservation.this, ListeItem.class);
                    startActivity(intent);
                }
                else if (id == R.id.menu_carte) {
                    Intent intent = new Intent(ListeReservation.this, ZooLocation.class);
                    startActivity(intent);
                }
                else if (id == R.id.menu_info) {
                    Intent intent = new Intent(ListeReservation.this, Informations.class);
                    startActivity(intent);
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ajouter) {
            Intent data = getIntent();
            int id_utilisateur = 1;//data.getIntExtra("id_utilisateur", 0);
            Intent intent = new Intent(ListeReservation.this, AjouterReservation.class);
            intent.putExtra("id_utilisateur", id_utilisateur);
            startActivity(intent);
        }
        else if (v.getId() == R.id.retour_liste_reservation) {
            Intent intent = new Intent(ListeReservation.this, menuNavigation.class);
            startActivity(intent);
            finish();
        }
    }
}