package com.example.propertyview.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Valid
@RequiredArgsConstructor
public class ArrivalTimeDto {
    @NotNull
    private String checkIn;
    private String checkOut;
}