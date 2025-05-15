package com.example.zootopia_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zootopia_mobile.activite.Activite;
import com.example.zootopia_mobile.animaux.AffichageAnimaux;
import com.example.zootopia_mobile.billets.Billets;
import com.example.zootopia_mobile.informations.Informations;
import com.example.zootopia_mobile.magasin.ListeItem;
import com.example.zootopia_mobile.reservation.ListeReservation;

public class menuNavigation extends AppCompatActivity implements View.OnClickListener {

    private ImageButton buttonFermerNav;
    private ImageButton imageButtonNotifNav;
    private ImageButton imageButtonCalenActivite;
    private ImageButton imageButtonAnimaux;
    private ImageButton imageButtonBilletterie;
    private ImageButton imageButtonAPropos;
    private ImageButton imageButtonLocalisation;
    private ImageButton imageButtonReservation;
    private Button buttonInscription;
    private Button buttonSeConnecter;
    private Button buttonAIdeEnLigne;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_navigation);

        //Différents buttons
        buttonFermerNav = (ImageButton) findViewById(R.id.imageButtonFermerNav);
        imageButtonLocalisation = (ImageButton) findViewById(R.id.imageButtonLocalisation);
        imageButtonCalenActivite = (ImageButton) findViewById(R.id.imageButtonCalenActivit);
        imageButtonBilletterie = (ImageButton) findViewById(R.id.imageButtonBilletterie);
        imageButtonAnimaux = (ImageButton) findViewById(R.id.imageButtonAnimaux);
        imageButtonReservation = (ImageButton) findViewById(R.id.imageButtonReservations);
        imageButtonAPropos = (ImageButton) findViewById(R.id.imageButtonAPropos);

        //On set le OnClickListener
        buttonFermerNav.setOnClickListener(this);
        imageButtonLocalisation.setOnClickListener(this);
        imageButtonCalenActivite.setOnClickListener(this);
        imageButtonAnimaux.setOnClickListener(this);
        imageButtonBilletterie.setOnClickListener(this);
        imageButtonReservation.setOnClickListener(this);
        imageButtonAPropos.setOnClickListener(this);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageButtonFermerNav) {
            Intent intent = new Intent(menuNavigation.this, MainActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.imageButtonLocalisation) {
            Intent intent = new Intent(menuNavigation.this, ZooLocation.class);
            startActivity(intent);
        }
        else if (v.getId()==R.id.imageButtonAnimaux){
            Intent intent = new Intent(menuNavigation.this, AffichageAnimaux.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.imageButtonReservations) {
            Intent intent = new Intent(menuNavigation.this, ListeReservation.class);
            startActivity(intent);
        }else if (v.getId()==R.id.imageButtonBilletterie){
            Intent intent = new Intent(menuNavigation.this, Billets.class);
            startActivity(intent);
        }else if (v.getId()==R.id.imageButtonAPropos){
            Intent intent = new Intent(menuNavigation.this, Informations.class);
            startActivity(intent);
//        }else if (v.getId()==R.id.buttonSeConnecter){
//            Intent intent = new Intent(menuNavigation.this, Connection.class);
//            startActivity(intent);
//        }else if (v.getId()==R.id.buttonAIdeEnLigne){
//            Intent intent = new Intent(menuNavigation.this, Aide.class);
//            startActivity(intent);
//        }
        }
        else if(v.getId() == R.id.imageButtonCalenActivit){
            Intent intent = new Intent(menuNavigation.this, Activite.class);
            startActivity(intent);
        }
    }
}