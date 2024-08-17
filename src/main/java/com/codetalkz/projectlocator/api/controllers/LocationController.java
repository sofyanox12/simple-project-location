package com.codetalkz.projectlocator.api.controllers;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codetalkz.projectlocator.api.models.Location;
import com.codetalkz.projectlocator.api.repositories.LocationRepository;

@CrossOrigin(origins = "http://localhost:8088")
@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    /* GET */
    @GetMapping("/")
    public ResponseEntity<List<Location>> getAllLocations(@RequestParam String requestParams) {
        return ResponseEntity.ok(locationRepository.findAll());
    }

    /* POST */
    @PostMapping("/")
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        return ResponseEntity.ok(locationRepository.save(location));
    }

    /* PUT */
    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable String id, @RequestBody Location location) {
        location.setId(Integer.parseInt(id));
        return ResponseEntity.ok(locationRepository.save(location));
    }

    /* DELETE */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteLocation(@PathVariable String id) {
        try {
            locationRepository.deleteById(Integer.parseInt(id));
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/")
    public ResponseEntity<Boolean> deleteAllLocations() {
        try {
            locationRepository.deleteAll();
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
