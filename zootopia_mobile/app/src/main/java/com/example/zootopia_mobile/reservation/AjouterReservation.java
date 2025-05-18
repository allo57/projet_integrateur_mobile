package com.example.zootopia_mobile.reservation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.zootopia_mobile.R;
import com.example.zootopia_mobile.ZooLocation;
import com.example.zootopia_mobile.activite.Activite;
import com.example.zootopia_mobile.animaux.AffichageAnimaux;
import com.example.zootopia_mobile.api.ApiService;
import com.example.zootopia_mobile.api.RetrofitInstance;
import com.example.zootopia_mobile.informations.Informations;
import com.example.zootopia_mobile.magasin.ListeItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AjouterReservation extends AppCompatActivity implements View.OnClickListener {

    private static final Logger log = LoggerFactory.getLogger(AjouterReservation.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ajouter_reservation);

        Spinner heure = findViewById(R.id.reservation_ajout_heure);
        ArrayAdapter<String> heureAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,  getResources().getStringArray(R.array.liste_heure));
        heureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        heure.setAdapter(heureAdapter);

        AppCompatButton ajouter = findViewById(R.id.ajout_reservation);
        AppCompatButton annuler = findViewById(R.id.annuler_ajout);
        ImageButton retour = findViewById(R.id.retour_liste_reservation);

        retour.setOnClickListener(this);
        ajouter.setOnClickListener(this);
        annuler.setOnClickListener(this);

        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.menu_animaux) {
                    Intent intent = new Intent(AjouterReservation.this, AffichageAnimaux.class);
                    startActivity(intent);
                }
                else if (id == R.id.menu_activites) {
                    Intent intent = new Intent(AjouterReservation.this, Activite.class);
                    startActivity(intent);
                }
                else if (id == R.id.menu_panier) {
                    Intent intent = new Intent(AjouterReservation.this, ListeItem.class);
                    startActivity(intent);
                }
                else if (id == R.id.menu_carte) {
                    Intent intent = new Intent(AjouterReservation.this, ZooLocation.class);
                    startActivity(intent);
                }
                else if (id == R.id.menu_info) {
                    Intent intent = new Intent(AjouterReservation.this, Informations.class);
                    startActivity(intent);
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ajout_reservation) {
            sendData();

            Intent intent = new Intent(AjouterReservation.this, ListeReservation.class);
            startActivity(intent);
            finish();
        }
        else if (v.getId() == R.id.annuler_ajout || v.getId() == R.id.retour_liste_reservation) {
            finish();
        }
    }

    public void sendData() {
        EditText nom = findViewById(R.id.reservation_ajout_nom);
        EditText no_tel = findViewById(R.id.reservation_ajout_no_tel);
        EditText nb_personnes = findViewById(R.id.reservation_ajout_nb_personnes);
        EditText date = findViewById(R.id.reservation_ajout_date);
        Spinner heure = findViewById(R.id.reservation_ajout_heure);
        EditText note = findViewById(R.id.reservation_ajout_note);

        String add_nom = nom.getText().toString();
        StringBuilder add_no_tel = new StringBuilder("(" + no_tel.getText().toString());
        add_no_tel.insert(4,") ");
        add_no_tel.insert(9,"-");

        String add_date = date.getText().toString();
        String add_heure = heure.getSelectedItem().toString();
        int add_nb_personnes = Integer.parseInt(nb_personnes.getText().toString());
        String add_note = note.getText().toString();
        int add_id_user = 1;

        ReservationPost new_reservation = new ReservationPost(add_nom, add_no_tel.toString(), add_nb_personnes, add_date, add_heure, add_note, 1, add_id_user);
        ApiService apiService = RetrofitInstance.getApi();
        Call<ResponseReservation> call = apiService.addReservation(new_reservation);

        call.enqueue(new Callback<ResponseReservation>() {
            @Override
            public void onResponse(Call<ResponseReservation> call, Response<ResponseReservation> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("TEST", "Added");
                    Intent intent = new Intent(AjouterReservation.this, ListeReservation.class);
                    startActivity(intent);
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
            public void onFailure(Call<ResponseReservation> call, Throwable t) {
                Log.e("API", "Erreur : " + t.getMessage());

            }
        });
    }
}