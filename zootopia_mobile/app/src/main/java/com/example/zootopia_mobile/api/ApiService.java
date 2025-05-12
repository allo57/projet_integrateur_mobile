package com.example.zootopia_mobile.api;

import com.example.zootopia_mobile.activite.ActiviteResponse;
import com.example.zootopia_mobile.animaux.Animal;
import com.example.zootopia_mobile.animaux.ReponseAnimaux;
import com.example.zootopia_mobile.reservation.Reservation;
import com.example.zootopia_mobile.reservation.ResponseReservation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("activites") //fin de l'url
    Call<ActiviteResponse> getActivites();

    @GET("animaux")
    Call<ReponseAnimaux> getAnimaux();

    @GET("reservation/utilisateur/{id}")
    Call<ResponseReservation> getUserReservations(@Path("id") String id);

    @POST("ajoutReservation")
    Call<ResponseReservation> addReservation(@Body Reservation post);

    @POST("modifierReservation")
    Call<ResponseReservation> editReservation(@Body Reservation post);

    @GET("supprimerReservation/{user_id}&{reservation_id}")
    Call<ResponseReservation> deleteReservation(@Path("user_id") int user_id, @Path("reservation_id") int reservation_id);

    //@GET("/billets")
    //Call<> getBillets();
}


