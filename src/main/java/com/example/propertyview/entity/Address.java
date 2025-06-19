package com.example.propertyview.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    private Integer houseNumber;
    private String street;
    private String city;
    private String country;
    private String postCode;
} 