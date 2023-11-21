package com.bank.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Entity
@Table(name = "currencies")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(message = "Currency name starts with capital letter, must be longer tha currency abbreviation to 100 characters long.",
            regexp = "[A-Z].{3,100}")
    private String currencyName;

    @Pattern(message = "Currency abbreviation must consist only of 3 capital letters.",
            regexp = "[A-Z]{3}")
    private String currencyAbb;

    private BigDecimal rate;
}
