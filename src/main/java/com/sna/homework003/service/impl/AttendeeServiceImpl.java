package com.sna.homework003.service.impl;

import com.sna.homework003.exception.DuplicateException;
import com.sna.homework003.exception.NotFoundException;
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
    public List<Attendees> getAllAttendees(Integer size,Integer page) {
        return attendeeRepository.getAllAttendees(size,page);
    }

    @Override
    public Attendees getAttendeeById(Integer id) {
        Attendees attendee = attendeeRepository.getAttendeeById(id);
        if (attendee == null) {
            throw new NotFoundException("Attendee with id " + id + " not found");
        }
        return attendee;
    }

    @Override
    public Attendees saveAttendee(AttendeeDTO attendee) {
        if (attendeeRepository.getAttendeeByName(attendee.getAttendeeName()) != null) {
            throw new DuplicateException("Name " + attendee.getAttendeeName() + " already exists");
        }
        if (attendeeRepository.getAttendeeByEmail(attendee.getEmail()) != null) {
            throw new DuplicateException("Email " + attendee.getEmail() + " already exists");
        }
        return attendeeRepository.saveAttendee(attendee);
    }

    @Override
    public Attendees updateAttendee(Integer attendeeId, AttendeeDTO attendee) {
        Attendees isValisd = attendeeRepository.getAttendeeById(attendeeId);
        if (isValisd == null) {
            throw new NotFoundException("Attendee with id " + attendeeId + " not found");
        }
        if (attendeeRepository.getAttendeeByName(attendee.getAttendeeName()) != null) {
            throw new DuplicateException("Name " + attendee.getAttendeeName() + " already exists");
        }
        if (attendeeRepository.getAttendeeByEmail(attendee.getEmail()) != null) {
            throw new DuplicateException("Email " + attendee.getEmail() + " already exists");
        }

        return attendeeRepository.updateAttendeeById(attendeeId, attendee);
    }

    @Override
    public List<Attendees> getAllAttendeesByEventId(Integer eventId) {
        return attendeeRepository.getAllAttendeeByEventId(eventId);
    }

    @Override
    public void deleteAttendeeByEventId(Integer eventId) {
        getAttendeeById(eventId);
        attendeeRepository.deleteAttendeeById(eventId);
    }
}
