package com.bank.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String country;

    private String city;

    private String street;

    private String houseNumber;

    private Integer apartmentNumber;

    @Pattern(message = "Invalid phone number.\nExample: +(999)-999-999-99-99",
            regexp = "^\\+?[(]?[0-9]{1,3}[)]?[-\\s.]?[0-9]{1,2}[-\\s.]?[0-9][-\\s.]?[0-9][-\\s.]?[0-9]{2}[-\\s.]?[0-9]{2}[-\\s.]?[0-9]{2}$")
    private String phoneNumber;

    @Pattern(message = "Invalid email.\nExample: email1example@domain.com",
            regexp = "^([a-zA-Z0-9_\\-.]{3,63})@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$")
    private String email;

    @Pattern(message = "Invalid password.\n" +
            "Password must contain at least 1 upper an lover case latin letter, 1 number, 1 special symbol and be from 8 to 20 symbol long",
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$")
    private String password;
}
