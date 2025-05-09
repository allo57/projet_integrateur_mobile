package com.example.zootopia_mobile.activite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zootopia_mobile.R;
import com.example.zootopia_mobile.animaux.AffichageAnimaux;
import com.example.zootopia_mobile.api.ApiService;
import com.example.zootopia_mobile.api.RetrofitInstance;
import com.example.zootopia_mobile.menuNavigation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Activite extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ActiviteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activite);
        ImageButton buttonNav = findViewById(R.id.imageButtonFermerNav);
        buttonNav.setOnClickListener(v -> {
            Intent intent = new Intent(Activite.this, menuNavigation.class);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ActiviteAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        ApiService apiService = RetrofitInstance.getApi();

        Call<ActiviteResponse> call = apiService.getActivites();

        call.enqueue(new Callback<ActiviteResponse>() {
            @Override
            public void onResponse(Call<ActiviteResponse> call, Response<ActiviteResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ActiviteModel> liste = response.body().getActivites();
                    adapter = new ActiviteAdapter(liste);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("API", "Code d'erreur HTTP : " + response.code());
                    if (response.errorBody() != null) {
                        try {
                            Log.e("API", "Erreur body : " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.e("API", "Erreur body null");
                    }
                }
            }


            @Override
            public void onFailure(Call<ActiviteResponse> call, Throwable t) {
                Log.e("API", "Erreur : " + t.getMessage());
            }
        });
    }
}


