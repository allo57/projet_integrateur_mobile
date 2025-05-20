/***************************************************
 *
 * Fichier : BilletResponse.java
 * Auteur : Samuel Cloutier
 * Fonctionnalité : Code pour chercher la réponse de l'api
 * Date : 8 mai 2025
 *
 ***************************************************/
package com.example.zootopia_mobile.billets;

import com.example.zootopia_mobile.billets.Billet;

import java.util.List;

public class BilletResponse {
    private List<Billet> data;

    public List<Billet> getBillets() {
        return data;
    }

    public void setData(List<Billet> data) {
        this.data = data;
    }
}
