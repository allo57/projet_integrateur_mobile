/***************************************************
 *
 * Fichier : Billet.java
 * Auteur : Samuel Cloutier
 * Fonctionnalité : Code de l'objet Billet
 * Date : 8 mai 2025
 *
 ***************************************************/
package com.example.zootopia_mobile.billets;

public class Billet {
    private int id_billet;
    private String nom;
    private String description;
    private double prix;

    public Billet(int id, String nom, String description, double prix) {
        this.id_billet = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
    }

    public int getId_billet() {
        return id_billet;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public double getPrix() {
        return prix;
    }
}
