package com.example.propertyview.repository;

import com.example.propertyview.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Query("""
        SELECT DISTINCT h FROM Hotel h
        WHERE (:name IS NULL OR LOWER(h.name) LIKE LOWER(CONCAT('%', :name, '%')))
          AND (:brand IS NULL OR LOWER(h.brand) LIKE LOWER(CONCAT('%', :brand, '%')))
          AND (:city IS NULL OR LOWER(h.address.city) LIKE LOWER(CONCAT('%', :city, '%')))
          AND (:country IS NULL OR LOWER(h.address.country) LIKE LOWER(CONCAT('%', :country, '%')))
        """)
    List<Hotel> search(
            String name,
            String brand,
            String city,
            String country);

    @Query("SELECT h.brand AS value, COUNT(h) AS count FROM Hotel h WHERE h.brand IS NOT NULL GROUP BY h.brand")
    List<Object[]> getBrandHistogram();

    @Query("SELECT h.address.city AS value, COUNT(h) AS count FROM Hotel h WHERE h.address.city IS NOT NULL GROUP BY h.address.city")
    List<Object[]> getCityHistogram();

    @Query("SELECT h.address.country AS value, COUNT(h) AS count FROM Hotel h WHERE h.address.country IS NOT NULL GROUP BY h.address.country")
    List<Object[]> getCountryHistogram();

    default java.util.Map<String, Long> getBrandHistogramMap() {
        return getBrandHistogram().stream()
                .collect(java.util.stream.Collectors.toMap(
                        arr -> (String) arr[0],
                        arr -> (Long) arr[1]
                ));
    }

    default java.util.Map<String, Long> getCityHistogramMap() {
        return getCityHistogram().stream()
                .collect(java.util.stream.Collectors.toMap(
                        arr -> (String) arr[0],
                        arr -> (Long) arr[1]
                ));
    }

    default java.util.Map<String, Long> getCountryHistogramMap() {
        return getCountryHistogram().stream()
                .collect(java.util.stream.Collectors.toMap(
                        arr -> (String) arr[0],
                        arr -> (Long) arr[1]
                ));
    }
} 