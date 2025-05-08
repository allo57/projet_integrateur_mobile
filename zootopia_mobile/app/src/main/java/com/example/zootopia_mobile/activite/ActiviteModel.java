package com.example.zootopia_mobile.activite;

public class ActiviteModel {
    private int id_activite;
    private String nom;
    private String description;
    private String date;
    private String heure_debut;
    private String heure_fin;

    private String type;
    private String infrastructure;

    public String getTitre() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getTag(){
        return type;
    }

    public String getInfrastructure() {
        return infrastructure;
    }
}
