package com.example.emtlab.service;

import com.example.emtlab.model.Host;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> listAllHosts();
    Optional<Host> findById(Long id);
    Optional<Host> create(String name, String surname, Long countryId);
    Optional<Host> edit(Long id, String name, String surname, Long countryId);
    Optional<Host> delete(Long id);
}
