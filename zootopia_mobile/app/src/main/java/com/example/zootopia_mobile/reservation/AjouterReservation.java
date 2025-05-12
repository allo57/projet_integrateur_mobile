package com.example.zootopia_mobile.reservation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.zootopia_mobile.R;
import com.example.zootopia_mobile.api.ApiService;
import com.example.zootopia_mobile.api.RetrofitInstance;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AjouterReservation extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ajouter_reservation);

        AppCompatButton ajouter = findViewById(R.id.ajout_reservation);
        AppCompatButton annuler = findViewById(R.id.annuler_ajout);
        ajouter.setOnClickListener(this);
        annuler.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ajout_reservation) {
            sendData();
        }
        else if (v.getId() == R.id.annuler_ajout) {
            finish();
        }
    }

    public void sendData() {
        /*EditText nom = findViewById(R.id.reservation_ajout_nom);
        EditText no_tel = findViewById(R.id.reservation_ajout_no_tel);
        EditText nb_personnes = findViewById(R.id.reservation_ajout_nb_personnes);
        EditText date = findViewById(R.id.reservation_ajout_date);
        Spinner heure = findViewById(R.id.reservation_ajout_heure);
        EditText note = findViewById(R.id.reservation_ajout_note);
        int tel = Integer.parseInt(no_tel.getText().toString());
        int nbr_personnes = Integer.parseInt(nb_personnes.getText().toString());


        Reservation new_reservation = new Reservation(nom.getText().toString(),
                                                        tel,
                                                        nbr_personnes,
                                                        date.getText().toString(),
                                                        "17:30",
                                                        note.getText().toString()
        );

        ApiService apiService = RetrofitInstance.getApi();
        Call<ResponseReservation> call = apiService.addReservation();

        call.enqueue(new Callback<ResponseReservation>() {
            @Override
            public void onResponse(Call<ResponseReservation> call, Response<ResponseReservation> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("TEST", "Added");
                    Intent intent = new Intent(AjouterReservation.this, ListeReservation.class);
                    startActivity(intent);
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
            public void onFailure(Call<ResponseReservation> call, Throwable t) {
                Log.e("API", "Erreur : " + t.getMessage());

            }
        });*/
    }
}