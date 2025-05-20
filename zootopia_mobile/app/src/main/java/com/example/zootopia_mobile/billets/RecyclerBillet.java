/***************************************************
 *
 * Fichier : RecyclerBillet.java
 * Auteur : Samuel Cloutier
 * Fonctionnalité : Code du recycler du billet
 * Date : 8 mai 2025
 *
 ***************************************************/
package com.example.zootopia_mobile.billets;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zootopia_mobile.R;

public class RecyclerBillet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_billet2);
    }
}