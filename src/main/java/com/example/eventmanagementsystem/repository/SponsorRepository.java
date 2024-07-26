/*
 * You can use the following import statements
 *
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.eventmanagementsystem.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.eventmanagementsystem.model.Event;
import com.example.eventmanagementsystem.model.Sponsor;

/**
 * SponsorRepository
 */
@Repository
public interface SponsorRepository {
    List<Sponsor> getSponsors();

    Sponsor getSponsorById(int id);

    Sponsor addSponsor(Sponsor sponsor);

    void deleteSponsor(int id);

    Sponsor updateSponsor(int id, Sponsor sponsor);

    List<Event> getSponsorEvents(int id);
}