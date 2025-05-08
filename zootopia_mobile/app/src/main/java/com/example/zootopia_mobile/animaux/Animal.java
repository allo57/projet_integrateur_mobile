package com.example.zootopia_mobile.animaux;

import com.google.gson.annotations.SerializedName;

public class Animal {
    @SerializedName("nom")
    private String nom;

    @SerializedName("imageUrl")
    private String imageUrl;

    public String getNom() {
        return nom;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

