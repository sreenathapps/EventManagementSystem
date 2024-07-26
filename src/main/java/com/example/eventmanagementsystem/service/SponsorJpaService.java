/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * 
 * import java.util.*;
 *
 */

// Write your code here
package com.example.eventmanagementsystem.service;

import java.util.List;

import com.example.eventmanagementsystem.model.Event;
import com.example.eventmanagementsystem.model.Sponsor;
import com.example.eventmanagementsystem.repository.SponsorRepository;

/**
 * SponsorJpaService
 */
public class SponsorJpaService implements SponsorRepository {

    @Override
    public List<Sponsor> getSponsors() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSponsors'");
    }

    @Override
    public Sponsor getSponsorById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSponsorById'");
    }

    @Override
    public Sponsor addSponsor(Sponsor sponsor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addSponsor'");
    }

    @Override
    public void deleteSponsor(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSponsor'");
    }

    @Override
    public Sponsor updateSponsor(int id, Sponsor sponsor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSponsor'");
    }

    @Override
    public List<Event> getSponsorEvents(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSponsorEvent'");
    }

    
}