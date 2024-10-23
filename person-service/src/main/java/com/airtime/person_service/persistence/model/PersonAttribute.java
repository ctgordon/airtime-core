package com.airtime.person_service.persistence.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "person_attribute", schema = "airtime", catalog = "")
public class PersonAttribute {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "person_id", nullable = false, insertable = false, updatable = false)
    private int personId;

    @Basic
    @Column(name = "weight_kg")
    private int weightKg;

    @Basic
    @Column(name = "height_cm")
    private int heightCm;

    public PersonAttribute() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(int weightKg) {
        this.weightKg = weightKg;
    }

    public int getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(int heightCm) {
        this.heightCm = heightCm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonAttribute that = (PersonAttribute) o;
        return id == that.id && personId == that.personId && weightKg == that.weightKg && heightCm == that.heightCm;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personId, weightKg, heightCm);
    }
}
