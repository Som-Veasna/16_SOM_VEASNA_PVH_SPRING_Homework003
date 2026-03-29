package com.sna.homework003.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendeeDTO {
    @NotBlank(message = "Attendee name must not be blank or null")
    private String attendeeName;
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be valid")
    private String email;
}
