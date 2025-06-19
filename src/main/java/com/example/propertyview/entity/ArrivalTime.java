package com.example.propertyview.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArrivalTime {
    private String checkIn;
    private String checkOut;
} 