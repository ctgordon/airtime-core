package com.airtime.logbook_service.persistence.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import com.airtime.logbook_service.web.dto.PersonDTO;

import java.util.UUID;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "APP_USER_ID", columnDefinition = "uuid", unique = true)
    private UUID appUserId;

    @Column(name = "FORENAME")
    private String forename;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "KNOWN_AS")
    private String knownAs;

    /*@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "APP_ROLE", referencedColumnName = "id")
    private PersonRole personRole;*/

    /*@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "app_user_id", name = "app_user_id")
    private PersonAttribute personAttribute;*/

    @Column(name = "APP_EMAIL_ADDRESS")
    private String appEmailAddress;

    @Column(name = "auth_email_address")
    private String authEmailAddress;

    @Column(name = "auth_user_id")
    private String authUserId;

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String name) {
        this.forename = name;
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

    /*public PersonAttribute getPersonAttribute() {
        return personAttribute;
    }

    public void setPersonAttribute(PersonAttribute personAttribute) {
        this.personAttribute = personAttribute;
    }*/

    public UUID getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(UUID uuid) {
        this.appUserId = uuid;
    }

    public String getAppEmailAddress() {
        return appEmailAddress;
    }

    public void setAppEmailAddress(String emailAddress) {
        this.appEmailAddress = emailAddress;
    }

    public String getAuthEmailAddress() {
        return authEmailAddress;
    }

    public void setAuthEmailAddress(String authEmailAddress) {
        this.authEmailAddress = authEmailAddress;
    }

    public String getAuthUserId() {
        return authUserId;
    }

    public void setAuthUserId(String authUserId) {
        this.authUserId = authUserId;
    }

    public PersonDTO dto() {
        return PersonDTO.builder()
                .id(this.getId())
                .forename(this.getForename())
                .surname(this.getSurname())
                .knownAs(this.getKnownAs())
                .appUserId(this.getAppUserId())
                .appEmailAddress(this.getAppEmailAddress())
                .authEmailAddress(this.getAuthEmailAddress())
                .build();
    }
}
