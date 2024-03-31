package com.example.emtlab.web;

import com.example.emtlab.model.Accommodation;
import com.example.emtlab.model.dto.AccommodationDto;
import com.example.emtlab.model.exceptions.NoAvailableRooms;
import com.example.emtlab.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationRestController {
    private final AccommodationService accommodationService;

    public AccommodationRestController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping("")
    public List<Accommodation> getAllAccommodations()
    {
        return accommodationService.listAllAccommodations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> getAccommodationById (@PathVariable Long id)
    {
        return accommodationService.findById(id).map(acc -> ResponseEntity.ok().body(acc))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Accommodation> addAccommodation (@RequestBody AccommodationDto accDto)
    {
        return accommodationService.create(accDto.getName(), accDto.getCategory(), accDto.getHostId(),accDto.getNumRooms())
                .map(acc -> ResponseEntity.ok().body(acc))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Accommodation> deleteAccommodation (@PathVariable Long id)
    {
        return accommodationService.delete(id)
                .map(acc -> ResponseEntity.ok().body(acc))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Accommodation> editAccommodation (@PathVariable Long id, @RequestBody AccommodationDto accDto)
    {
        return accommodationService.edit(id, accDto.getName(), accDto.getCategory(), accDto.getHostId(), accDto.getNumRooms())
                .map(acc -> ResponseEntity.ok().body(acc))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/bookRoom/{id}")
    public ResponseEntity<Accommodation> bookRoomInAccommodation (@PathVariable Long id)
    {
        try {
            return accommodationService.rentedAccommodation(id)
                    .map(acc -> ResponseEntity.ok().body(acc))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (NoAvailableRooms e) {
            throw new RuntimeException(e);
        }
    }
}
