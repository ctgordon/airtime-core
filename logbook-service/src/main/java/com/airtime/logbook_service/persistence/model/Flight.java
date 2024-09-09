package com.airtime.logbook_service.persistence.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.type.SqlTypes;
import com.airtime.logbook_service.web.dto.FlightDTO;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "UUID", columnDefinition = "uuid", unique = true)
    private UUID uuid;

    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "owner_id", columnDefinition = "uuid", unique = true)
    private UUID ownerId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "aircraft", referencedColumnName = "id")
    private Aircraft aircraft;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "pilot_in_command", referencedColumnName = "id")
    private Person person;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "departure_airport", referencedColumnName = "id")
    private Airport departureAirport;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "arrival_airport", referencedColumnName = "id")
    private Airport arrivalAirport;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "departure_datetime")
    private Timestamp departureDatetime;

    @Column(name = "arrival_datetime")
    private Timestamp arrivalDatetime;

    @Column(name = "take_offs")
    private int takeOffs;

    @Column(name = "landings")
    private int landings;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_date")
    private Timestamp updatedDate;

    @Column(name = "flight_time_minutes")
    private int flightTimeMinutes;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ato", referencedColumnName = "id")
    private Ato ato;

    /*@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner_id", referencedColumnName = "uuid")
    private Person owner;*/

    public Flight() {
    }

    public int getId() {
        return id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Timestamp getDepartureDatetime() {
        return departureDatetime;
    }

    public void setDepartureDatetime(Timestamp departureDatetime) {
        this.departureDatetime = departureDatetime;
    }

    public Timestamp getArrivalDatetime() {
        return arrivalDatetime;
    }

    public void setArrivalDatetime(Timestamp arrivalDatetime) {
        this.arrivalDatetime = arrivalDatetime;
    }

    public int getTakeOffs() {
        return takeOffs;
    }

    public void setTakeOffs(int takeOffs) {
        this.takeOffs = takeOffs;
    }

    public int getLandings() {
        return landings;
    }

    public void setLandings(int landings) {
        this.landings = landings;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getFlightTimeMinutes() {
        return flightTimeMinutes;
    }

    public void setFlightTimeMinutes(int flightTimeMinutes) {
        this.flightTimeMinutes = flightTimeMinutes;
    }

    public Ato getAto() {
        return ato;
    }

    public void setAto(Ato ato) {
        this.ato = ato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id && Objects.equals(aircraft, flight.aircraft) && Objects.equals(person, flight.person) && Objects.equals(departureAirport, flight.departureAirport) && Objects.equals(arrivalAirport, flight.arrivalAirport) && Objects.equals(remarks, flight.remarks) && Objects.equals(departureDatetime, flight.departureDatetime) && Objects.equals(arrivalDatetime, flight.arrivalDatetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, aircraft, person, departureAirport, arrivalAirport, remarks, departureDatetime, arrivalDatetime, createdBy, createdDate, updatedBy, updatedDate, flightTimeMinutes);
    }

    public FlightDTO dto() {
        return FlightDTO.builder()
                .id(this.getId())
                .uuid(this.getUuid())
                .aircraft(this.getAircraft().dto())
                .pilotInCommand(this.getPerson().dto())
                .departureAirport(this.getDepartureAirport().dto())
                .arrivalAirport(this.getArrivalAirport().dto())
                .departureDatetime(this.getDepartureDatetime())
                .arrivalDatetime(this.getArrivalDatetime())
                .remarks(this.getRemarks())
                .takeOffs(this.getTakeOffs())
                .landings(this.getLandings())
                .createdBy(this.createdBy)
                .createdDate(this.createdDate)
                .updatedBy(this.updatedBy)
                .updatedDate(this.updatedDate)
                .flightTimeMinutes(this.flightTimeMinutes)
                .ato(this.ato)
                .build();
    }
}
