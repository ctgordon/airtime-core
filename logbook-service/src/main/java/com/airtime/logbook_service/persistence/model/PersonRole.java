package com.airtime.logbook_service.persistence.model;

import jakarta.persistence.*;
import com.airtime.logbook_service.web.dto.PersonRoleDTO;

import java.util.Objects;

@Entity
@Table(name = "PERSON_ROLE")
public class PersonRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ROLE")
    private String role;

    public PersonRole() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public PersonRoleDTO dto() {
        return PersonRoleDTO.builder()
                .id(this.getId())
                .role(this.getRole())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonRole that = (PersonRole) o;
        return id == that.id && role.equals(that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }
}
