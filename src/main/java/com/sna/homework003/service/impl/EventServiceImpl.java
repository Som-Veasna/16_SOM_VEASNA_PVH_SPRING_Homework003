package com.sna.homework003.service.impl;

import com.sna.homework003.model.entity.Events;
import com.sna.homework003.repository.EventRepository;
import com.sna.homework003.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    @Override
    public List<Events> getAllEvents() {
        return eventRepository.getAllEvents();
    }
}
