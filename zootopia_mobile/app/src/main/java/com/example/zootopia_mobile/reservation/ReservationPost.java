/***************************************************
 *
 * Fichier : ReservationPost
 * Auteur : Jacob Perreault
 * Fonctionnalité : Classe "clone" de la classe Réservation qui permet de faire les Post vers l'api. Parce que les nom de réception sont pas identique que lors des Get
 * Date : 9 mai 2025
 *
 ***************************************************/
package com.example.zootopia_mobile.reservation;

public class ReservationPost {
    int id_reservation;
    String name;
    String telephone;
    int nb_personnes;
    String date;
    String heure;
    String description;
    String note;
    int statut;
    int id_utilisateur;

    public ReservationPost(String nom, String no_tel, int nb_personnes, String date, String heure, String note, int id_user) {
        setName(nom);
        setTelephone(no_tel);
        setNb_personnes(nb_personnes);
        setDate(date);
        setHeure(heure);
        setNote(note);
        setStatut(1);
        setId_utilisateur(id_user);
        setDescription("");
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getNb_personnes() {
        return nb_personnes;
    }

    public void setNb_personnes(int nb_personnes) {
        this.nb_personnes = nb_personnes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }
}
