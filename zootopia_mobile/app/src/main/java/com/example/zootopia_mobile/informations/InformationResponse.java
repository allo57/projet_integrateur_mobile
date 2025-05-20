/***************************************************
 *
 * Fichier : InformationResponse.java
 * Auteur : Samuel Cloutier
 * Fonctionnalité : Code pour aller chercher la réponse de l'api d'Information
 * Date : 8 mai 2025
 *
 ***************************************************/
package com.example.zootopia_mobile.informations;

import com.example.zootopia_mobile.informations.Information;

import java.util.List;

public class InformationResponse {

    private List<Information> data;

    public List<Information> getInfos() {
        return data;
    }

    public void setData(List<Information> data) {
        this.data = data;
    }
}
