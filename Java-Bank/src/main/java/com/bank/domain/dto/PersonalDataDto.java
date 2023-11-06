package com.bank.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonalDataDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String country;

    private String city;

    private String street;

    private String houseNumber;

    private Integer apartmentNumber;

    private String phoneNumber;

    private String email;

    private String password;
}
