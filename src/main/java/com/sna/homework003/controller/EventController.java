package com.sna.homework003.controller;

import com.sna.homework003.model.dto.EventDTO;
import com.sna.homework003.model.entity.Events;
import com.sna.homework003.model.response.ApiResponse;
import com.sna.homework003.service.EventService;
import jakarta.validation.Valid;
import jdk.jfr.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
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
    @GetMapping("/{event-id}")
    public ResponseEntity<?> getEventById(@PathVariable("event-id") Integer eventId) {
       Events event= eventService.getEventById(eventId);
        ApiResponse<Events> response=ApiResponse.<Events>builder()
                .success(true)
                .message("Success")
                .status(HttpStatus.OK.value())
                .payload(event)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
    @PostMapping
    public ResponseEntity<?> saveEvent(@RequestBody @Valid EventDTO event) {
        ApiResponse<Events> response=ApiResponse.<Events>builder()
                .success(true)
                .message("Success")
                .status(HttpStatus.CREATED.value())
                .payload(eventService.saveEvent(event))
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/{event-id}")
    public ResponseEntity<?> updateEvent(@PathVariable("event-id") Integer eventId, @RequestBody EventDTO eventDTO) {
       Events event= eventService.updateEvent(eventId, eventDTO);
        ApiResponse<Events> response=ApiResponse.<Events>builder()
                .success(true)
                .message("Success")
                .status(HttpStatus.OK.value())
                .payload(event)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
