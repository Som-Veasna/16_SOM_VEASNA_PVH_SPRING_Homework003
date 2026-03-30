package com.sna.homework003.service.impl;

import com.sna.homework003.exception.DuplicateException;
import com.sna.homework003.exception.NotFoundException;
import com.sna.homework003.model.dto.EventDTO;
import com.sna.homework003.model.entity.Events;
import com.sna.homework003.repository.EventAttendeeRepository;
import com.sna.homework003.repository.EventRepository;
import com.sna.homework003.service.AttendeeService;
import com.sna.homework003.service.EventService;
import com.sna.homework003.service.VenueService;
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
    public List<Events> getAllEvents(Integer size, Integer page) {
        return eventRepository.getAllEvents(size,page);
    }

    @Override
    public Events saveEvent(EventDTO request) {
        venueService.getVenueById(request.getVenueId());
        for (Integer attendeeId : request.getAttendeeId()) {
            attendeeService.getAttendeeById(attendeeId);
        }
        Events existing = eventRepository.getAllEventByNameAndDate(request.getEventName(), request.getEventDate());
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
        venueService.getVenueById(request.getVenueId());
        for (Integer attendeeId : request.getAttendeeId()) {
            attendeeService.getAttendeeById(attendeeId);
        }
        Events existing = eventRepository.getAllEventByNameAndDate(request.getEventName(), request.getEventDate());
        if (existing != null) {
            throw new DuplicateException("Event with name " + request.getEventName() + " and date " + request.getEventDate() + " already exists");
        }

        for (Integer attendeeId : request.getAttendeeId()) {
            attendeeService.getAttendeeById(attendeeId);
        }

        Events event = eventRepository.updateEvent(eventId, request);
        eventAttendeeRepository.removeEventAttendeeByEventId(eventId);
        for (Integer attendeeId : request.getAttendeeId()) {
            eventAttendeeRepository.saveEventAttendee(event.getEventId(), attendeeId);
        }
        return getEventById(event.getEventId());
    }
    @Override
    public void deleteEventByEventId(Integer eventId) {
       getEventById(eventId);
       eventRepository.deleteEventById(eventId);
    }


}
