/***************************************************
 *
 * Fichier : BilletPanier.java
 * Auteur : Sarah-Maude Gagné
 * Fonctionnalité : modèle des billets du panier
 * Date : 18 mai 2025
 *
 ***************************************************/
package com.example.zootopia_mobile.magasin;

import com.example.zootopia_mobile.billets.Billet;

public class BilletPanier {
    private Billet billet;
    private int quantite;

    public BilletPanier(Billet billet, int quantite) {
        this.billet = billet;
        this.quantite = quantite;
    }

    public Billet getBillet() {
        return billet;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}

