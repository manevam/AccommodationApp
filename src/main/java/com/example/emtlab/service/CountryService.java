package com.example.emtlab.service;

import com.example.emtlab.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> listAllCountries();
    Optional<Country> findById(Long id);
    Optional<Country> create(String name, String continent);
    Optional<Country> edit(Long id, String name, String continent);
    Optional<Country> delete(Long id);
}
