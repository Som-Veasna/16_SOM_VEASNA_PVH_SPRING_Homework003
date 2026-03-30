package com.sna.homework003.service;

import com.sna.homework003.model.dto.VenueDTO;
import com.sna.homework003.model.entity.Venues;

import java.util.List;

public interface VenueService {
   List<Venues> getAllVenues(Integer size, Integer page);
   Venues getVenueById(Integer id);
   Venues saveVenue(VenueDTO venueDTO);
   Venues updateVenueById(Integer id, VenueDTO venueDTO);
   void deleteVenueById(Integer id);
}
