package com.sna.homework003.service.impl;

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
        return venueRepository.getVenueById(id);
    }

    @Override
    public Venues saveVenue(VenueDTO venueDTO) {

        return venueRepository.saveVenue(venueDTO);
    }

    @Override
    public Venues updateVenueById(Integer id, VenueDTO venueDTO) {
        return venueRepository.updateVenueById(id, venueDTO);
    }
}
