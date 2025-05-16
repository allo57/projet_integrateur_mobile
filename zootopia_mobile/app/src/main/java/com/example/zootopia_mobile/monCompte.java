package com.example.zootopia_mobile;

import android.os.Bundle;
import android.view.View;
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

        SQLiteManager dbHelper = new SQLiteManager(this);

        int id = inscription.userId;
        User user = dbHelper.getUser(id);

        if (user != null) {
            String userInfo = "Nom : " + user.getName() + "\n" + "\n"
                    + "Courriel : " + user.getEmail() + "\n" + "\n"
                    + "Téléphone : " + user.getNoTel() + "\n" + "\n"
                    + "Code postal : " + user.getCodePostal();

            infos.setText(userInfo);
        } else {
            infos.setText("Utilisateur non trouvé.");
        }
    }
}