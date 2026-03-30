package com.sna.homework003.controller;

import com.sna.homework003.model.dto.VenueDTO;
import com.sna.homework003.model.entity.Venues;
import com.sna.homework003.model.response.ApiResponse;
import com.sna.homework003.service.VenueService;
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
@RequestMapping("/api/v1/venues")
public class VenueController {
  private final VenueService venueService;
  @Operation(summary = "Get all venue")
  @GetMapping
  public List<Venues> getAllVenues(@RequestParam( defaultValue = "10") @Positive Integer size , @RequestParam(defaultValue = "1") @Positive Integer page) {
      return venueService.getAllVenues(size, page);
  }
  @Operation(summary = "Get Venue by venue id")
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
  @Operation(summary = "Create venue")
  @PostMapping
    public Venues saveVenue(@RequestBody @Valid VenueDTO venueDTO) {
      return venueService.saveVenue(venueDTO);
  }
  @Operation(summary = "Update venue by venue id")
  @PutMapping("/{venue-id}")
    public Venues updateVenueById(@PathVariable("venue-id") Integer id, VenueDTO venueDTO) {
      return venueService.updateVenueById(id, venueDTO);
  }
  @Operation(summary = "Delete venue by venue id")
  @DeleteMapping("/{venue-id}")
  public ResponseEntity<?> deleteVenueById(@PathVariable("venue-id") Integer id) {
    venueService.deleteVenueById(id);
    ApiResponse<Void> response=ApiResponse.<Void>builder()
            .success(true)
            .status(HttpStatus.FOUND.value())
            .message("Venue delete successfully")
            .timestamp(Instant.now())
            .build();
     return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }



}
