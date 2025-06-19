package com.example.propertyview.controller;

import com.example.propertyview.controller.dto.HotelCreateDto;
import com.example.propertyview.controller.dto.HotelFullDto;
import com.example.propertyview.controller.dto.HotelShortDto;
import com.example.propertyview.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/property-view")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("/hotels")
    public List<HotelShortDto> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/hotels/{id}")
    public HotelFullDto getHotel(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @GetMapping("/search")
    public List<HotelShortDto> searchHotels(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) List<String> amenities
    ) {
        return hotelService.searchHotels(name, brand, city, country, amenities);
    }

    @PostMapping("/hotels")
    public HotelShortDto createHotel(@RequestBody @Valid HotelCreateDto dto) {
        return hotelService.createHotel(dto);
    }

    @PostMapping("/hotels/{id}/amenities")
    public List<String> addAmenities(@PathVariable Long id, @RequestBody List<String> amenities) {
        hotelService.addAmenities(id, amenities);
        return amenities;
    }

    @GetMapping("/histogram/{param}")
    public Map<String, Long> getHistogram(@PathVariable String param) {
        return hotelService.getHistogram(param);
    }
} 