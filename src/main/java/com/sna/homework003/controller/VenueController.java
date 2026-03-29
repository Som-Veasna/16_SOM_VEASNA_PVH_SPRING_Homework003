package com.sna.homework003.controller;

import com.sna.homework003.model.dto.VenueDTO;
import com.sna.homework003.model.entity.Venues;
import com.sna.homework003.model.response.ApiResponse;
import com.sna.homework003.service.VenueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
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
    public ResponseEntity<?> getVenueById(@PathVariable("venue-id") Integer venueId) {
      Venues venues=venueService.getVenueById(venueId);
    ApiResponse<Venues> response=ApiResponse.<Venues>builder()
            .success(true)
            .status(HttpStatus.FOUND.value())
            .message("Venue found")
            .payload(venues)
            .timestamp(Instant.now())
            .build();
      return ResponseEntity.status(HttpStatus.FOUND).body(response);
  }
  @PostMapping
    public Venues saveVenue(@RequestBody @Valid VenueDTO venueDTO) {
      return venueService.saveVenue(venueDTO);
  }
  @PutMapping("/{venue-id}")
    public Venues updateVenueById(@PathVariable("venue-id") Integer id, VenueDTO venueDTO) {
      return venueService.updateVenueById(id, venueDTO);
  }


}
