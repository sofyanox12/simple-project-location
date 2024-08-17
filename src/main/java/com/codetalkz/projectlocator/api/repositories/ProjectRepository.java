package com.codetalkz.projectlocator.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codetalkz.projectlocator.api.models.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {}
