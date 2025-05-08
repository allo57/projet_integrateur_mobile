package com.example.zootopia_mobile;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

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
    }
}