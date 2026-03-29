package com.sna.homework003.service.impl;

import com.sna.homework003.exception.DuplicateException;
import com.sna.homework003.exception.NotFoundException;
import com.sna.homework003.model.dto.VenueDTO;
import com.sna.homework003.model.entity.Venues;
import com.sna.homework003.repository.VenueRepository;
import com.sna.homework003.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {
    private final VenueRepository venueRepository;

    @Override
    public List<Venues> getAllVenues() {
        return venueRepository.getAllVenues();
    }

    @Override
    public Venues getVenueById(Integer id) {
        Venues venue = venueRepository.getVenueById(id);
        if (venue == null) {
            throw new NotFoundException("Venue with id " + id + " not found");
        }
        return venue;
    }
    @Override
    public Venues saveVenue(VenueDTO venueDTO) {
        if (venueRepository.getVenueByName(venueDTO.getVenueName()) != null) {
            throw new DuplicateException("Venue name " + venueDTO.getVenueName() + " already exists");
        }
        return venueRepository.saveVenue(venueDTO);
    }
    @Override
    public Venues updateVenueById(Integer id, VenueDTO venueDTO) {
        Venues venue = venueRepository.getVenueById(id);
        if (venue == null) {
            throw new NotFoundException("Venue with id " + id + " not found");
        }
        if (venueRepository.getVenueByName(venueDTO.getVenueName()) != null) {
            throw new DuplicateException("Venue name " + venueDTO.getVenueName() + " already exists");
        }
        return venueRepository.updateVenueById(id, venueDTO);
    }
}