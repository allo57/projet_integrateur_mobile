/***************************************************
 *
 * Fichier : RecyclerActivite.java
 * Auteur : Sarah-Maude Gagné
 * Fonctionnalité : code du recycler des activités
 * Date : 9 mai 2025
 *
 ***************************************************/
package com.example.zootopia_mobile.activite;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zootopia_mobile.R;

public class RecyclerActivite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_activite);
    }
}