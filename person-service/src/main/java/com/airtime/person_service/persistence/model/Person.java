package com.airtime.person_service.persistence.model;

import com.airtime.person_service.web.dto.PersonDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "UUID", columnDefinition = "uuid", unique = true)
    private UUID uuid;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MONIKER")
    private String moniker;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role", referencedColumnName = "id")
    private PersonRole personRole;

    /*@OneToMany(mappedBy = "person")
    private List<PersonAttribute> personAttributes;*/

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id", referencedColumnName = "person_id")
    private PersonAttribute personAttribute;

    /*@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id", referencedColumnName = "person_id")
    private Membership membership;*/

    @OneToMany
    @JoinColumn(name = "person_id")
    private List<Membership> memberships;


    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "auth_email_address")
    private String authEmailAddress;

    @Column(name = "auth_user_id")
    private String authUserId;

    @OneToMany
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private List<Licence> licences;

    public Person() {
    }

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

    public String getMoniker() {
        return moniker;
    }

    public void setMoniker(String moniker) {
        this.moniker = moniker;
    }

    public PersonRole getPersonRole() {
        return personRole;
    }

    public void setPersonRole(PersonRole personRole) {
        this.personRole = personRole;
    }

    /*public List<PersonAttribute> getPersonAttributes() {
        return personAttributes;
    }

    public void setPersonAttributes(List<PersonAttribute> personAttributes) {
        this.personAttributes = personAttributes;
    }*/

    public PersonAttribute getPersonAttribute() {
        return personAttribute;
    }

    public void setPersonAttribute(PersonAttribute personAttribute) {
        this.personAttribute = personAttribute;
    }

    public List<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<Membership> memberships) {
        this.memberships = memberships;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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

    public List<Licence> getLicences() {
        return licences;
    }

    public void setLicences(List<Licence> licences) {
        this.licences = licences;
    }

    public PersonDTO dto() {
        return PersonDTO.builder()
                .id(this.getId())
                .name(this.getName())
                .moniker(this.getMoniker())
                .personRole(this.getPersonRole())
                .personAttribute(this.getPersonAttribute())
                .memberships(this.getMemberships())
                .uuid(this.getUuid())
                .emailAddress(this.getEmailAddress())
                .authEmailAddress(this.getAuthEmailAddress())
                .licences(this.getLicences())
                .build();
    }
}
