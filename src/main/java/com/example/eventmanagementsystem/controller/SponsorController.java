/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * 
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.eventmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.eventmanagementsystem.model.Event;
import com.example.eventmanagementsystem.model.Sponsor;
import com.example.eventmanagementsystem.service.SponsorJpaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * SponsorController
 */
@RestController
public class SponsorController {
    @Autowired
    private SponsorJpaService sponsorJpaService;

    @GetMapping("/events/sponsors")
    public List<Sponsor> getSponsors() {
        return sponsorJpaService.getSponsors();
    }

    @PostMapping("/events/sponsors")
    public Sponsor addSponsor(@RequestBody Sponsor sponsor) {
        return sponsorJpaService.addSponsor(sponsor);
    }

    @GetMapping("/events/sponsors/{id}")
    public Sponsor getSponsor(@PathVariable("id") int id) {
        return sponsorJpaService.getSponsorById(id);
    }

    @PutMapping("/events/sponsors/{id}")
    public Sponsor updateSponsor(@PathVariable("id") int id, @RequestBody Sponsor sponsor) {
        return sponsorJpaService.updateSponsor(id, sponsor);
    }

    @DeleteMapping("/events/sponsors/{id}")
    public void deleteSposnor(@PathVariable("id") int id) {
        sponsorJpaService.deleteSponsor(id);
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/sponsors/{id}/events")
    public List<Event> getSponsorEvents(@PathVariable("id") int id) {
        return sponsorJpaService.getSponsorEvents(id);
    }

}