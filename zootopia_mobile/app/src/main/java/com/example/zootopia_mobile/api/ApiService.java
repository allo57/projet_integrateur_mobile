package com.example.zootopia_mobile.api;

import com.example.zootopia_mobile.activite.ActiviteResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("activites") //fin de l'url
    Call<ActiviteResponse> getActivites();
}


