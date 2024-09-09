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

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.eventmanagementsystem.model.Event;
import com.example.eventmanagementsystem.model.Sponsor;
import com.example.eventmanagementsystem.repository.EventJpaRepository;
import com.example.eventmanagementsystem.repository.SponsorJpaRepository;
import com.example.eventmanagementsystem.repository.SponsorRepository;

/**
 * SponsorJpaService
 */
@Service
public class SponsorJpaService implements SponsorRepository {
    @Autowired
    EventJpaRepository eventJpaRepository;
    @Autowired
    SponsorJpaRepository sponsorJpaRepository;

    @Override
    public List<Sponsor> getSponsors() {
        return sponsorJpaRepository.findAll();
    }

    @Override
    public Sponsor getSponsorById(int id) {
        try {
            return sponsorJpaRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Sponsor addSponsor(Sponsor sponsor) {
        try {
            List<Integer> eventIds = new ArrayList<>();
            for(Event event: sponsor.getEvents()) eventIds.add(event.getEventId());

            List<Event> completeEvents = eventJpaRepository.findAllById(eventIds);
            if (completeEvents.size() != eventIds.size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            sponsor.setEvents(completeEvents);

            for (Event event : completeEvents) {
                List<Sponsor> sponsorList = event.getSponsors();
                sponsorList.add(sponsor);
            }


            return sponsorJpaRepository.save(sponsor);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteSponsor(int id) {
        try {
            Sponsor sponsor = sponsorJpaRepository.findById(id).get();
            List<Event> events = sponsor.getEvents();
            for (Event event : events) {
                event.getSponsors().remove(sponsor);
            }
            eventJpaRepository.saveAll(events);

            sponsorJpaRepository.delete(sponsor);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Sponsor updateSponsor(int id, Sponsor sponsor) {
        try {
            Sponsor newSponsor = sponsorJpaRepository.findById(id).get();
            if (sponsor.getSponsorName() != null) {
                newSponsor.setSponsorName(sponsor.getSponsorName());
            }
            if (sponsor.getIndustry() != null) {
                newSponsor.setIndustry(sponsor.getIndustry());
            }
            if (sponsor.getEvents() != null) {
                List<Integer> eventIds = new ArrayList<>();
                List<Event> events = sponsor.getEvents();
                for (Event event : events) {
                    eventIds.add(event.getEventId());
                }
                List<Event> completeEvents = eventJpaRepository.findAllById(eventIds);
                if (eventIds.size() != completeEvents.size())
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some events are not found");
                newSponsor.setEvents(completeEvents);
            }

            sponsorJpaRepository.save(newSponsor);
            return newSponsor;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Event> getSponsorEvents(int id) {
        try {
            Sponsor sponsor = sponsorJpaRepository.findById(id).get();
            return sponsor.getEvents();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}