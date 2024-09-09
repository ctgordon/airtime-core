package com.airtime.logbook_service.persistence.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Subscriber {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;
    @Basic
    @Column(name = "daily_email")
    private boolean dailyEmail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public boolean isDailyEmail() {
        return dailyEmail;
    }

    public void setDailyEmail(boolean dailyEmail) {
        this.dailyEmail = dailyEmail;
    }

    public Subscriber() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscriber that)) return false;
        return id == that.id && dailyEmail == that.dailyEmail && Objects.equals(person, that.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, person, dailyEmail);
    }
}
