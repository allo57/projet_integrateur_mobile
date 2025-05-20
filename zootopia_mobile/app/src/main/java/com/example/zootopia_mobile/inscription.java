

/***************************************************
 *
 * Fichier : inscription.java
 * Auteur : Heidi Lavoie
 * Fonctionnalité : Code de l'affichage du form d'inscription
 * Date : 15 mai 2025
 *
 ***************************************************/


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

public class inscription extends AppCompatActivity {

    public static int userId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inscription);

        ImageButton buttonNav = findViewById(R.id.imageButtonFermerNav);
        buttonNav.setOnClickListener(v -> {
            Intent intent = new Intent(inscription.this, menuNavigation.class);
            startActivity(intent);
        });

        // Gestion inscription
        Button buttonConf = findViewById(R.id.buttonConnexion);
        buttonConf.setOnClickListener(v -> {
            EditText nomEdit = findViewById(R.id.editNom);
            EditText postalEdit = findViewById(R.id.editPostal);
            EditText phoneEdit = findViewById(R.id.editPhone);
            EditText emailEdit = findViewById(R.id.editEmail);
            EditText passwordEdit = findViewById(R.id.editPassword);
            EditText passwordConfEdit = findViewById(R.id.editPasswordConf);

            String nom = nomEdit.getText().toString().trim();
            String postal = postalEdit.getText().toString().trim();
            String phone = phoneEdit.getText().toString().trim();
            String email = emailEdit.getText().toString().trim();
            String password = passwordEdit.getText().toString();
            String passwordConf = passwordConfEdit.getText().toString();

            // Champs vide
            if (nom.isEmpty() || postal.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConf.isEmpty()) {
                Toast.makeText(inscription.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Nom complet
            if (!nom.matches("^[A-ZÀ-Ú][a-zà-ú]+([ -][A-ZÀ-Ú][a-zà-ú]+)*$")) {
                Toast.makeText(inscription.this, "Nom complet invalide.", Toast.LENGTH_SHORT).show();
                return;
            }

            // (000) 000-0000
            if (!phone.matches("^\\(\\d{3}\\) \\d{3}-\\d{4}$")) {
                Toast.makeText(inscription.this, "Numéro de téléphone invalide. Format attendu : (000) 000-0000", Toast.LENGTH_SHORT).show();
                return;
            }

            //@ et un domaine
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]{2,6}$")) {
                Toast.makeText(inscription.this, "Adresse courriel invalide.", Toast.LENGTH_SHORT).show();
                return;
            }

            // A1A 1A1
            if (!postal.matches("^[A-Z]\\d[A-Z] \\d[A-Z]\\d$")) {
                Toast.makeText(inscription.this, "Code postal invalide. Format attendu : A1A 1A1", Toast.LENGTH_SHORT).show();
                return;
            }

            // Mot de passe identiques
            if (!password.equals(passwordConf)) {
                Toast.makeText(inscription.this, "Les mots de passe ne sont pas identiques.", Toast.LENGTH_SHORT).show();
                return;
            }

            SQLiteManager db = SQLiteManager.instanceOfDatabase(inscription.this);
            db.ajoutUser(nom, postal, phone, 1, email, password);

            Toast.makeText(inscription.this, "Inscription réussie, connexion en cours...", Toast.LENGTH_SHORT).show();

            SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
            prefs.edit().putBoolean("loggedIn", true).apply();

            int idUtilisateur = db.getIdUser(email, password);
            if (idUtilisateur != -1) {
                inscription.userId = idUtilisateur;
            }

            Toast.makeText(inscription.this, "Connexion réussie !", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(inscription.this, MainActivity.class);
            startActivity(intent);
        });
    }
}