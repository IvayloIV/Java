package com.softuni.virus.domain.models.service;

import com.softuni.virus.domain.entities.Virus;

import java.util.List;

public class CapitalServiceModel {

    private String id;

    private String name;

    private Double latitude;

    private Double longitude;

    private List<Virus> viruses;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<Virus> getViruses() {
        return viruses;
    }

    public void setViruses(List<Virus> viruses) {
        this.viruses = viruses;
    }
}
