package com.cg.sakila.enitites;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "actor")
public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "actor_id")
	private int actorId; // Use Long for auto-incremented primary keys in PostgreSQL

	@Column(name = "first_name")
	@NotNull(message = "First name is required")
	@Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
	private String firstName;

	@Column(name = "last_name")
	@NotNull(message = "Last name is required")
	@Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
	private String lastName;

	@Column(name = "last_update")
	private Timestamp lastUpdate;

	@JsonIgnore
	@ManyToMany(mappedBy = "actors")
	private Set<Film> films = new HashSet<>();

	public Actor() {
		super();
	}

	public Set<Film> getFilms() {
		return films;
	}

	public void setFilms(Set<Film> films) {
		this.films = films;
	}

	public int getActorId() {
		return actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Actor [actorId=" + actorId + ", firstName=" + firstName + ", lastName=" + lastName + ", lastUpdate="
				+ lastUpdate + "]";
	}
}
