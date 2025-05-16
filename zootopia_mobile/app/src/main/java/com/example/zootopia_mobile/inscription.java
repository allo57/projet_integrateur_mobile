package com.example.zootopia_mobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zootopia_mobile.activite.Activite;
import com.example.zootopia_mobile.SQLiteManager;

public class inscription extends AppCompatActivity {

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
        Button buttonConf = findViewById(R.id.buttonConfInscription);
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

            if (nom.isEmpty() || postal.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConf.isEmpty()) {
                Toast.makeText(inscription.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(passwordConf)) {
                Toast.makeText(inscription.this, "Les mots de passe ne sont pas identiques.", Toast.LENGTH_SHORT).show();
                return;
            }

            SQLiteManager db = SQLiteManager.instanceOfDatabase(inscription.this);
            db.ajoutUser(nom, postal, phone, 1, email, password);

            Toast.makeText(inscription.this, "Inscription réussie, connexion en cours...", Toast.LENGTH_SHORT).show();

            SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
            prefs.edit().putBoolean("loggedIn", true).apply();

            Toast.makeText(inscription.this, "Connexion réussie !", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(inscription.this, MainActivity.class);
            startActivity(intent);
        });
    }
}