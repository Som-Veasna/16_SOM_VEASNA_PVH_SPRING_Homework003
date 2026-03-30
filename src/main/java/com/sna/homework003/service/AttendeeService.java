package com.sna.homework003.service;

import com.sna.homework003.model.dto.AttendeeDTO;
import com.sna.homework003.model.entity.Attendees;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AttendeeService{
    List<Attendees> getAllAttendees(Integer size,Integer page);
    Attendees getAttendeeById(Integer id);
    Attendees saveAttendee(AttendeeDTO attendee);
    Attendees updateAttendee(Integer attendeeId,AttendeeDTO attendee);
    List<Attendees> getAllAttendeesByEventId(Integer eventId);
    void deleteAttendeeByEventId(Integer eventId);
}
