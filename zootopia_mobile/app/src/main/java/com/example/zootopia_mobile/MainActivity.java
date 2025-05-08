package com.example.zootopia_mobile;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zootopia_mobile.api.ApiClient;
import com.example.zootopia_mobile.api.ApiService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Créer l'API
        // ApiService apiService = ApiClient.getClient().create(ApiService.class);

        // Création ou ouverture de la BD
        SQLiteManager db = SQLiteManager.instanceOfDatabase(this);
        SQLiteDatabase database = db.getWritableDatabase();

        db.ajoutUser("Test", "A1A1A1", "1234567890", 1, "test@email.com", "pass123");
        ImageButton buttonNav = (ImageButton) findViewById(R.id.imageButtonPaw);

        buttonNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, menuNavigation.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}