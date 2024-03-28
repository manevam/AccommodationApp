package com.example.emtlab.service.impl;

import com.example.emtlab.model.Accommodation;
import com.example.emtlab.model.AccommodationCategory;
import com.example.emtlab.model.exceptions.InvalidAccommodationIdException;
import com.example.emtlab.model.exceptions.InvalidHostIdException;
import com.example.emtlab.model.exceptions.NoAvailableRooms;
import com.example.emtlab.repository.AccommodationRepository;
import com.example.emtlab.repository.CountryRepository;
import com.example.emtlab.repository.HostRepository;
import com.example.emtlab.service.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, CountryRepository countryRepository, HostRepository hostRepository) {
        this.accommodationRepository = accommodationRepository;
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Accommodation> listAllAccommodations() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return Optional.of(accommodationRepository.findById(id).orElseThrow(InvalidAccommodationIdException::new));
    }

    @Override
    public Optional<Accommodation> create(String name, AccommodationCategory category, Long hostId, Integer numRooms) {
        Accommodation accommodation = new Accommodation(name, category,
                                                        hostRepository.findById(hostId).orElseThrow(InvalidHostIdException::new),
                                                        numRooms);
        accommodationRepository.save(accommodation);
        return Optional.of(accommodation);
    }

    @Override
    public Optional<Accommodation> edit(Long id, String name, AccommodationCategory category, Long hostId, Integer numRooms) {
        Accommodation accommodation = findById(id).orElseThrow(InvalidAccommodationIdException::new);
        accommodation.setName(name);
        accommodation.setCategory(category);
        accommodation.setHost(hostRepository.findById(hostId).orElseThrow(InvalidHostIdException::new));
        accommodation.setNumRooms(numRooms);
        accommodationRepository.save(accommodation);
        return Optional.of(accommodation);
    }

    @Override
    public Optional<Accommodation> delete(Long id) {
        Accommodation accommodation = findById(id).orElseThrow(InvalidAccommodationIdException::new);
        accommodationRepository.deleteById(id);
        return Optional.of(accommodation);
    }

    @Override
    public Optional<Accommodation> rentedAccommodation(Long id) throws NoAvailableRooms {
        Accommodation accommodation = findById(id).orElseThrow(InvalidAccommodationIdException::new);
        if(accommodation.getNumRooms() == 0)
            throw new NoAvailableRooms();
        accommodation.setNumRooms(0);
        accommodationRepository.save(accommodation);
        return Optional.of(accommodation);
    }
}
