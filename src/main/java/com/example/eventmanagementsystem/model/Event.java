/*
 * You can use the following import statements
 *
 * import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 * 
 * import javax.persistence.*;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.eventmanagementsystem.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Event
 */
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "date")
    private String date;
    @ManyToMany
    @JoinTable(
        name = "event_sponsor",
        joinColumns = @JoinColumn(name = "eventid"),
        inverseJoinColumns = @JoinColumn(name = "sponsorid")
    )
    @JsonIgnoreProperties("events")
    private List<Sponsor> sponsors = new ArrayList<>();

    public Event() {
    }

    public Event(int id, String name, String date, List<Sponsor> sponsors) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.sponsors = sponsors;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Sponsor> getSponsors() {
        return sponsors;
    }

    public void setSponsors(List<Sponsor> sponsors) {
        this.sponsors = sponsors;
    }

}