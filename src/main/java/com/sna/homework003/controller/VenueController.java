package com.sna.homework003.controller;

import com.sna.homework003.model.dto.VenueDTO;
import com.sna.homework003.model.entity.Venues;
import com.sna.homework003.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/venues")
public class VenueController {
  private final VenueService venueService;
  @GetMapping
  public List<Venues> getAllVenues() {
      return venueService.getAllVenues();
  }
  @GetMapping("/{venue-id}")
    public Venues getVenueById(@PathVariable("venue-id") Integer venueId) {
      return venueService.getVenueById(venueId);
  }
  @PostMapping
    public Venues saveVenue(@RequestBody VenueDTO venueDTO) {
      return venueService.saveVenue(venueDTO);
  }
  @PutMapping("/{venue-id}")
    public Venues updateVenueById(@PathVariable("venue-id") Integer id, VenueDTO venueDTO) {
      return venueService.updateVenueById(id, venueDTO);
  }


}
