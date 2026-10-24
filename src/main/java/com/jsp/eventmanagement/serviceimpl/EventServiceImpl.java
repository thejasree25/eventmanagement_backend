
package com.jsp.eventmanagement.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsp.eventmanagement.entity.Event;
import com.jsp.eventmanagement.repository.EventRepository;
import com.jsp.eventmanagement.service.EventService;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Event not found: " + id));
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long id, Event updatedEvent) {
        Event event = getEventById(id);

        event.setTitle(updatedEvent.getTitle());
        event.setDescription(updatedEvent.getDescription());
        event.setLocation(updatedEvent.getLocation());
        event.setEventDate(updatedEvent.getEventDate());
        event.setTicketPrice(updatedEvent.getTicketPrice());
        event.setTotalSeats(updatedEvent.getTotalSeats());
        event.setAvailableSeats(updatedEvent.getAvailableSeats());
        event.setImageUrl(updatedEvent.getImageUrl());
        event.setStatus(updatedEvent.getStatus());

        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        Event event = getEventById(id);
        eventRepository.delete(event);
    }
}
