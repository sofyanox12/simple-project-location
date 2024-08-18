package com.codetalkz.projectlocator.api.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.codetalkz.projectlocator.api.dto.ProjectDto;
import com.codetalkz.projectlocator.api.handlers.ResponseHandler;
import com.codetalkz.projectlocator.api.models.Location;
import com.codetalkz.projectlocator.api.models.Project;
import com.codetalkz.projectlocator.api.repositories.LocationRepository;
import com.codetalkz.projectlocator.api.repositories.ProjectRepository;
import com.codetalkz.projectlocator.utils.Formatter;

@Validated
@RequestMapping("/api/v1")
@RestController
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final LocationRepository locationRepository;

    ProjectController(ProjectRepository projectRepository, LocationRepository locationRepository) {
        this.projectRepository = projectRepository;
        this.locationRepository = locationRepository;
    }

    /* GET */
    @GetMapping("/projects")
    public ResponseEntity<Object> getAllProjects() {
        try {
            List<Project> projects = projectRepository.findAll();
            return ResponseHandler.give(projects, "Successfully retrieved all projects!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.give(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<Object> getProjectById(@PathVariable String id) {
        try {
            Project project = projectRepository.findById(Integer.parseInt(id)).orElse(null);
            if (project == null) {
                return ResponseHandler.give(null, "Project not found", HttpStatus.NOT_FOUND);
            }
            return ResponseHandler.give(project, "Successfully retrieved the project!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.give(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* POST */
    @PostMapping("/projects")
    public ResponseEntity<Object> createProject(@RequestBody ProjectDto project) {
        try {
            Set<Location> locations = getLocationsByIds(project.getLocationsIds());

            Project savedProject = projectRepository.save(Project.builder()
                    .name(project.getName())
                    .client(project.getClient())
                    .startDate(Formatter.toLocalDateTime(project.getStartDate()))
                    .endDate(Formatter.toLocalDateTime(project.getEndDate()))
                    .leader(project.getLeader())
                    .description(project.getDescription())
                    .locations(locations)
                    .build());

            return ResponseHandler.give(savedProject, "Successfully created a project!", HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseHandler.give(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* PUT */
    @PutMapping("/projects/{id}")
    public ResponseEntity<Object> updateProject(@PathVariable String id, @RequestBody ProjectDto project) {
        try {
            Project existingProject = projectRepository.findById(Integer.parseInt(id)).orElse(null);

            if (existingProject == null) {
                return ResponseHandler.give(null, "Selected project not found!", HttpStatus.NOT_FOUND);
            }

            Set<Location> locations = getLocationsByIds(project.getLocationsIds());

            Project newData = new Project();
            newData.setName(project.getName());
            newData.setClient(project.getClient());
            newData.setStartDate(Formatter.toLocalDateTime(project.getStartDate()));
            newData.setEndDate(Formatter.toLocalDateTime(project.getEndDate()));
            newData.setLeader(project.getLeader());
            newData.setDescription(project.getDescription());
            newData.setLocations(locations);
            
            existingProject.update(newData);

            Project updatedProject = projectRepository.save(existingProject);

            return ResponseHandler.give(updatedProject, "Successfully updated the project!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.give(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* DELETE */
    @DeleteMapping("/projects/all")
    public ResponseEntity<Object> deleteAllProjects() {
        try {
            List<Project> projects = projectRepository.findAll();

            if (projects.isEmpty()) {
                return ResponseHandler.give(null, "No project deleted", HttpStatus.NOT_FOUND);
            }

            projectRepository.deleteAll();

            return ResponseHandler.give(null, "Successfully deleted all projects!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.give(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Object> deleteProject(@PathVariable String id) {
        try {
            Project foundProject = projectRepository.findById(Integer.parseInt(id)).orElse(null);

            if (foundProject == null) {
                return ResponseHandler.give(null, "Project not found", HttpStatus.NOT_FOUND);
            }

            projectRepository.delete(foundProject);
            return ResponseHandler.give(foundProject, "Successfully deleted the project!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.give(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Set<Location> getLocationsByIds(Integer[] locationIds) {
        if (locationIds.length == 0) {
            return new HashSet<>();
        }

        Set<Location> locations = new HashSet<>();
        for (Integer locationId : locationIds) {
            Location location = locationRepository.findById(locationId).orElse(null);
            if (location != null)
                locations.add(location);
            else
                throw new IllegalArgumentException("Location with id: " + locationId + " not found.");
        }
        return locations;
    }
}
