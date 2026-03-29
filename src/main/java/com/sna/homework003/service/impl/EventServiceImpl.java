package com.sna.homework003.service.impl;

import com.sna.homework003.exception.DuplicateException;
import com.sna.homework003.exception.NotFoundException;
import com.sna.homework003.model.dto.EventDTO;
import com.sna.homework003.model.entity.Attendees;
import com.sna.homework003.model.entity.Events;
import com.sna.homework003.repository.AttendeeRepository;
import com.sna.homework003.repository.EventAttendeeRepository;
import com.sna.homework003.repository.EventRepository;
import com.sna.homework003.service.AttendeeService;
import com.sna.homework003.service.EventService;
import com.sna.homework003.service.VenueService;
import jdk.jfr.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventAttendeeRepository eventAttendeeRepository;
    private final AttendeeService attendeeService;
    private final VenueService venueService;
    @Override
    public List<Events> getAllEvents() {
        return eventRepository.getAllEvents();
    }

    @Override
    public Events saveEvent(EventDTO request) {
        venueService.getVenueById(request.getVenueId());
        for (Integer attendeeId : request.getAttendeeId()) {
            attendeeService.getAttendeeById(attendeeId);
        }

        Events existing = eventRepository.getEventByNameAndDate(request.getEventName(), request.getEventDate());
        if (existing != null) {
            throw new DuplicateException("Event with name " + request.getEventName() + " and date " + request.getEventDate() + " already exists");
        }

        Events event = eventRepository.saveEvent(request);
        for (Integer attendeeId : request.getAttendeeId()) {
            eventAttendeeRepository.saveEventAttendee(event.getEventId(), attendeeId);
        }
        return getEventById(event.getEventId());
    }

    @Override
    public Events getEventById(Integer eventId) {
       Events event= eventRepository.getEventById(eventId);
       if(event==null){
           throw new NotFoundException("Event with id " + eventId + " not found");
       }

        return event;
    }

    @Override
    public Events updateEvent(Integer eventId, EventDTO request) {
        getEventById(eventId);
        venueService.getVenueById(request.getVenueId());
        Events event=  eventRepository.updateEvent(eventId, request);
       venueService.getVenueById(request.getVenueId());
        eventAttendeeRepository.removeEventAttendeeByEventId(eventId);
        for(Integer attendeeId:request.getAttendeeId()){
            attendeeService.getAttendeeById(attendeeId);
            eventAttendeeRepository.saveEventAttendee(event.getEventId(), attendeeId);
        }
        return getEventById(event.getEventId());
    }


}
