package com.example.zootopia_mobile.reservation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zootopia_mobile.R;
import com.example.zootopia_mobile.SQLiteManager;
import com.example.zootopia_mobile.api.ApiService;
import com.example.zootopia_mobile.api.RetrofitInstance;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SupprimerReservation extends AppCompatActivity {
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_supprimer_reservation);

        Intent data = getIntent();
        int user_id = data.getIntExtra("id_utilisateur", 0);
        int reservation_id = data.getIntExtra("id_reservation", 0);


        ApiService apiService = RetrofitInstance.getApi();
        Call<ResponseReservation> call = apiService.deleteReservation(user_id, reservation_id);

        call.enqueue(new Callback<ResponseReservation>() {
            @Override
            public void onResponse(Call<ResponseReservation> call, Response<ResponseReservation> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("TEST", "Deleted");

                    SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(context);
                    sqLiteManager.deleteReservation(reservation_id);

                    Intent intent = new Intent(SupprimerReservation.this, ListeReservation.class);
                    startActivity(intent);
                    finish();

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
        });
    }
}