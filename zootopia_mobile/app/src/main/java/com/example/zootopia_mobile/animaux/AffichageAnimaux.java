package com.example.zootopia_mobile.animaux;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zootopia_mobile.R;
import com.example.zootopia_mobile.api.ApiClient;
import com.example.zootopia_mobile.api.ApiService;
import com.example.zootopia_mobile.menuNavigation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AffichageAnimaux extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_animaux);

        ImageButton buttonNav = findViewById(R.id.imageButtonFermerNav);
        buttonNav.setOnClickListener(v -> {
            Intent intent = new Intent(AffichageAnimaux.this, menuNavigation.class);
            startActivity(intent);
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerViewAnimaux);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        AnimalAdapter adapter = new AnimalAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.getAnimaux().enqueue(new Callback<ReponseAnimaux>() {
            @Override
            public void onResponse(Call<ReponseAnimaux> call, Response<ReponseAnimaux> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setAnimaux(response.body().getData());
                } else {
                    Toast.makeText(AffichageAnimaux.this, "Erreur de réponse", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ReponseAnimaux> call, Throwable t) {
                Toast.makeText(AffichageAnimaux.this, "Erreur API : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
