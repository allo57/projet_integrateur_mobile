package com.example.zootopia_mobile.reservation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zootopia_mobile.R;
import com.example.zootopia_mobile.SQLiteManager;

public class DetailReservtion extends AppCompatActivity {
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

        Intent data = getIntent();
        int reservation_id = data.getIntExtra("id_reservation", 0);
        int user_id = data.getIntExtra("id_utilisateur", 0);

        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(context);
        Reservation reservation = sqLiteManager.getReservation(reservation_id, user_id);

        id_reservation.setText(String.valueOf(reservation.get_id_reservation()));
        nom.setText(reservation.get_nom());

        String tel = String.valueOf(reservation.get_no_tel());
        no_tel.setText(tel);

        nb_personnes.setText(String.valueOf(reservation.get_nb_personnes()));
        date.setText(reservation.get_date());
        heure.setText(reservation.get_heure());
        note.setText(reservation.get_note());
    }
}