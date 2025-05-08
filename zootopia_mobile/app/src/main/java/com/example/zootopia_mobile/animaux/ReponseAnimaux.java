package com.example.zootopia_mobile.animaux;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ReponseAnimaux {
    @SerializedName("data")
    private List<Animal> data;

    public List<Animal> getData() {
        return data;
    }

    public void setData(List<Animal> data) {
        this.data = data;
    }
}
