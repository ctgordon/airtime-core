package com.airtime.person_service.persistence.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Membership {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "person_id", nullable = false, insertable = false, updatable = false)
    private int personId;
    @Basic
    @Column(name = "membership_start", nullable = false)
    private Timestamp membershipStart;
    @Basic
    @Column(name = "membership_end", nullable = false)
    private Timestamp membershipEnd;
    @Basic
    @Column(name = "created", nullable = false)
    private Timestamp created;
    @Basic
    @Column(name = "created_by", nullable = false)
    private int createdBy;
    @Basic
    @Column(name = "updated", nullable = false)
    private Timestamp updated;
    @Basic
    @Column(name = "updated_by", nullable = false)
    private int updatedBy;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "membership_body", referencedColumnName = "id")
    private MembershipBody membershipBody;

    @Basic
    @Column(name = "description")
    private String description;

    @Column(name = "in_use")
    private boolean inUse;

    public Membership() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public Timestamp getMembershipStart() {
        return membershipStart;
    }

    public void setMembershipStart(Timestamp membershipStart) {
        this.membershipStart = membershipStart;
    }

    public Timestamp getMembershipEnd() {
        return membershipEnd;
    }

    public void setMembershipEnd(Timestamp membershipEnd) {
        this.membershipEnd = membershipEnd;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public MembershipBody getMembershipBody() {
        return membershipBody;
    }

    public void setMembershipBody(MembershipBody membershipBody) {
        this.membershipBody = membershipBody;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Membership that = (Membership) o;
        return id == that.id && personId == that.personId && createdBy == that.createdBy && updatedBy == that.updatedBy && Objects.equals(membershipStart, that.membershipStart) && Objects.equals(membershipEnd, that.membershipEnd) && Objects.equals(created, that.created) && Objects.equals(updated, that.updated) && Objects.equals(membershipBody, that.membershipBody) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personId, membershipStart, membershipEnd, created, createdBy, updated, updatedBy, membershipBody, description);
    }
}
