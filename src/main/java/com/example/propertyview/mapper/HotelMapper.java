package com.example.propertyview.mapper;

import com.example.propertyview.controller.dto.*;
import com.example.propertyview.entity.*;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    @Mapping(target = "address", expression = "java(hotel.getAddress() != null ? String.format(\"%s %s, %s, %s, %s\", hotel.getAddress().getHouseNumber(), hotel.getAddress().getStreet(), hotel.getAddress().getCity(), hotel.getAddress().getPostCode(), hotel.getAddress().getCountry()) : null)")
    @Mapping(target = "phone", source = "contacts.phone")
    HotelShortDto toShortDto(Hotel hotel);
    List<HotelShortDto> toShortDtoList(List<Hotel> hotels);

    @Mapping(target = "address", source = "address")
    @Mapping(target = "contacts", source = "contacts")
    @Mapping(target = "arrivalTime", source = "arrivalTime")
    HotelFullDto toFullDto(Hotel hotel);

    @Mapping(target = "address", source = "address")
    @Mapping(target = "contacts", source = "contacts")
    @Mapping(target = "arrivalTime", source = "arrivalTime")
    Hotel toEntity(HotelCreateDto dto);

    AddressDto addressToAddressDto(Address address);
    ContactsDto contactsToContactsDto(Contacts contacts);
    ArrivalTimeDto arrivalTimeToArrivalTimeDto(ArrivalTime arrivalTime);

    Address toAddress(AddressDto dto);
    Contacts toContacts(ContactsDto dto);
    ArrivalTime toArrivalTime(ArrivalTimeDto dto);
} 