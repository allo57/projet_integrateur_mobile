package com.example.zootopia_mobile.animaux;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zootopia_mobile.R;

public class AffichageAnimalUnique extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_animal_unique);

        TextView infoTextView = findViewById(R.id.textViewInfosAnimal);

        // Récupération des données de l'intent
        String nom = getIntent().getStringExtra("nom");
        String description = getIntent().getStringExtra("description");
        String date_naissance = getIntent().getStringExtra("date_naissance");
        String etat = getIntent().getStringExtra("etat");
        String image = getIntent().getStringExtra("image");

        String info = "Nom : " + nom + "\n\nDescription : " + description +
                "\n\nDate de naissance : " + date_naissance + "\n\nÉtat de l'animal : " + etat +
                "\n\nImage : " + image;
        infoTextView.setText(info);
    }
}