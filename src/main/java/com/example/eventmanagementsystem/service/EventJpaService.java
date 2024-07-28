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
import com.example.eventmanagementsystem.repository.EventRepository;
import com.example.eventmanagementsystem.repository.SponsorJpaRepository;

/**
 * EventJpaService
 */
@Service
public class EventJpaService implements EventRepository {
    @Autowired
    EventJpaRepository eventJpaRepository;
    @Autowired
    SponsorJpaRepository sponsorJpaRepository;

    @Override
    public List<Event> getEvents() {
        List<Event> eventList = eventJpaRepository.findAll();
        System.err.println(eventList);
        return eventList;
    }

    @Override
    public Event addEvent(Event event) {
        try {
            List<Integer> sponsorIds = new ArrayList<>();
            for (Sponsor sponsor : event.getSponsors()) {
                sponsorIds.add(sponsor.getId());
            }
            List<Sponsor> completeSponsors = sponsorJpaRepository.findAllById(sponsorIds);
            if (sponsorIds.size() != completeSponsors.size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some of sponsors are not found");
            }
            event.setSponsors(completeSponsors);
            eventJpaRepository.save(event);
            return event;

        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Event getEventById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEventById'");
    }

    @Override
    public Event updateEvent(int id, Event event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateEvent'");
    }

    @Override
    public void deleteEvent(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteEvent'");
    }

    @Override
    public List<Sponsor> getEventSponsors(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEventSponsor'");
    }

}