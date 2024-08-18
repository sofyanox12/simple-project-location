package com.codetalkz.projectlocator.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codetalkz.projectlocator.api.models.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
}
