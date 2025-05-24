/***************************************************
 *
 * Fichier : ActiviteResponse.java
 * Auteur : Sarah-Maude Gagné
 * Fonctionnalité : Réponse de l'api activités
 * Date : 9 mai 2025
 *
 ***************************************************/
package com.example.zootopia_mobile.activite;

import java.util.List;

public class ActiviteResponse {
    private List<ActiviteModel> data;

    public List<ActiviteModel> getActivites() {
        return data;
    }

    public void setData(List<ActiviteModel> data) {
        this.data = data;
    }
}
