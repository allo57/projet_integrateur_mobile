package com.example.zootopia_mobile;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
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

        // db.ajoutUser("Test", "A1A1A1", "1234567890", 1, "test@email.com", "pass123");
        // db.ajoutUser("Heidi", "J1J 1J1", "(566) 569-5656", 1, "heidi@gmail.com", "user1234");

        // db.deleteUser("Test");
        // db.deleteUser("Heidi");

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

        // FACEBOOK
        ImageButton btnFacebook = findViewById(R.id.btnFacebook);
        btnFacebook.setOnClickListener(v -> {
            Intent intent;
            try {
                getPackageManager().getPackageInfo("com.facebook.katana", 0);
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));
            } catch (Exception e) {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));
            }
            startActivity(intent);
        });

        // INSTAGRAM
        ImageButton btnInstagram = findViewById(R.id.btnInstagram);
        btnInstagram.setOnClickListener(v -> {
            Uri uri = Uri.parse("http://instagram.com");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.instagram.android");

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com")));
            }
        });

        // REDDIT
        ImageButton btnReddit = findViewById(R.id.btnReddit);
        btnReddit.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://www.reddit.com");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.reddit.frontpage");

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });

        // PINTEREST
        ImageButton btnPinterest = findViewById(R.id.btnPinterest);
        btnPinterest.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://www.pinterest.com");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.pinterest");

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });

        // TWITTER
        ImageButton btnTwitter = findViewById(R.id.btnTwitter);
        btnTwitter.setOnClickListener(v -> {
            Intent intent;
            try {
                getPackageManager().getPackageInfo("com.twitter.android", 0);
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com"));
            } catch (Exception e) {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com"));
            }
            startActivity(intent);
        });
    }
}