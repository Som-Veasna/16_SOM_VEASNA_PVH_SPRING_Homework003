package com.sna.homework003.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VenueDTO {
    @NotBlank(message = "Attendee name must not be blank or null")
    private String venueName;
    @NotBlank(message = "Email must not be blank")
    private String location;
}
