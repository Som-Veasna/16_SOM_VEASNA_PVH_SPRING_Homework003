package com.sna.homework003.service.impl;

import com.sna.homework003.model.dto.AttendeeDTO;
import com.sna.homework003.model.entity.Attendees;
import com.sna.homework003.repository.AttendeeRepository;
import com.sna.homework003.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepository attendeeRepository;
    @Override
    public List<Attendees> getAllAttendees() {
        return attendeeRepository.getAllAttendees();
    }

    @Override
    public Attendees getAttendeeById(Integer id) {
        return attendeeRepository.getAttendeeById(id);
    }

    @Override
    public Attendees saveAttendee(AttendeeDTO attendee) {
        return attendeeRepository.saveAttendee(attendee);
    }

    @Override
    public Attendees updateAttendee(Integer attendeeId, AttendeeDTO attendee) {
        return attendeeRepository.updateAttendeeById(attendeeId, attendee);
    }

    @Override
    public List<Attendees> getAllAttendeesByEventId(Integer eventId) {
        return attendeeRepository.getAllAttendeeByEventId(eventId);
    }
}
