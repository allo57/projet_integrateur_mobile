package com.example.zootopia_mobile.reservation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zootopia_mobile.R;
import com.example.zootopia_mobile.SQLiteManager;

public class ModifierReservation extends AppCompatActivity implements View.OnClickListener{
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modifier_reservation);

        EditText nom = findViewById(R.id.reservation_modif_nom);
        EditText no_tel = findViewById(R.id.reservation_modif_no_tel);
        EditText nb_personnes = findViewById(R.id.reservation_modif_nb_personnes);
        EditText date = findViewById(R.id.reservation_modif_no_tel);
        EditText heure = findViewById(R.id.reservation_modif_no_tel);
        EditText note = findViewById(R.id.reservation_modif_note);


        AppCompatButton save = findViewById(R.id.enregistrer_modification);
        save.setOnClickListener(this);

        AppCompatButton annuler = findViewById(R.id.annuler_modification);
        annuler.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.enregistrer_modification) {

            SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(context);
            Reservation updated_reservation = new Reservation();
            updated_reservation.set_id_reservation(1);

            sqLiteManager.updateReservations(updated_reservation);

            Intent intent = new Intent(ModifierReservation.this, ListeReservation.class);
            startActivity(intent);
            finish();
        }
        else if (v.getId() == R.id.annuler_modification) {
            finish();
        }
    }
}