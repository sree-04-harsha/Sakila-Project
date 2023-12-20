package com.cg.sakila.enitites;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private int cityId; // Assuming PostgreSQL uses SERIAL for auto-incrementing primary keys

    @Column(name = "city", nullable = false)
    @NotEmpty(message = "City name is required")
    @Size(max = 50, message = "City name cannot exceed 50 characters")
	private String city;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @NotNull(message = "Country is required")
    private Country country;

    @Column(name = "last_update", nullable = false)
    @NotNull(message = "Last update timestamp is required")
    private Timestamp lastUpdate;

    public City(int cityId, String city, Country country, Timestamp lastUpdate) {
        super();
        this.cityId = cityId;
        this.city = city;
        this.country = country;
        this.lastUpdate = lastUpdate;
    }

    public City() {
        super();
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "City [cityId=" + cityId + ", city=" + city + ", country=" + country + ", lastUpdate=" + lastUpdate
                + "]";
    }
    
}
