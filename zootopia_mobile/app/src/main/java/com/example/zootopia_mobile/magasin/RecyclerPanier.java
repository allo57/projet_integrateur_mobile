/***************************************************
 *
 * Fichier : RecyclerPanier.java
 * Auteur : Sarah-Maude Gagné
 * Fonctionnalité : recyclerview du panier
 * Date : 18 mai 2025
 *
 ***************************************************/
package com.example.zootopia_mobile.magasin;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zootopia_mobile.R;

public class RecyclerPanier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_panier);
    }
}