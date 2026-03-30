package com.sna.homework003.service;

import com.sna.homework003.model.dto.EventDTO;
import com.sna.homework003.model.entity.Events;
import jdk.jfr.Event;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EventService {
    List<Events> getAllEvents(Integer size, Integer page);
    Events saveEvent(EventDTO request);
    Events getEventById(Integer eventId);
    Events updateEvent(Integer eventId, EventDTO request);
    void deleteEventByEventId(Integer eventId);
}
