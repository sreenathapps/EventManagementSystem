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
            for(Event eve: sponsor.getEvents()) {
                eventIds.add(eve.getEventId());
            }
            //Retrieve the event Entities from teh database
            List<Event> completeEves = eventJpaRepository.findAllById(eventIds);

            if (eventIds.size() != completeEves.size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            //Map the events to the sponsor
            sponsor.setEvents(completeEves);

            for(Event eve: completeEves) {
                eve.getSponsors().add(sponsor);
            }

            Sponsor savedSponsor =  sponsorJpaRepository.save(sponsor);

            eventJpaRepository.saveAll(completeEves);

            return savedSponsor;

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

            //update all other fields of the sponsor entity
            if (sponsor.getSponsorName() != null) {
                newSponsor.setSponsorName(sponsor.getSponsorName());
            }
            if (sponsor.getIndustry() != null) {
                newSponsor.setIndustry(sponsor.getIndustry());
            }
            //update events along with the association
            if (sponsor.getEvents() != null) {
                //Clear all the existing associations of sponsor
                List<Event> events = newSponsor.getEvents();
                for(Event event : events) {
                    event.getSponsors().remove(newSponsor);
                }
                eventJpaRepository.saveAll(events);

                //Extract event Ids of new Events
                List<Integer> newEventIds = new ArrayList<>();
                for(Event event: sponsor.getEvents()) {
                    newEventIds.add(event.getEventId());
                }

                //Fetch all the Events from database
                List<Event> newEvents = eventJpaRepository.findAllById(newEventIds);

                if (newEventIds.size() != newEvents.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }

                //Add teh new associations of the event
                for(Event event: newEvents) {
                    event.getSponsors().add(newSponsor);
                }
                eventJpaRepository.saveAll(newEvents);

                //map the new Events to the sponsor
                newSponsor.setEvents(newEvents);
            }

            return sponsorJpaRepository.save(newSponsor);
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