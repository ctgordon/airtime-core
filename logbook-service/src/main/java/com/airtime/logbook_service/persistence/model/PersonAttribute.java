package com.airtime.logbook_service.persistence.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "person_attribute", schema = "airtime", catalog = "")
public class PersonAttribute {
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "APP_USER_ID", columnDefinition = "uuid", unique = true)
    private UUID appUserId;

    @Basic
    @Column(name = "weight_kg")
    private int weightKg;

    @Basic
    @Column(name = "height_cm")
    private int heightCm;

    public PersonAttribute() {
    }

    /*public UUID getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(UUID appUserId) {
        this.appUserId = appUserId;
    }*/

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

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonAttribute that)) return false;
        return id == that.id && weightKg == that.weightKg && heightCm == that.heightCm && Objects.equals(appUserId, that.appUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appUserId, weightKg, heightCm);
    }*/
}
