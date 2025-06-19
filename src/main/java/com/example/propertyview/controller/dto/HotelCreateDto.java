package com.example.propertyview.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelCreateDto {
    @NotNull
    @Valid
    private String name;
    private String description;
    @NotNull
    @Valid
    private String brand;
    @NotNull
    @Valid
    private AddressDto address;
    @NotNull
    @Valid
    private ContactsDto contacts;
    @NotNull
    @Valid
    private ArrivalTimeDto arrivalTime;
} 