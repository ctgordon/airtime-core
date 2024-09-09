package com.airtime.logbook_service.persistence.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "certificate_of_revalidation", schema = "airtime", catalog = "")
public class CertificateOfRevalidation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "licence_id")
    private int licenceId;

    /*@Basic
    @Column(name = "rating_type")
    private int ratingType;*/

    @Basic
    @Column(name = "date_of_rating_test")
    private Date dateOfRatingTest;
    @Basic
    @Column(name = "date_of_ir_test")
    private Date dateOfIrTest;
    @Basic
    @Column(name = "valid_until")
    private Date validUntil;

    /*@Basic
    @Column(name = "examiner")
    private int examiner;*/

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "examiner", referencedColumnName = "id")
    private Person examiner;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "rating_type", referencedColumnName = "id")
    private RatingType ratingType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLicenceId() {
        return licenceId;
    }

    public void setLicenceId(int licenceId) {
        this.licenceId = licenceId;
    }

    public Date getDateOfRatingTest() {
        return dateOfRatingTest;
    }

    public void setDateOfRatingTest(Date dateOfRatingTest) {
        this.dateOfRatingTest = dateOfRatingTest;
    }

    public Date getDateOfIrTest() {
        return dateOfIrTest;
    }

    public void setDateOfIrTest(Date dateOfIrTest) {
        this.dateOfIrTest = dateOfIrTest;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public Person getExaminer() {
        return examiner;
    }

    public void setExaminer(Person person) {
        this.examiner = person;
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

        CertificateOfRevalidation that = (CertificateOfRevalidation) o;

        if (id != that.id) return false;
        if (licenceId != that.licenceId) return false;
        if (dateOfRatingTest != null ? !dateOfRatingTest.equals(that.dateOfRatingTest) : that.dateOfRatingTest != null)
            return false;
        if (dateOfIrTest != null ? !dateOfIrTest.equals(that.dateOfIrTest) : that.dateOfIrTest != null) return false;
        if (validUntil != null ? !validUntil.equals(that.validUntil) : that.validUntil != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + licenceId;
        result = 31 * result + (dateOfRatingTest != null ? dateOfRatingTest.hashCode() : 0);
        result = 31 * result + (dateOfIrTest != null ? dateOfIrTest.hashCode() : 0);
        result = 31 * result + (validUntil != null ? validUntil.hashCode() : 0);
        return result;
    }
}
