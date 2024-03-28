package com.example.emtlab.service;

import com.example.emtlab.model.Accommodation;
import com.example.emtlab.model.AccommodationCategory;
import com.example.emtlab.model.exceptions.NoAvailableRooms;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {

    List<Accommodation> listAllAccommodations();
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> create(String name, AccommodationCategory category, Long hostId, Integer numRooms);
    Optional<Accommodation> edit(Long id, String name, AccommodationCategory category, Long hostId, Integer numRooms);
    Optional<Accommodation> delete(Long id);
    Optional<Accommodation> rentedAccommodation(Long id) throws NoAvailableRooms;
}
