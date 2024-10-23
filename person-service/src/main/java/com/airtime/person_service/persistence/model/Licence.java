package com.airtime.person_service.persistence.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
public class Licence {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "date_of_initial_issue")
    private Date dateOfInitialIssue;
    @Basic
    @Column(name = "country_code")
    private String countryCode;

    @Basic
    @Column(name = "remarks_and_restrictions")
    private String remarksAndRestrictions;
    @Basic
    @Column(name = "state_of_issue")
    private String stateOfIssue;
    @Basic
    @Column(name = "licence_number")
    private String licenceNumber;
    @Basic
    @Column(name = "owner_id")
    private int ownerId;

    @OneToMany
    @JoinColumn(name = "licence_id", referencedColumnName = "id")
    private List<CertificateOfRevalidation> certificatesOfRevalidation;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "type", referencedColumnName = "id")
    private RatingType ratingType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateOfInitialIssue() {
        return dateOfInitialIssue;
    }

    public void setDateOfInitialIssue(Date dateOfInitialIssue) {
        this.dateOfInitialIssue = dateOfInitialIssue;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRemarksAndRestrictions() {
        return remarksAndRestrictions;
    }

    public void setRemarksAndRestrictions(String remarksAndRestrictions) {
        this.remarksAndRestrictions = remarksAndRestrictions;
    }

    public String getStateOfIssue() {
        return stateOfIssue;
    }

    public void setStateOfIssue(String stateOfIssue) {
        this.stateOfIssue = stateOfIssue;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public List<CertificateOfRevalidation> getCertificatesOfRevalidation() {
        return certificatesOfRevalidation;
    }

    public void setCertificatesOfRevalidation(List<CertificateOfRevalidation> certificatesOfRevalidation) {
        this.certificatesOfRevalidation = certificatesOfRevalidation;
    }

    public RatingType getRatingType() {
        return ratingType;
    }

    public void setRatingType(RatingType ratingType) {
        this.ratingType = ratingType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Licence licence = (Licence) o;

        if (id != licence.id) return false;
        if (ownerId != licence.ownerId) return false;
        if (title != null ? !title.equals(licence.title) : licence.title != null) return false;
        if (dateOfInitialIssue != null ? !dateOfInitialIssue.equals(licence.dateOfInitialIssue) : licence.dateOfInitialIssue != null)
            return false;
        if (countryCode != null ? !countryCode.equals(licence.countryCode) : licence.countryCode != null) return false;
        if (remarksAndRestrictions != null ? !remarksAndRestrictions.equals(licence.remarksAndRestrictions) : licence.remarksAndRestrictions != null)
            return false;
        if (stateOfIssue != null ? !stateOfIssue.equals(licence.stateOfIssue) : licence.stateOfIssue != null)
            return false;
        if (licenceNumber != null ? !licenceNumber.equals(licence.licenceNumber) : licence.licenceNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (dateOfInitialIssue != null ? dateOfInitialIssue.hashCode() : 0);
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        result = 31 * result + (remarksAndRestrictions != null ? remarksAndRestrictions.hashCode() : 0);
        result = 31 * result + (stateOfIssue != null ? stateOfIssue.hashCode() : 0);
        result = 31 * result + (licenceNumber != null ? licenceNumber.hashCode() : 0);
        result = 31 * result + ownerId;
        return result;
    }
}
