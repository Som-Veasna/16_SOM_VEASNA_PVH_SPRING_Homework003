package com.sna.homework003.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    @NotBlank(message = "Event name must not be blank")
    private String eventName;

    @NotNull(message = "Event date must not be null")
    @Future(message = "Event date must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate;

    @NotNull(message = "Venue id must not be null")
    private Integer venueId;

    @NotEmpty(message = "Attendee list must not be empty")
    private List<Integer> attendeeId;
}