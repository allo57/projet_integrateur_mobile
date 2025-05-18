package com.example.zootopia_mobile.magasin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zootopia_mobile.MainActivity;
import com.example.zootopia_mobile.R;
import com.example.zootopia_mobile.menuNavigation;

public class Confirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirmation);

        Button retour = findViewById(R.id.retour);
        retour.setOnClickListener(v -> {
            Intent intent = new Intent(Confirmation.this, MainActivity.class);
            startActivity(intent);
        });
    }
}