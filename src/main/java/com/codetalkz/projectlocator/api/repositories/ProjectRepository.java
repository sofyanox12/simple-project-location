package com.codetalkz.projectlocator.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codetalkz.projectlocator.api.models.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
