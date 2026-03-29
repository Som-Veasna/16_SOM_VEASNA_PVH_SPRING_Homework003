package com.sna.homework003.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    @NotBlank(message = "Event name must not be blank")
    private String eventName;

    @NotNull(message = "Event date must not be null")
    private Instant eventDate;

    @NotNull(message = "Venue id must not be null")
    private Integer venueId;

    @NotEmpty(message = "Attendee list must not be empty")
    private List<Integer> attendeeId;
}