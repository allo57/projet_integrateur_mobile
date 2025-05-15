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
