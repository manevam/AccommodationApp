package com.example.emtlab.model.dto;

import com.example.emtlab.model.AccommodationCategory;
import lombok.Data;

@Data
public class AccommodationDto {
    String name;
    AccommodationCategory category;
    Long hostId;
    Integer numRooms;
}
