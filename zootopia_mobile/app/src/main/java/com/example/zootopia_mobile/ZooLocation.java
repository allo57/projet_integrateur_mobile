package com.example.zootopia_mobile;

import static android.content.ContentValues.TAG;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.zootopia_mobile.databinding.ActivityZooLocationBinding;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.android.PolyUtil;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.TravelMode;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ZooLocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityZooLocationBinding binding;
    private final LatLng ZOO_LOCATION = new LatLng(45.403730, -71.894230);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityZooLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.id_map);
        mapFragment.getMapAsync(this);

        ImageButton buttonNav = (ImageButton) findViewById(R.id.imageButtonFermerNav);

        buttonNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZooLocation.this, menuNavigation.class);
                startActivity(intent);
            }
        });


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(ZOO_LOCATION).title("Zootopia"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ZOO_LOCATION, 12));

        LatLng userLocation = new LatLng(45.3753, -71.9268);

        calculateDirections(userLocation, ZOO_LOCATION);
    }

    private void calculateDirections(LatLng origin, LatLng destination) {
        Log.d(TAG, "calculateDirections: calculating directions.");

        // Configuration du contexte de l'API Google
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyDem2ibU_mpZSlVD1RF6Ere9rWOA97VtO8")
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        DirectionsApiRequest directions = DirectionsApi.newRequest(context)
                .mode(TravelMode.DRIVING)
                .origin(new com.google.maps.model.LatLng(origin.latitude, origin.longitude))
                .destination(new com.google.maps.model.LatLng(destination.latitude, destination.longitude));

        try {
            DirectionsResult result = directions.await();

            // Traitement du résultat et affichage de l'itinéraire
            if (result.routes.length > 0) {
                addPolylinesToMap(result);
            }

        } catch (Exception e) {
            Log.e(TAG, "calculateDirections: Erreur de calcul d'itinéraire " + e.getMessage());
        }
    }

    private void addPolylinesToMap(final DirectionsResult result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (DirectionsRoute route : result.routes) {
                    List<LatLng> decodedPath = PolyUtil.decode(route.overviewPolyline.getEncodedPath());

                    PolylineOptions polylineOptions = new PolylineOptions();
                    polylineOptions.color(Color.BLUE);
                    polylineOptions.width(8);

                    for (LatLng latLng : decodedPath) {
                        polylineOptions.add(new LatLng(latLng.latitude, latLng.longitude));
                    }

                    mMap.addPolyline(polylineOptions);
                }
            }
        });
    }
}