
package com.jsp.eventmanagement.service;

import java.util.List;

import com.jsp.eventmanagement.entity.Event;

public interface EventService {

    List<Event> getAllEvents();

    Event getEventById(Long id);

    Event createEvent(Event event);

    Event updateEvent(Long id, Event event);

    void deleteEvent(Long id);
}
