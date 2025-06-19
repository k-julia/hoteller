package com.example.propertyview.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    @NotNull
    private Integer houseNumber;
    @NotNull
    private String street;
    @NotNull
    private String city;
    @NotNull
    private String country;
    @NotNull
    private String postCode;
}