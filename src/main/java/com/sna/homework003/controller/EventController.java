package com.sna.homework003.controller;

import com.sna.homework003.model.dto.EventDTO;
import com.sna.homework003.model.entity.Events;
import com.sna.homework003.model.response.ApiResponse;
import com.sna.homework003.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
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
    @Operation(summary = "Get all events")
    @GetMapping
    public List<Events> getAllEvents(@RequestParam( defaultValue = "10") @Positive Integer size , @RequestParam(defaultValue = "1") @Positive Integer page) {
        return eventService.getAllEvents(size,page);
    }
    @Operation(summary = "Get event by id")
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
    @Operation(summary = "Create event")
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
    @Operation(summary = "Update event by event id")
    @PutMapping("/{event-id}")
    public ResponseEntity<?> updateEvent(@PathVariable("event-id") Integer eventId, @RequestBody @Valid EventDTO eventDTO) {
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
    @Operation(summary = "Delete event by event id")
    @DeleteMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Void>> deleteEventById(@PathVariable("event-id") Integer eventId) {
        eventService.deleteEventByEventId(eventId);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .success(true)
                .message("Event deleted successfully")
                .status(200)
                .timestamp(Instant.now())
                .build());
    }
}
