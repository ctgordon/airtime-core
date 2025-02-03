package com.airtime.logbook_service.persistence.model;

import com.airtime.logbook_service.web.dto.ProfileDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "PROFILE")
public class Profile {
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uuid", unique = true)
    private UUID id;

    @Column(name = "FORENAME")
    private String forename;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "KNOWN_AS")
    private String knownAs;

    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "USER_ID", columnDefinition = "uuid", unique = true)
    private UUID userId;

    @Column(name = "IN_USE")
    private boolean inUse;

    /*@JsonIgnore
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;*/

    public Profile() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public ProfileDTO dto() {
        return ProfileDTO.builder()
                .id(this.getId())
                .forename(this.getForename())
                .surname(this.getSurname())
                .knownAs(this.getKnownAs())
                .userId(this.getUserId())
                .inUse(this.isInUse())
                .build();
    }
}
