package com.softuni.virus.domain.models.service;

import com.softuni.virus.domain.entities.Capital;
import com.softuni.virus.domain.enums.Creator;
import com.softuni.virus.domain.enums.Magnitude;
import com.softuni.virus.domain.enums.VirusMutation;

import java.util.Date;
import java.util.List;

public class VirusServiceModel {

    private String id;

    private String name;

    private String description;

    private String sideEffects;

    private Creator creator;

    private Boolean isDeadly;

    private Boolean isCurable;

    private VirusMutation mutation;

    private Double turnoverRate;

    private Integer hoursUntilTurn;

    private Magnitude magnitude;

    private Date releasedOn;

    private List<CapitalServiceModel> capitals;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public Boolean getDeadly() {
        return isDeadly;
    }

    public void setDeadly(Boolean deadly) {
        isDeadly = deadly;
    }

    public Boolean getCurable() {
        return isCurable;
    }

    public void setCurable(Boolean curable) {
        isCurable = curable;
    }

    public VirusMutation getMutation() {
        return mutation;
    }

    public void setMutation(VirusMutation mutation) {
        this.mutation = mutation;
    }

    public Double getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Double turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Integer getHoursUntilTurn() {
        return hoursUntilTurn;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    public Magnitude getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    public Date getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(Date releasedOn) {
        this.releasedOn = releasedOn;
    }

    public List<CapitalServiceModel> getCapitals() {
        return capitals;
    }

    public void setCapitals(List<CapitalServiceModel> capitals) {
        this.capitals = capitals;
    }
}
