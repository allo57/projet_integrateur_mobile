/***************************************************
 *
 * Fichier : AffichageAnimalUnique.java
 * Auteur : Heidi Lavoie
 * Fonctionnalité : Code de l'affichage d'un animal précis
 * Date : 9 mai 2025
 *
 ***************************************************/


package com.example.zootopia_mobile.animaux;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.zootopia_mobile.R;
import com.example.zootopia_mobile.ZooLocation;
import com.example.zootopia_mobile.activite.Activite;
import com.example.zootopia_mobile.magasin.ListePanier;
import com.example.zootopia_mobile.menuNavigation;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class AffichageAnimalUnique extends AppCompatActivity implements View.OnClickListener {

    private ImageButton boutonActivite;
    private ImageButton boutonMap;
    private ImageButton boutonAnimaux;
    private ImageButton boutonPanier;
    private ImageButton boutonReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_animal_unique);

        //header
        ImageButton buttonNav = findViewById(R.id.imageButtonFermerNav);
        buttonNav.setOnClickListener(v -> {
            Intent intent = new Intent(AffichageAnimalUnique.this, AffichageAnimaux.class);
            startActivity(intent);
        });

        ImageView imageView = findViewById(R.id.imageViewAnimal);
        TextView nomTextView = findViewById(R.id.textViewNomAnimal);
        TextView ageTextView = findViewById(R.id.textViewAge);
        TextView descTextView = findViewById(R.id.textViewDescription);

        String nom = getIntent().getStringExtra("nom");
        String description = getIntent().getStringExtra("description");
        String date_naissance = getIntent().getStringExtra("date_naissance");
        String etat = getIntent().getStringExtra("etat");
        String etat_desc = getIntent().getStringExtra("etat_desc");
        String image = getIntent().getStringExtra("image");

        String ageString = "";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDate = LocalDate.parse(date_naissance, formatter);
            LocalDate currentDate = LocalDate.now();
            Period age = Period.between(birthDate, currentDate);
            int years = age.getYears();
            int months = age.getMonths();

            if (years > 0) {
                ageString = years + " an" + (years > 1 ? "s" : "");
                if (months > 0) {
                    ageString += " et " + months + " mois";
                }
            } else {
                ageString = months + " mois";
            }
        } catch (Exception e) {
            ageString = "Âge inconnu";
        }

        Glide.with(this)
                .load("http://10.0.2.2:8000/img/" + image)
                .placeholder(R.drawable.animal_default)
                .into(imageView);

        nomTextView.setText(nom);
        ageTextView.setText("Âge : " + ageString);
        descTextView.setText("\n" + etat + " : " + etat_desc + "\n\n" + description);

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
            intent = new Intent(AffichageAnimalUnique.this, ListePanier.class);
        } else if (v.getId() == R.id.btnActivite) {
            // Intent pour ouvrir la page Activité
            intent = new Intent(AffichageAnimalUnique.this, Activite.class);
        } else if (v.getId() == R.id.btnMap) {
            // Intent pour ouvrir la carte
            intent = new Intent(AffichageAnimalUnique.this, ZooLocation.class);
        } else if (v.getId() == R.id.btnPaw) {
            // Intent pour afficher les animaux
            intent = new Intent(AffichageAnimalUnique.this, AffichageAnimaux.class);
        } else if (v.getId() == R.id.btnReservation) {
            // intent = new Intent(Activite.this, Reservation.class);
            // startActivity(intent);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}