
package com.jsp.eventmanagement.service;

import java.util.List;

import com.jsp.eventmanagement.entity.Venue;

public interface VenueService {

    Venue createVenue(Venue venue);

    List<Venue> getAllVenues();

    Venue getVenueById(Long id);

    Venue updateVenue(Long id, Venue venue);

    void deleteVenue(Long id);
}
