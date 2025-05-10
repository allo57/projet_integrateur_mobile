package com.example.zootopia_mobile.reservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zootopia_mobile.R;

public class ListeReservation extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_liste_reservation);
        String reservation[] = {"test", "test2", "test3"};

        recyclerView = (RecyclerView) findViewById(R.id.liste_reservation);
        RecyclerReservation adapter = new RecyclerReservation(this, reservation);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AppCompatButton ajouter = findViewById(R.id.ajouter);
        ajouter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ListeReservation.this, AjouterReservation.class);
        startActivity(intent);
    }
}