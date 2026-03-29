package com.sna.homework003.controller;

import com.sna.homework003.model.dto.AttendeeDTO;
import com.sna.homework003.model.entity.Attendees;
import com.sna.homework003.model.response.ApiResponse;
import com.sna.homework003.service.AttendeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attendees")
public class AttendeeController {
    private final AttendeeService attendeeService;

    @GetMapping
    public List<Attendees> getAllAttendees() {
        return attendeeService.getAllAttendees();
    }
    @GetMapping("/{attendee-id}")
    public ResponseEntity<?> getAttendeeById(@PathVariable("attendee-id") Integer attendeeId) {
        Attendees attendee = attendeeService.getAttendeeById(attendeeId);
       ApiResponse<Attendees> response= ApiResponse.<Attendees>builder()
                .success(true)
                .status(200)
                .message("Attendee found")
                .payload(attendee)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.ok().body(response);
    }
    @PostMapping
    public ResponseEntity<?> saveAttendee(@RequestBody @Valid  AttendeeDTO attendee) {
        ApiResponse<Attendees> response=ApiResponse.<Attendees>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .message("Attendee saved")
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/{attendee-id}")
    public ResponseEntity<?> updateAttendeById(@PathVariable("attendee-id") Integer attendeeId, @RequestBody @Valid AttendeeDTO attendee) {
        ApiResponse<Attendees> response=ApiResponse.<Attendees>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Attendee updated")
                .payload(attendeeService.updateAttendee(attendeeId, attendee))
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/event/{event-id}")
    public List<Attendees> getAllAttendeesByEventId(@PathVariable("event-id") Integer eventId) {
        return attendeeService.getAllAttendeesByEventId(eventId);
    }

}
