package com.airtime.logbook_service.persistence.model;

import jakarta.persistence.*;
import com.airtime.logbook_service.web.dto.AtoDTO;

import java.util.Objects;

@Entity
public class Ato {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;

    @Column(name = "inUse")
    private boolean inUse;

    @Column(name = "club_currency")
    private int clubCurrency;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public int getClubCurrency() {
        return clubCurrency;
    }

    public void setClubCurrency(int validity) {
        this.clubCurrency = validity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ato ato)) return false;
        return id == ato.id && inUse == ato.inUse && clubCurrency == ato.clubCurrency && name.equals(ato.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, inUse, clubCurrency);
    }

    public AtoDTO dto() {
        return AtoDTO.builder()
                .id(this.getId())
                .name(this.getName())
                .inUse(this.isInUse())
                .clubCurrency(this.getClubCurrency())
                .build();
    }
}
