package com.example.zootopia_mobile.api;

import com.example.zootopia_mobile.activite.ActiviteResponse;
import com.example.zootopia_mobile.animaux.Animal;
import com.example.zootopia_mobile.animaux.ReponseAnimaux;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("activites") //fin de l'url
    Call<ActiviteResponse> getActivites();

    @GET("animaux")
    Call<ReponseAnimaux> getAnimaux();
}


