package com.codetalkz.projectlocator.api.service;

import java.util.ArrayList;
import java.util.List;

import com.codetalkz.projectlocator.api.models.Location;

public class LocationService {
    static List<Location> locations = new ArrayList<>();
    private int id = 0;

    /* GET */
    public List<Location> getAll() {
        return locations;
    }

    public static Location getById(int id) {
        for (Location location : locations) {
            if (location.getId() == id) {
                return location;
            }
        }
        return null;
    }

    /* POST */
    public Location create(Location location) {
        location.setId(this.id++);
        locations.add(location);
        return location;
    }

    /* PUT */
    public static Location update(int id, Location location) {
        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).getId() == id) {
                locations.set(i, location);
                return location;
            }
        }
        return null;
    }

    /* DELETE */
    public static boolean deleteById(int id) {
        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).getId() == id) {
                locations.remove(i);
                return true;
            }
        }
        return false;
    }
}
