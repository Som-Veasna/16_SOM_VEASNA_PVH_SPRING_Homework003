package com.sna.homework003.controller;

import com.sna.homework003.model.dto.AttendeeDTO;
import com.sna.homework003.model.entity.Attendees;
import com.sna.homework003.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public Attendees getAttendeeById(@PathVariable("attendee-id") Integer attendeeId) {
        return attendeeService.getAttendeeById(attendeeId);
    }
    @PostMapping
    public Attendees saveAttendee(@RequestBody AttendeeDTO attendee) {
        return attendeeService.saveAttendee(attendee);
    }
    @PutMapping("/{attendee-id}")
    public Attendees updateAttendeById(@PathVariable("attendee-id") Integer attendeeId, @RequestBody AttendeeDTO attendee) {
        return attendeeService.updateAttendee(attendeeId, attendee);
    }
    @GetMapping("/event/{event-id}")
    public List<Attendees> getAllAttendeesByEventId(@PathVariable("event-id") Integer eventId) {
        return attendeeService.getAllAttendeesByEventId(eventId);
    }

}
