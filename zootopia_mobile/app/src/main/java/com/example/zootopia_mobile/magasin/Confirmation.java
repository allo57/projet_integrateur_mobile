/***************************************************
 *
 * Fichier : Confirmation.java
 * Auteur : Sarah-Maude Gagné
 * Fonctionnalité : confirmation de la transaction
 * Date : 18 mai 2025
 *
 ***************************************************/
package com.example.zootopia_mobile.magasin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zootopia_mobile.MainActivity;
import com.example.zootopia_mobile.R;

public class Confirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirmation);

        Button retour = findViewById(R.id.retour);
        retour.setOnClickListener(v -> {
            Intent intent = new Intent(Confirmation.this, MainActivity.class);
            startActivity(intent);
        });
    }
}