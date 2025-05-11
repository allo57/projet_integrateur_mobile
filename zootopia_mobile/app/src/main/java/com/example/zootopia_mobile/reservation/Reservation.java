package com.example.zootopia_mobile.reservation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private int id_reservation;
    private String nom;
    private int no_tel;
    private LocalDateTime _date_heure;
    private int nb_personnes;
    private String note;
    private String description;
    private int id_etat_reservation;
    private int id_utilisateur;

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

    public int get_no_tel() {
        return this.no_tel;
    }

    public void set_no_tel(int no_tel) {
        this.no_tel = no_tel;
    }

    public LocalDateTime get_date_heure() {
        return this._date_heure;
    }

    public void set_date_heure(LocalDateTime date_heure) {
        this._date_heure = date_heure;
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
        return this._date_heure.format(objFormat);
    }

    public String get_date() {
        DateTimeFormatter objFormat = DateTimeFormatter.ofPattern("dd MM yyyy");
        return this._date_heure.format(objFormat);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id_reservation=" + id_reservation +
                ", nom='" + nom + '\'' +
                ", no_tel=" + no_tel +
                ", date_heure=" + (_date_heure != null ? _date_heure.toString() : "null") +
                ", nb_personnes=" + nb_personnes +
                ", note='" + note + '\'' +
                ", description='" + description + '\'' +
                ", id_etat_reservation=" + id_etat_reservation +
                ", id_utilisateur=" + id_utilisateur +
                '}';
    }
}
