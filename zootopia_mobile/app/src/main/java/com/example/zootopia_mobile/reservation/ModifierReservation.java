package com.example.zootopia_mobile.reservation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.zootopia_mobile.R;
import com.example.zootopia_mobile.SQLiteManager;
import com.example.zootopia_mobile.ZooLocation;
import com.example.zootopia_mobile.activite.Activite;
import com.example.zootopia_mobile.animaux.AffichageAnimaux;
import com.example.zootopia_mobile.api.ApiService;
import com.example.zootopia_mobile.api.RetrofitInstance;
import com.example.zootopia_mobile.informations.Informations;
import com.example.zootopia_mobile.magasin.ListeItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifierReservation extends AppCompatActivity implements View.OnClickListener{
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modifier_reservation);

        Intent data = getIntent();
        int id_reservation = data.getIntExtra("id_reservation", 0);
        int id_user = data.getIntExtra("id_utilisateur", 0);

        TextView title = findViewById(R.id.titre_modifier_reservation);
        title.setText("Numéro de la réservation : " + String.valueOf(id_reservation));

        EditText nom = findViewById(R.id.reservation_modif_nom);
        EditText no_tel = findViewById(R.id.reservation_modif_no_tel);
        EditText nb_personnes = findViewById(R.id.reservation_modif_nb_personnes);
        EditText date = findViewById(R.id.reservation_modif_date);
        EditText note = findViewById(R.id.reservation_modif_note);

        Spinner heure = findViewById(R.id.reservation_modif_heure);
        ArrayAdapter<String> heureAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,  getResources().getStringArray(R.array.liste_heure));
        heureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        heure.setAdapter(heureAdapter);

        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(context);
        Reservation reservation = sqLiteManager.getReservation(id_reservation, id_user);

        nom.setText(reservation.get_nom());
        no_tel.setText(String.valueOf(reservation.get_no_tel()));
        nb_personnes.setText(String.valueOf(reservation.get_nb_personnes()));
        date.setText(reservation.get_date());
        note.setText(reservation.get_note());

        ImageButton retour = findViewById(R.id.retour_liste_reservation);
        retour.setOnClickListener(this);

        AppCompatButton save = findViewById(R.id.enregistrer_modification);
        save.setOnClickListener(this);

        AppCompatButton annuler = findViewById(R.id.annuler_modification);
        annuler.setOnClickListener(this);

        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.menu_animaux) {
                    Intent intent = new Intent(ModifierReservation.this, AffichageAnimaux.class);
                    startActivity(intent);
                }
                else if (id == R.id.menu_activites) {
                    Intent intent = new Intent(ModifierReservation.this, Activite.class);
                    startActivity(intent);
                }
                else if (id == R.id.menu_panier) {
                    Intent intent = new Intent(ModifierReservation.this, ListeItem.class);
                    startActivity(intent);
                }
                else if (id == R.id.menu_carte) {
                    Intent intent = new Intent(ModifierReservation.this, ZooLocation.class);
                    startActivity(intent);
                }
                else if (id == R.id.menu_info) {
                    Intent intent = new Intent(ModifierReservation.this, Informations.class);
                    startActivity(intent);
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.enregistrer_modification) {

            SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(context);
            Reservation updated_reservation = new Reservation();
            updated_reservation.set_id_reservation(1);

            sqLiteManager.updateReservations(updated_reservation);

            sendData();

            Intent intent = new Intent(ModifierReservation.this, ListeReservation.class);
            startActivity(intent);
            finish();
        }
        else if (v.getId() == R.id.annuler_modification || v.getId() == R.id.retour_liste_reservation) {
            finish();
        }
    }

    public void sendData() {
        Intent data = getIntent();
        int id_reservation = data.getIntExtra("id_reservation", 0);
        int add_id_user = data.getIntExtra("id_utilisateur", 0);

        Log.e("id", String.valueOf(id_reservation));
        EditText nom = findViewById(R.id.reservation_modif_nom);
        EditText no_tel = findViewById(R.id.reservation_modif_no_tel);
        EditText nb_personnes = findViewById(R.id.reservation_modif_nb_personnes);
        EditText date = findViewById(R.id.reservation_modif_date);
        Spinner heure = findViewById(R.id.reservation_modif_heure);
        EditText note = findViewById(R.id.reservation_modif_note);

        String add_nom = nom.getText().toString();
        StringBuilder add_no_tel = new StringBuilder("(" + no_tel.getText().toString());
        add_no_tel.insert(4,") ");
        add_no_tel.insert(9,"-");

        String add_date = date.getText().toString();
        String add_heure = heure.getSelectedItem().toString();
        int add_nb_personnes = Integer.parseInt(nb_personnes.getText().toString());
        String add_note = note.getText().toString();

        ReservationPost new_reservation = new ReservationPost(add_nom, add_no_tel.toString(), add_nb_personnes, add_date, add_heure, add_note, 1, add_id_user);
        new_reservation.setId_reservation(id_reservation);
        ApiService apiService = RetrofitInstance.getApi();
        Call<ResponseReservation> call = apiService.editReservation(new_reservation);

        call.enqueue(new Callback<ResponseReservation>() {
            @Override
            public void onResponse(Call<ResponseReservation> call, Response<ResponseReservation> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("TEST", "Added");
                    Intent intent = new Intent(ModifierReservation.this, ListeReservation.class);
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