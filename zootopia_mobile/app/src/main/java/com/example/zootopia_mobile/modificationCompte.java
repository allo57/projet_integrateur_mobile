
/***************************************************
 *
 * Fichier : modificationCompte.java
 * Auteur : Heidi Lavoie
 * Fonctionnalité : Code de l'affichage du form de modification de compte
 * Date : 17 mai 2025
 *
 ***************************************************/

package com.example.zootopia_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class modificationCompte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modification_compte);

        ImageButton buttonNav = findViewById(R.id.imageButtonFermerNav);
        buttonNav.setOnClickListener(v -> {
            Intent intent = new Intent(modificationCompte.this, monCompte.class);
            startActivity(intent);
        });

        SQLiteManager dbHelper = new SQLiteManager(this);

        int id = inscription.userId;
        User user = dbHelper.getUser(id);

        EditText editNom = findViewById(R.id.editNom);
        EditText editPhone = findViewById(R.id.editPhone);
        EditText editEmail = findViewById(R.id.editEmail);
        EditText editPostal = findViewById(R.id.editPostal);
        EditText editPassword = findViewById(R.id.editPassword);
        EditText editPasswordConf = findViewById(R.id.editPasswordConf);

        editNom.setText(user.getName());
        editPhone.setText(user.getNoTel());
        editEmail.setText(user.getEmail());
        editPostal.setText(user.getCodePostal());
        editPassword.setText("");
        editPasswordConf.setText("");

        Button buttonConfirmer = findViewById(R.id.buttonConfModif);

        buttonConfirmer.setOnClickListener(v -> {
            String nom = editNom.getText().toString().trim();
            String tel = editPhone.getText().toString().trim();
            String email = editEmail.getText().toString().trim();
            String postal = editPostal.getText().toString().trim();
            String password = editPassword.getText().toString();
            String passwordConf = editPasswordConf.getText().toString();

            // Champs vide
            if (nom.isEmpty() || postal.isEmpty() || tel.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConf.isEmpty()) {
                Toast.makeText(modificationCompte.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Nom complet
            if (!nom.matches("^[A-ZÀ-Ú][a-zà-ú]+([ -][A-ZÀ-Ú][a-zà-ú]+)*$")) {
                Toast.makeText(modificationCompte.this, "Nom complet invalide.", Toast.LENGTH_SHORT).show();
                return;
            }

            // (000) 000-0000
            if (!tel.matches("^\\(\\d{3}\\) \\d{3}-\\d{4}$")) {
                Toast.makeText(modificationCompte.this, "Numéro de téléphone invalide. Format attendu : (000) 000-0000", Toast.LENGTH_SHORT).show();
                return;
            }

            //@ et un domaine
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]{2,6}$")) {
                Toast.makeText(modificationCompte.this, "Adresse courriel invalide.", Toast.LENGTH_SHORT).show();
                return;
            }

            // A1A 1A1
            if (!postal.matches("^[A-Z]\\d[A-Z] \\d[A-Z]\\d$")) {
                Toast.makeText(modificationCompte.this, "Code postal invalide. Format attendu : A1A 1A1", Toast.LENGTH_SHORT).show();
                return;
            }

            // Mot de passe identiques
            if (!password.equals(passwordConf)) {
                Toast.makeText(modificationCompte.this, "Les mots de passe ne sont pas identiques.", Toast.LENGTH_SHORT).show();
                return;
            }

            dbHelper.updateUser(id, nom, tel, email, postal, password);

            Toast.makeText(this, "Compte mis à jour avec succès!", Toast.LENGTH_SHORT).show();

            // Redirection vers la page de compte
            Intent intent = new Intent(modificationCompte.this, monCompte.class);
            startActivity(intent);
        });

    }
}
