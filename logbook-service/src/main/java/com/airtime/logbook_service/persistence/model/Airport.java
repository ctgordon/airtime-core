package com.airtime.logbook_service.persistence.model;

import jakarta.persistence.*;
import com.airtime.logbook_service.web.dto.AirportDTO;

import java.util.Objects;

@Entity
@Table(name = "airport", schema = "personal_logbook", catalog = "")
public class Airport {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "country_name")
    private String countryName;
    @Basic
    @Column(name = "country_code")
    private String countryCode;
    @Basic
    @Column(name = "airport_name")
    private String airportName;
    @Basic
    @Column(name = "city_name")
    private String cityName;
    @Basic
    @Column(name = "latitude")
    private String latitude;
    @Basic
    @Column(name = "longitude")
    private String longitude;
    @Basic
    @Column(name = "airport_code")
    private String airportCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport that = (Airport) o;
        return id == that.id && Objects.equals(countryName, that.countryName) && Objects.equals(countryCode, that.countryCode) && Objects.equals(airportName, that.airportName) && Objects.equals(cityName, that.cityName) && Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude) && Objects.equals(airportCode, that.airportCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, countryName, countryCode, airportName, cityName, latitude, longitude, airportCode);
    }

    public AirportDTO dto() {
        return AirportDTO.builder()
                .id(this.getId())
                .countryName(this.getCountryName())
                .countryCode(this.getCountryCode())
                .airportName(this.getAirportName())
                .cityName(this.getCityName())
                .latitude(this.getLatitude())
                .longitude(this.getLongitude())
                .airportCode(this.getAirportCode())
                .build();
    }
}
