package com.example.zootopia_mobile.reservation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private int _id_reservation;
    private String _nom;
    private int _no_tel;
    private LocalDateTime _date_heure;
    private int _nb_personnes;
    private String _note;
    private String _description;
    private int _id_etat_reservation;
    private int _id_utilisateur;
    public int get_id_reservation() {
        return this._id_reservation;
    }

    public void set_id_reservation(int id_reservation) {
        this._id_reservation = id_reservation;
    }

    public String get_nom() {
        return this._nom;
    }

    public void set_nom(String nom) {
        this._nom = nom;
    }

    public int get_no_tel() {
        return this._no_tel;
    }

    public void set_no_tel(int no_tel) {
        this._no_tel = _no_tel;
    }

    public LocalDateTime get_date_heure() {
        return this._date_heure;
    }

    public void set_date_heure(LocalDateTime date_heure) {
        this._date_heure = date_heure;
    }

    public int get_nb_personnes() {
        return this._nb_personnes;
    }

    public void set_nb_personnes(int nb_personnes) {
        this._nb_personnes = nb_personnes;
    }

    public String get_note() {
        return this._note;
    }

    public void set_note(String note) {
        this._note = note;
    }

    public String get_description() {
        return this._description;
    }

    public void set_description(String description) {
        this._description = description;
    }

    public int get_id_etat_reservation() {
        return this._id_etat_reservation;
    }

    public void set_id_etat_reservation(int id_etat_reservation) {
        this._id_etat_reservation = id_etat_reservation;
    }

    public int get_id_utilisateur() {
        return this._id_utilisateur;
    }

    public void set_id_utilisateur(int id_utilisateur) {
        this._id_utilisateur = id_utilisateur;
    }

    public String get_heure() {
        DateTimeFormatter objFormat = DateTimeFormatter.ofPattern("HH:mm");
        String heure = this._date_heure.format(objFormat);
        return heure;
    }
    public String get_date() {
        DateTimeFormatter objFormat = DateTimeFormatter.ofPattern("dd MM yyyy");
        String date = this._date_heure.format(objFormat);
        return date;
    }
}