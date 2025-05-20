
/***************************************************
 *
 * Fichier : confirmationSuppresionCompte.java
 * Auteur : Heidi Lavoie
 * Fonctionnalité : Code de l'affichage de la confirmation de suppression de compte
 * Date : 17 mai 2025
 *
 ***************************************************/

package com.example.zootopia_mobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class confirmationSuppresionCompte extends AppCompatActivity {

    private Button conf;
    private Button annuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirmation_suppresion_compte);

        ImageButton buttonNav = findViewById(R.id.imageButtonFermerNav);
        conf = findViewById(R.id.confirmation);
        annuler = findViewById(R.id.annulation);

        SQLiteManager db = SQLiteManager.instanceOfDatabase(confirmationSuppresionCompte.this);
        int id = inscription.userId;

        buttonNav.setOnClickListener(v -> {
            Intent intent = new Intent(confirmationSuppresionCompte.this, menuNavigation.class);
            startActivity(intent);
        });

        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteUser(id);

                Toast.makeText(confirmationSuppresionCompte.this, "Suppression du compte en cours...", Toast.LENGTH_SHORT).show();

                SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
                prefs.edit().putBoolean("loggedIn", false).apply();
                inscription.userId = 0;

                Toast.makeText(confirmationSuppresionCompte.this, "Compte supprimé...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(confirmationSuppresionCompte.this, menuNavigation.class);
                startActivity(intent);
            }
        });

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(confirmationSuppresionCompte.this, monCompte.class);
                startActivity(intent);
            }
        });

    }
}