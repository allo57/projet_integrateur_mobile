package com.example.zootopia_mobile.reservation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zootopia_mobile.R;
import com.example.zootopia_mobile.SQLiteManager;
import com.example.zootopia_mobile.api.ApiService;
import com.example.zootopia_mobile.api.RetrofitInstance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListeReservation extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    RecyclerReservation adapter;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_liste_reservation);

        recyclerView = (RecyclerView) findViewById(R.id.liste_reservation);
        adapter = new RecyclerReservation(context, new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiService apiService = RetrofitInstance.getApi();
        Call<ResponseReservation> call = apiService.getUserReservations("1");

        call.enqueue(new Callback<ResponseReservation>() {
            @Override
            public void onResponse(Call<ResponseReservation> call, Response<ResponseReservation> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Reservation> liste = response.body().getUserReservations();
                    Log.e("TEST", "Response: " + liste.toString());
                    adapter = new RecyclerReservation(context, liste);
                    recyclerView.setAdapter(adapter);

                    SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(context);
                    for (int i = 0; i < liste.size(); i++) {
                        sqLiteManager.ajoutReservation(liste.get(i));
                    }


                } else {
                    Log.e("API", "Code d'erreur HTTP : " + response.code());
                    if (response.errorBody() != null) {
                        try {
                            Log.e("API", "Erreur body : " + response.errorBody().string());
                            // Ajoute les nouvelles données et modifie les anciennes données
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.e("API", "Erreur body null");
                        // Check if la base de données n'est pas nulle
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseReservation> call, Throwable t) {
                Log.e("API", "Erreur : " + t.getMessage());

            }
        });

        AppCompatButton ajouter = findViewById(R.id.ajouter);
        ajouter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ListeReservation.this, AjouterReservation.class);
        startActivity(intent);
    }
}