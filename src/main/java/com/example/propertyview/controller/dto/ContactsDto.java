package com.example.propertyview.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Valid
@AllArgsConstructor
@NoArgsConstructor
public class ContactsDto {
    @NotNull
    private String phone;
    @NotNull
    private String email;
}