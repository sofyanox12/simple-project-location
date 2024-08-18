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

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    /* GET */
    @GetMapping("/locations")
    public ResponseEntity<Object> getAllLocations() {
        try {
            List<Location> locations = locationRepository.findAll();
            return ResponseHandler.give(locations, "Successfully get all locations", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.give(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* POST */
    @PostMapping("/locations")
    public ResponseEntity<Object> createLocation(@RequestBody Location location) {
        try {
            Location newLocation = locationRepository.save(location);
            return ResponseHandler.give(newLocation, "Successfully create location", HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseHandler.give(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* PUT */
    @PutMapping("/locations/{id}")
    public ResponseEntity<Object> updateLocation(@PathVariable String id, @RequestBody Location location) {
        try {
            Location foundLocation = locationRepository.findById(Integer.parseInt(id)).orElse(null);
            if (foundLocation == null) {
                return ResponseHandler.give(null, "Location not found", HttpStatus.NOT_FOUND);
            }

            foundLocation.update(location);

            return ResponseHandler.give(foundLocation, "Successfully update the location", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.give(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* DELETE */
    @DeleteMapping("/locations/all")
    public ResponseEntity<Object> deleteAllLocations() {
        try {
            List<Location> locations = locationRepository.findAll();

            if (locations.isEmpty()) {
                return ResponseHandler.give(null, "No location deleted", HttpStatus.NOT_FOUND);
            }

            locationRepository.deleteAll();

            return ResponseHandler.give(null, "Successfully delete all " + locations.size() + " locations",
                    HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.give(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<Object> deleteLocation(@PathVariable String id) {
        try {
            Location foundLocation = locationRepository.findById(Integer.parseInt(id)).orElse(null);

            if (foundLocation == null) {
                return ResponseHandler.give(null, "Location not found", HttpStatus.NOT_FOUND);
            }

            locationRepository.delete(foundLocation);
            return ResponseHandler.give(foundLocation, "Successfully delete the location", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.give(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
