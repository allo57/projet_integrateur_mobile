/***************************************************
 *
 * Fichier : RecyclerAnimaux.java
 * Auteur : Heidi Lavoie
 * Fonctionnalité : Code du recycler des animaux
 * Date : 9 mai 2025
 *
 ***************************************************/

package com.example.zootopia_mobile.animaux;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zootopia_mobile.R;

public class RecylerAnimaux extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recyler_animaux);
    }
}