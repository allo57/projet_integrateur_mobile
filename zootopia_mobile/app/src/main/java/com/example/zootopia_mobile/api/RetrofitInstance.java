

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

public class RetrofitInstance {
    private static Retrofit retrofit;

    public static ApiService getApi() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8000/api/") // localhost Android
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }
}
