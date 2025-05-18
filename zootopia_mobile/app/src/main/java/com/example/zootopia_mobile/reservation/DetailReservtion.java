package com.example.zootopia_mobile.reservation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zootopia_mobile.R;
import com.example.zootopia_mobile.SQLiteManager;
import com.example.zootopia_mobile.ZooLocation;
import com.example.zootopia_mobile.activite.Activite;
import com.example.zootopia_mobile.animaux.AffichageAnimaux;
import com.example.zootopia_mobile.informations.Informations;
import com.example.zootopia_mobile.magasin.ListeItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DetailReservtion extends AppCompatActivity  implements View.OnClickListener{
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_reservtion);

        TextView id_reservation = findViewById(R.id.detail_id_reservation);
        TextView nom = findViewById(R.id.detail_nom);
        TextView no_tel = findViewById(R.id.detail_no_tel);
        TextView nb_personnes = findViewById(R.id.detail_nb_personnes);
        TextView date = findViewById(R.id.detail_date);
        TextView heure = findViewById(R.id.detail_heure);
        TextView note = findViewById(R.id.detail_note);

        ImageButton retour = findViewById(R.id.retour_liste_reservation);
        retour.setOnClickListener(this);

        Intent data = getIntent();
        int reservation_id = data.getIntExtra("id_reservation", 0);
        int user_id = data.getIntExtra("id_utilisateur", 0);

        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(context);
        Reservation reservation = sqLiteManager.getReservation(reservation_id, user_id);

        id_reservation.setText(String.valueOf(reservation.get_id_reservation()));
        nom.setText(reservation.get_nom());

        StringBuilder tel = new StringBuilder("(" + String.valueOf(reservation.get_no_tel()));
        tel.insert(4,") ");
        tel.insert(9,"-");

        no_tel.setText(tel);

        nb_personnes.setText(String.valueOf(reservation.get_nb_personnes()));
        date.setText(reservation.get_date());
        heure.setText(reservation.get_heure());
        note.setText(reservation.get_note());

        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.menu_animaux) {
                    Intent intent = new Intent(DetailReservtion.this, AffichageAnimaux.class);
                    startActivity(intent);
                }
                else if (id == R.id.menu_activites) {
                    Intent intent = new Intent(DetailReservtion.this, Activite.class);
                    startActivity(intent);
                }
                else if (id == R.id.menu_panier) {
                    Intent intent = new Intent(DetailReservtion.this, ListeItem.class);
                    startActivity(intent);
                }
                else if (id == R.id.menu_carte) {
                    Intent intent = new Intent(DetailReservtion.this, ZooLocation.class);
                    startActivity(intent);
                }
                else if (id == R.id.menu_info) {
                    Intent intent = new Intent(DetailReservtion.this, Informations.class);
                    startActivity(intent);
                }
                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}