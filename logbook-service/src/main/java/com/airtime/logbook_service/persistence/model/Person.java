package com.airtime.logbook_service.persistence.model;

import com.airtime.logbook_service.web.dto.PersonDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "PERSON")
public class Person {
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uuid", unique = true)
    private UUID id;

    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "APP_USER_ID", columnDefinition = "uuid", unique = true)
    private UUID appUserId;

    @Column(name = "FORENAME")
    private String forename;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "KNOWN_AS")
    private String knownAs;

    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "USER_ID")
    private UUID userId;

    public Person() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(UUID appUserId) {
        this.appUserId = appUserId;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getKnownAs() {
        return knownAs;
    }

    public void setKnownAs(String knownAs) {
        this.knownAs = knownAs;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public PersonDTO dto() {
        return PersonDTO.builder()
                .id(this.getId())
                .forename(this.getForename())
                .surname(this.getSurname())
                .knownAs(this.getKnownAs())
                .appUserId(this.getAppUserId())
                .build();
    }
}
