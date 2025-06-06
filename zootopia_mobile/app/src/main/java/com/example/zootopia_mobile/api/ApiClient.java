/***************************************************
 *
 * Fichier : ApiClient.java
 * Auteur : Sarah-Maude Gagné & Heidi Lavoie
 * Fonctionnalité : Gestion API
 * Date : 8 mai 2025
 *
 ***************************************************/

package com.example.zootopia_mobile.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiClient {
    private static final String BASE_URL = "http://10.0.2.2:8000/api/"; // URL pour api
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

