/***************************************************
 *
 * Fichier : Animal.java
 * Auteur : Heidi Lavoie
 * Fonctionnalité : Code de la classe Animal
 * Date : 8 mai 2025
 *
 ***************************************************/

package com.example.zootopia_mobile.animaux;

public class Animal {
    private int id_animal;
    private String nom;
    private String description;
    private String date_naissance;
    private String etat;
    private String etat_desc;
    private String image;

    public int getId_animal() {
        return id_animal;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public String getEtat() {
        return etat;
    }

    public String getEtatDesc() {
        return etat_desc;
    }

    public String getImage() {
        return image;
    }
}


