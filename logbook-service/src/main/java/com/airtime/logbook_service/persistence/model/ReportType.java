package com.airtime.logbook_service.persistence.model;

import jakarta.persistence.*;
import com.airtime.logbook_service.web.dto.ReportTypeDTO;

import java.util.Objects;

@Entity
@Table(name = "report_type", schema = "airtime", catalog = "")
public class ReportType {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 2000)
    private String name;

    @Basic
    @Column(name = "in_use", nullable = false, length = 2000)
    private boolean inUse;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportType that = (ReportType) o;
        return id == that.id && inUse == that.inUse && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, inUse);
    }

    public ReportTypeDTO dto() {
        return ReportTypeDTO.builder()
                .id(id)
                .name(name)
                .inUse(inUse)
                .build();
    }
}
