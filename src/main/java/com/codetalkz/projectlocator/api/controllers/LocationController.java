package com.codetalkz.projectlocator.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codetalkz.projectlocator.api.handlers.ResponseHandler;
import com.codetalkz.projectlocator.api.models.Location;
import com.codetalkz.projectlocator.api.repositories.LocationRepository;

@Validated
@RequestMapping("/api/v1")
@RestController
public class LocationController {

    private final LocationRepository locationRepository;

    private static final HttpStatus SUCCESS = HttpStatus.OK;
    private static final HttpStatus ERROR = HttpStatus.INTERNAL_SERVER_ERROR;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    /* GET */
    @GetMapping("/locations")
    public ResponseEntity<Object> getAllLocations() {
        try {
            List<Location> locations = locationRepository.findAll();
            return ResponseHandler.give(locations, "Successfully get all locations", SUCCESS);
        } catch (Exception e) {
            return ResponseHandler.give(null, "Failed to get all locations", ERROR);
        }
    }

    /* POST */
    @PostMapping("/locations")
    public ResponseEntity<Object> createLocation(@RequestBody Location location) {
        try {
            Location newLocation = locationRepository.save(location);
            return ResponseHandler.give(newLocation, "Successfully create location", HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseHandler.give(null, "Failed to create location", ERROR);
        }
    }

    /* PUT */
    @PutMapping("/locations/{id}")
    public ResponseEntity<Object> updateLocation(@PathVariable String id, @RequestBody Location location) {
        try {
            Location updatedLocation = locationRepository.save(location);
            return ResponseHandler.give(updatedLocation, "Successfully update location", SUCCESS);
        } catch (Exception e) {
            return ResponseHandler.give(null, "Failed to update location", ERROR);
        }
    }

    /* DELETE */
    @DeleteMapping("/locations/{id}")
    public ResponseEntity<Object> deleteLocation(@PathVariable String id) {
        try {
            locationRepository.deleteById(Integer.parseInt(id));
            return ResponseHandler.give(null, "Successfully delete location", SUCCESS);
        } catch (Exception e) {
            return ResponseHandler.give(null, "Failed to delete location", ERROR);
        }
    }

    @DeleteMapping("/locations")
    public ResponseEntity<Object> deleteAllLocations() {
        try {
            locationRepository.deleteAll();
            return ResponseHandler.give(null, "Successfully delete all locations", SUCCESS);
        } catch (Exception e) {
            return ResponseHandler.give(null, "Failed to delete all locations", ERROR);
        }
    }
}
