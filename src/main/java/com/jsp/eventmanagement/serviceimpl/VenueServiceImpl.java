
package com.jsp.eventmanagement.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsp.eventmanagement.entity.Venue;
import com.jsp.eventmanagement.repository.VenueRepository;
import com.jsp.eventmanagement.service.VenueService;

@Service
@Transactional
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public Venue createVenue(Venue venue) {
        venue.setId(null);
        return venueRepository.save(venue);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Venue getVenueById(Long id) {
        return venueRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Venue not found with ID: " + id));
    }

    @Override
    public Venue updateVenue(Long id, Venue venue) {
        Venue existingVenue = getVenueById(id);

        existingVenue.setName(venue.getName());
        existingVenue.setLocation(venue.getLocation());
        existingVenue.setCapacity(venue.getCapacity());
        existingVenue.setDescription(venue.getDescription());

        return venueRepository.save(existingVenue);
    }

    @Override
    public void deleteVenue(Long id) {
        Venue existingVenue = getVenueById(id);
        venueRepository.delete(existingVenue);
    }
}
