package com.example.zootopia_mobile;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class menuNavigation extends AppCompatActivity implements View.OnClickListener {

    private ImageButton buttonFermerNav;
    private ImageButton imageButtonNotifNav;
    private ImageButton imageButtonCalenActivit;
    private ImageButton imageButtonAnimaux;
    private ImageButton imageButtonBilletterie;
    private ImageButton imageButtonAPropos;
    private ImageButton imageButtonLocalisation;
    private Button buttonInscription;
    private Button buttonSeConnecter;
    private Button buttonAIdeEnLigne;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_navigation);

        //Différents buttons
        buttonFermerNav = (ImageButton) findViewById(R.id.imageButtonFermerNav);
        imageButtonLocalisation = (ImageButton) findViewById(R.id.imageButtonLocalisation);

        //On set le OnClickListener
        buttonFermerNav.setOnClickListener(this);
        imageButtonLocalisation.setOnClickListener(this);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.imageButtonFermerNav){
            Intent intent = new Intent(menuNavigation.this, MainActivity.class);
            startActivity(intent);
        } else if (v.getId()==R.id.imageButtonLocalisation) {
            Intent intent = new Intent(menuNavigation.this, ZooLocation.class);
            startActivity(intent);
        }
    }
}