package com.example.propertyview.service;

import com.example.propertyview.entity.Hotel;
import com.example.propertyview.mapper.HotelMapper;
import com.example.propertyview.repository.HotelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {
    @Mock
    private HotelRepository hotelRepository;
    @Mock
    private HotelMapper hotelMapper;
    @InjectMocks
    private HotelService hotelService;

    @Test
    void addAmenitiesCreatesList() {
        var hotel = new Hotel();
        hotel.setId(1L);
        when(hotelRepository.findById(1L)).thenReturn(java.util.Optional.of(hotel));
        when(hotelRepository.save(hotel)).thenReturn(hotel);
        hotelService.addAmenities(1L, java.util.List.of("WiFi"));
        assertThat(hotel.getAmenities()).contains("WiFi");
    }

    @Test
    void addAmenitiesThrowsOnNotExistingHotel() {
        assertThrows(NoSuchElementException.class, () -> hotelService.addAmenities(1L, java.util.List.of("WiFi")));
    }

    @Test
    void getHistogramThrowsOnUnknownParam() {
        assertThrows(IllegalArgumentException.class, () -> hotelService.getHistogram("unknown"));
    }
} 