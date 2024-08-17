package com.codetalkz.projectlocator.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codetalkz.projectlocator.api.models.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {}
