package com.example.zootopia_mobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class connexion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_connexion);
        ImageButton buttonNav = findViewById(R.id.imageButtonFermerNav);
        buttonNav.setOnClickListener(v -> {
            Intent intent = new Intent(connexion.this, menuNavigation.class);
            startActivity(intent);
        });

        Button buttonInsc = findViewById(R.id.buttonInscription2);
        buttonInsc.setOnClickListener(v -> {
            Intent intent = new Intent(connexion.this, inscription.class);
            startActivity(intent);
        });

        Button button = findViewById(R.id.buttonConnexion);
        button.setOnClickListener(v -> {
            EditText courrielText = findViewById(R.id.editConnexionEmail);
            EditText mdpText = findViewById(R.id.editPassword);

            String courriel = courrielText.getText().toString().trim();
            String mdp = mdpText.getText().toString().trim();

            if (courriel.isEmpty() || mdp.isEmpty()) {
                Toast.makeText(connexion.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
                return;
            }

            SQLiteManager dbHelper = new SQLiteManager(connexion.this);
            boolean utilisateurValide = dbHelper.verifierUtilisateur(courriel, mdp);

            if (utilisateurValide) {
                SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
                prefs.edit().putBoolean("loggedIn", true).apply();

                int idUtilisateur = dbHelper.getIdUser(courriel, mdp);

                if (idUtilisateur != -1) {
                    inscription.userId = idUtilisateur;
                }

                Toast.makeText(connexion.this, "Connexion réussie !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(connexion.this, menuNavigation.class);
                startActivity(intent);
            } else {
                Toast.makeText(connexion.this, "Courriel ou mot de passe invalide.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}