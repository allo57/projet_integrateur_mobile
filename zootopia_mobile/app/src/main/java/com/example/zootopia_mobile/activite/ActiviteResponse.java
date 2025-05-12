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
