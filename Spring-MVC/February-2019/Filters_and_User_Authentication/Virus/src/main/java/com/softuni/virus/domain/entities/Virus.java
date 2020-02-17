package com.softuni.virus.domain.entities;

import com.softuni.virus.domain.enums.Creator;
import com.softuni.virus.domain.enums.Magnitude;
import com.softuni.virus.domain.enums.VirusMutation;
import com.softuni.virus.util.annotations.ReleaseDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "virus")
public class Virus extends BaseEntity {

    @NotNull
    @NotEmpty(message = "Name cannot be empty.")
    @Size(min = 3, max = 10, message = "Name must be between 3 and 10 symbols.")
    @Column(name = "name")
    private String name;

    @NotNull
    @NotEmpty(message = "Description cannot be empty.")
    @Size(min = 5, max = 100, message = "Description must be between 5 and 100 symbols.")
    @Column(name = "description")
    private String description;

    @Size(max = 50, message = "Side effects cannot be more than 50 symbols.")
    @Column(name = "side_effects")
    private String sideEffects;

    @NotNull(message = "Creator cannot be empty.")
    @Enumerated(EnumType.STRING)
    @Column(name = "creator")
    private Creator creator;

    @Column(name = "is_deadly")
    private Boolean isDeadly;

    @Column(name = "is_curable")
    private Boolean isCurable;

    @NotNull(message = "Mutation cannot be null.")
    @Enumerated(EnumType.STRING)
    @Column(name = "mutation")
    private VirusMutation mutation;

    @NotNull
    @Min(value = 0, message = "Turnover rate cannot be below 0.")
    @Max(value = 100, message = "Turnover rate cannot be more than 100.")
    @Column(name = "turnover_rate")
    private Double turnoverRate;

    @NotNull
    @Min(value = 1, message = "Hours until turn cannot be below 1.")
    @Max(value = 12, message = "Hours until turn rate cannot be more than 12.")
    @Column(name = "hours_until_turn")
    private Integer hoursUntilTurn;

    @NotNull(message = "Magnitude cannot be null.")
    @Enumerated(EnumType.STRING)
    @Column(name = "magnitude")
    private Magnitude magnitude;

    @NotNull(message = "Released on cannot be null.")
    @Column(name = "released_on")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ReleaseDate
    private Date releasedOn;

    @NotNull(message = "You must select capitals.")
    @ManyToMany(targetEntity = Capital.class)
    @JoinTable(name = "viruses_capitals",
                joinColumns = @JoinColumn(name = "virus_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "capital_id", referencedColumnName = "id"))
    private List<Capital> capitals;

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

    public List<Capital> getCapitals() {
        return capitals;
    }

    public void setCapitals(List<Capital> capitals) {
        this.capitals = capitals;
    }
}
