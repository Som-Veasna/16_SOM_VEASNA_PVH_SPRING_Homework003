package com.sna.homework003.controller;

import com.sna.homework003.model.dto.AttendeeDTO;
import com.sna.homework003.model.entity.Attendees;
import com.sna.homework003.model.response.ApiResponse;
import com.sna.homework003.service.AttendeeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
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
     @Operation(summary = "Get all attendee")
    @GetMapping
    public List<Attendees> getAllAttendees(@RequestParam(defaultValue = "10") @Positive Integer size , @RequestParam(defaultValue = "1") @Positive Integer page) {
        return attendeeService.getAllAttendees(size,page);
    }
    @Operation(summary = "Get attendee by attendee id")
    @GetMapping("/{attendee-id}")
    public ResponseEntity<?> getAttendeeById(@PathVariable("attendee-id") Integer attendeeId) {
       ApiResponse<Attendees> response= ApiResponse.<Attendees>builder()
                .success(true)
                .status(200)
                .message("Attendee found")
                .payload(attendeeService.getAttendeeById(attendeeId))
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.ok().body(response);
    }
    @Operation(summary = "Create attendee")
    @PostMapping
    public ResponseEntity<?> saveAttendee(@RequestBody @Valid  AttendeeDTO attendee) {
        ApiResponse<Attendees> response=ApiResponse.<Attendees>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .message("Attendee saved")
                .payload(attendeeService.saveAttendee(attendee))
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @Operation(summary = "Update attendee by attendee id")
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
    @DeleteMapping("/{attendee-id}")
    public ResponseEntity<?> deleteAttendeeById(@PathVariable("attendee-id") Integer attendeeId) {
        attendeeService.deleteAttendeeByEventId(attendeeId);
        ApiResponse<Void>  response=ApiResponse.<Void>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Attendee deleted")
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
