package com.sna.homework003.controller;

import com.sna.homework003.model.entity.Events;
import com.sna.homework003.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    @GetMapping
    public List<Events> getAllEvents() {
        return eventService.getAllEvents();
    }
}
