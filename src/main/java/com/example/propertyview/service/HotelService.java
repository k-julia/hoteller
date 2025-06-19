package com.example.propertyview.service;

import com.example.propertyview.controller.dto.HotelCreateDto;
import com.example.propertyview.controller.dto.HotelFullDto;
import com.example.propertyview.controller.dto.HotelShortDto;
import com.example.propertyview.entity.Hotel;
import com.example.propertyview.mapper.HotelMapper;
import com.example.propertyview.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public List<HotelShortDto> getAllHotels() {
        return hotelMapper.toShortDtoList(hotelRepository.findAll());
    }

    public HotelFullDto getHotelById(Long id) {
        return hotelRepository.findById(id)
                .map(hotelMapper::toFullDto)
                .orElseThrow(() -> new NoSuchElementException("Hotel not found"));
    }

    @Transactional
    public HotelShortDto createHotel(HotelCreateDto dto) {
        Hotel hotel = hotelMapper.toEntity(dto);
        hotel = hotelRepository.save(hotel);
        return hotelMapper.toShortDto(hotel);
    }

    @Transactional
    public void addAmenities(Long hotelId, List<String> amenities) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new NoSuchElementException("Hotel not found"));
        if (hotel.getAmenities() == null) hotel.setAmenities(new ArrayList<>());
        hotel.getAmenities().addAll(amenities);
        hotelRepository.save(hotel);
    }

    public List<HotelShortDto> searchHotels(String name, String brand, String city, String country, List<String> amenities) {
        List<Hotel> hotels = hotelRepository.search(name, brand, city, country);
        if (amenities != null && !amenities.isEmpty()) {
            hotels = hotels.stream()
                    .filter(hotel -> hotel.getAmenities() != null && hotel.getAmenities().containsAll(amenities))
                    .toList();
        }
        return hotelMapper.toShortDtoList(hotels);
    }

    public Map<String, Long> getHistogram(String param) {
        switch (param) {
            case "brand" -> {
                return hotelRepository.findAll().stream()
                        .filter(h -> h.getBrand() != null)
                        .collect(Collectors.groupingBy(Hotel::getBrand, Collectors.counting()));
            }
            case "city" -> {
                return hotelRepository.findAll().stream()
                        .filter(h -> h.getAddress() != null && h.getAddress().getCity() != null)
                        .collect(Collectors.groupingBy(h -> h.getAddress().getCity(), Collectors.counting()));
            }
            case "country" -> {
                return hotelRepository.findAll().stream()
                        .filter(h -> h.getAddress() != null && h.getAddress().getCountry() != null)
                        .collect(Collectors.groupingBy(h -> h.getAddress().getCountry(), Collectors.counting()));
            }
            case "amenities" -> {
                Map<String, Long> amenityMap = new HashMap<>();
                hotelRepository.findAll().forEach(h -> {
                    if (h.getAmenities() != null) {
                        h.getAmenities().forEach(a -> amenityMap.put(a, amenityMap.getOrDefault(a, 0L) + 1));
                    }
                });
                return amenityMap;
            }
            default -> throw new IllegalArgumentException("Unknown histogram param: " + param);
        }
    }
} 