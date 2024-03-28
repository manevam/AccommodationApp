package com.example.emtlab.service.impl;

import com.example.emtlab.model.Host;
import com.example.emtlab.model.exceptions.InvalidCountryIdException;
import com.example.emtlab.model.exceptions.InvalidHostIdException;
import com.example.emtlab.repository.CountryRepository;
import com.example.emtlab.repository.HostRepository;
import com.example.emtlab.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;

    public HostServiceImpl(HostRepository hostRepository, CountryRepository countryRepository) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Host> listAllHosts() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        Host host = hostRepository.findById(id).orElseThrow(InvalidHostIdException::new);
        return Optional.of(host);
    }

    @Override
    public Optional<Host> create(String name, String surname, Long countryId) {
        Host host = new Host(name,surname,
                                countryRepository.findById(countryId).orElseThrow(InvalidCountryIdException::new));
        hostRepository.save(host);
        return Optional.of(host);
    }

    @Override
    public Optional<Host> edit(Long id, String name, String surname, Long countryId) {
        Host host = findById(id).orElseThrow(InvalidHostIdException::new);
        host.setName(name);
        host.setSurname(surname);
        host.setCountry(countryRepository.findById(countryId).orElseThrow(InvalidCountryIdException::new));

        hostRepository.save(host);
        return Optional.of(host);
    }

    @Override
    public Optional<Host> delete(Long id) {
        Host host = findById(id).orElseThrow(InvalidHostIdException::new);
        hostRepository.deleteById(id);
        return Optional.of(host);
    }

}
