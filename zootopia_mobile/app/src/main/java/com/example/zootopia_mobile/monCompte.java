package com.example.zootopia_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class monCompte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mon_compte);

        TextView infos = findViewById(R.id.textViewInformations);
        Button boutonSupprimer = findViewById(R.id.buttonSupprimerCompte);
        Button boutonModifier = findViewById(R.id.buttonModifCompte);
        ImageButton buttonNav = findViewById(R.id.imageButtonFermerNav);

        buttonNav.setOnClickListener(v -> {
            Intent intent = new Intent(monCompte.this, menuNavigation.class);
            startActivity(intent);
        });

        boutonSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(monCompte.this, confirmationSuppresionCompte.class);
                startActivity(intent);
            }
        });

        boutonModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(monCompte.this, modificationCompte.class);
                startActivity(intent);
            }
        });

        SQLiteManager dbHelper = new SQLiteManager(this);

        int id = inscription.userId;
        User user = dbHelper.getUser(id);

        String length = user.getPassword();
        int etoiles = length.length();
        String mdp = "";

        for (int i = 0; i < etoiles; i++) {
            mdp = mdp.concat("*");
        }

        if (user != null) {
            String userInfo = "Nom : " + user.getName() + "\n" + "\n"
                    + "Courriel : " + user.getEmail() + "\n" + "\n"
                    + "Téléphone : " + user.getNoTel() + "\n" + "\n"
                    + "Code postal : " + user.getCodePostal() + "\n" + "\n"
                    + "Mot de passe : " + mdp;

            infos.setText(userInfo);
        } else {
            infos.setText("Utilisateur non trouvé.");
        }
    }
}