package com.airtime.logbook_service.persistence.model;

import com.airtime.logbook_service.web.dto.AircraftTypeDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "aircraft_type")
public class AircraftType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "type", nullable = false, length = 100)
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AircraftType that = (AircraftType) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    public AircraftTypeDTO dto() {
        return AircraftTypeDTO.builder().id(this.getId()).type(this.getType()).build();
    }
}
