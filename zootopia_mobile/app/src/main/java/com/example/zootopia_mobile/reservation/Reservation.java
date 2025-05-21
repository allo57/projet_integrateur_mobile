/***************************************************
 *
 * Fichier : Reservation
 * Auteur : Jacob Perreault
 * Fonctionnalité : Classe qui permet la gestion d'une réservation
 * Date : 9 mai 2025
 *
 ***************************************************/
package com.example.zootopia_mobile.reservation;

import android.util.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Reservation {
    private int id_reservation;
    private String nom;
    private long no_tel;
    private String date_heure;
    private int nb_personnes;
    private String note;
    private String description;
    private int id_etat_reservation;
    private int id_utilisateur;

    public static ArrayList<Reservation> reservationArrayList = new ArrayList<>();

    public Reservation() {
        this.id_reservation = 0;
        this.nom = "";
        this.no_tel = 0;
        this.date_heure = "";
        this.nb_personnes = 0;
        this.note = "";
        this.description = "";
        this.id_etat_reservation = 0;
        this.id_utilisateur = 0;
    }

    // Pour post
    public Reservation (String nom, long no_tel, int nb_personnes, String date, String heure, String note, int id_user) {
        set_nom(nom);
        set_no_tel(no_tel);
        set_nb_personnes(nb_personnes);
        set_date_heure(date + " " + heure);
        set_note(note);
        set_id_utilisateur(id_user);
    }

    public int get_id_reservation() {
        return this.id_reservation;
    }

    public void set_id_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public String get_nom() {
        return this.nom;
    }

    public void set_nom(String nom) {
        this.nom = nom;
    }

    public long get_no_tel() {
        return this.no_tel;
    }

    public void set_no_tel(long no_tel) {
        this.no_tel = no_tel;
    }

    public String get_date_heure() {
        return this.date_heure;
    }

    public void set_date_heure(String date_heure) {
        this.date_heure = date_heure;
    }

    public int get_nb_personnes() {
        return this.nb_personnes;
    }

    public void set_nb_personnes(int nb_personnes) {
        this.nb_personnes = nb_personnes;
    }

    public String get_note() {
        return this.note;
    }

    public void set_note(String note) {
        this.note = note;
    }

    public String get_description() {
        return this.description;
    }

    public void set_description(String description) {
        this.description = description;
    }

    public int get_id_etat_reservation() {
        return this.id_etat_reservation;
    }

    public void set_id_etat_reservation(int id_etat_reservation) {
        this.id_etat_reservation = id_etat_reservation;
    }

    public int get_id_utilisateur() {
        return this.id_utilisateur;
    }

    public void set_id_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String get_heure() {
        DateTimeFormatter objFormat = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter currentFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date_heure, currentFormat);
        return dateTime.format(objFormat).toString();
    }

    public String get_date() {
        DateTimeFormatter objFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter currentFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date_heure, currentFormat);
        return dateTime.format(objFormat).toString();
    }

    public boolean equals(Reservation r2) {
        return this.get_nom() == r2.get_nom() &&
        this.get_no_tel() == r2.get_no_tel() &&
        this.get_date_heure() == r2.get_date_heure() &&
        this.get_nb_personnes() == r2.get_nb_personnes() &&
        this.get_note() == r2.get_note() &&
        this.get_description() == r2.get_description() &&
        this.get_id_etat_reservation() == r2.get_id_etat_reservation();
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id_reservation=" + id_reservation +
                ", nom='" + nom + '\'' +
                ", no_tel=" + no_tel +
                ", date_heure=" + get_date_heure() +
                ", nb_personnes=" + nb_personnes +
                ", note='" + note + '\'' +
                ", description='" + description + '\'' +
                ", id_etat_reservation=" + id_etat_reservation +
                ", id_utilisateur=" + id_utilisateur +
                '}';
    }
}
