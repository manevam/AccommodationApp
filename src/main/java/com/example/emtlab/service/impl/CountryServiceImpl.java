package com.example.emtlab.service.impl;

import com.example.emtlab.model.Country;
import com.example.emtlab.model.exceptions.InvalidCountryIdException;
import com.example.emtlab.repository.CountryRepository;
import com.example.emtlab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return Optional.of(countryRepository.findById(id).orElseThrow(InvalidCountryIdException::new));
    }

    @Override
    public Optional<Country> create(String name, String continent) {
        Country country = new Country(name, continent);
        countryRepository.save(country);
        return Optional.of(country);
    }

    @Override
    public Optional<Country> edit(Long id, String name, String continent) {
        Country country = findById(id).orElseThrow(InvalidCountryIdException::new);
        country.setName(name);
        country.setContinent(continent);
        countryRepository.save(country);
        return Optional.of(country);
    }

    @Override
    public Optional<Country> delete(Long id) {
        Country country = findById(id).orElseThrow(InvalidCountryIdException::new);
        countryRepository.delete(country);
        return Optional.of(country);
    }
}
