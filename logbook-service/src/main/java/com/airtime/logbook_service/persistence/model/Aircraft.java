package com.airtime.logbook_service.persistence.model;

import jakarta.persistence.*;
import com.airtime.logbook_service.web.dto.AircraftDTO;

@Entity
@Table(name = "AIRCRAFT")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TAIL_NUMBER")
    private String tailNumber;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "type", referencedColumnName = "id")
    private AircraftType aircraftType;

    public Aircraft() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    public AircraftType getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(AircraftType aircraftType) {
        this.aircraftType = aircraftType;
    }

    public AircraftDTO dto() {
        return AircraftDTO.builder()
                .id(this.getId())
                .tailNumber(this.getTailNumber())
                .aircraftType(this.getAircraftType())
                .build();
    }
}
