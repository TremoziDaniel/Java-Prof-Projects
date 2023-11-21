package com.bank.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonalDataDto {

    @Schema(description = "Personal data id", defaultValue = "1", type = "Long", nullable = true)
    private Long id;

    @Schema(description = "First name", defaultValue = "John")
    private String firstName;

    @Schema(description = "Last name", defaultValue = "Johnson")
    private String lastName;

    @Schema(description = "Country", defaultValue = "United States of America")
    private String country;

    @Schema(description = "City", defaultValue = "New York")
    private String city;

    @Schema(description = "Street", defaultValue = "Wall Street")
    private String street;

    @Schema(description = "House number", defaultValue = "221B")
    private String houseNumber;

    @Schema(description = "Apartment number", defaultValue = "9", type = "int")
    private int apartmentNumber;

    @Schema(description = "Phone number", defaultValue = "+(999)-999-999-99-99")
    private String phoneNumber;

    @Schema(description = "Email address", defaultValue = "email1example@domain.com")
    private String email;

    @Schema(description = "Password", defaultValue = "Pa$$word123")
    private String password;
}
