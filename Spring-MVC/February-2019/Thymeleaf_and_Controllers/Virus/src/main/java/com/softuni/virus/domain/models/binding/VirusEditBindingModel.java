package com.softuni.virus.domain.models.binding;

import com.softuni.virus.domain.enums.Creator;
import com.softuni.virus.domain.enums.Magnitude;
import com.softuni.virus.domain.enums.VirusMutation;
import com.softuni.virus.util.annotations.ReleaseDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

public class VirusEditBindingModel {

    private String id;

    @NotNull
    @NotEmpty(message = "Name cannot be empty.")
    @Size(min = 3, max = 10, message = "Name must be between 3 and 10 symbols.")
    private String name;

    @NotNull
    @NotEmpty(message = "Description cannot be empty.")
    @Size(min = 5, max = 100, message = "Description must be between 5 and 100 symbols.")
    private String description;

    @Size(max = 50, message = "Side effects cannot be more than 50 symbols.")
    private String sideEffects;

    @NotNull(message = "Creator cannot be empty.")
    private Creator creator;

    private Boolean isDeadly;

    private Boolean isCurable;

    @NotNull(message = "Mutation cannot be null.")
    private VirusMutation mutation;

    @NotNull(message = "Turnover rate cannot be null.")
    @Min(value = 0, message = "Turnover rate cannot be below 0.")
    @Max(value = 100, message = "Turnover rate cannot be more than 100.")
    private Double turnoverRate;

    @NotNull(message = "Hours until turn cannot be null.")
    @Min(value = 1, message = "Hours until turn cannot be below 1.")
    @Max(value = 12, message = "Hours until turn rate cannot be more than 12.")
    private Integer hoursUntilTurn;

    @NotNull(message = "Magnitude cannot be null.")
    private Magnitude magnitude;

    @NotNull(message = "Released on cannot be null.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ReleaseDate
    private Date releasedOn;

    @NotNull(message = "You must select capitals.")
    private List<String> capitals;

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

    public List<String> getCapitals() {
        return capitals;
    }

    public void setCapitals(List<String> capitals) {
        this.capitals = capitals;
    }
}
