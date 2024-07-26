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
 * EventRepository
 */
@Repository
public interface EventRepository {

    List<Event> getEvents();
    Event addEvent(Event event);
    Event getEventById(int id);
    Event updateEvent(int id, Event event);
    void deleteEvent(int id);
    List<Sponsor> getEventSponsors(int id);
}