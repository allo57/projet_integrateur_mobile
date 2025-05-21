/***************************************************
 *
 * Fichier : ResponseReservation
 * Auteur : Jacob Perreault
 * Fonctionnalité : Reçois la réponse de de l'api suite à une requête
 * Date : 9 mai 2025
 *
 ***************************************************/
package com.example.zootopia_mobile.reservation;


import java.util.List;
public class ResponseReservation {
    private List<Reservation> data;

    public List<Reservation> getUserReservations() { return data; }

    public void setData(List<Reservation> data) {
        this.data = data;
    }
}
