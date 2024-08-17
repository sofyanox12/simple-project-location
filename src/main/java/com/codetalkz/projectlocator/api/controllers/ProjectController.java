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
import com.codetalkz.projectlocator.api.models.Project;
import com.codetalkz.projectlocator.api.repositories.ProjectRepository;

@Validated
@RequestMapping("/api/v1")
@RestController
public class ProjectController {

    private final ProjectRepository projectRepository;

    ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /* GET */
    @GetMapping("/projects")
    public ResponseEntity<Object> getAllProjects() {
        try {
            List<Project> projects = projectRepository.findAll();
            return ResponseHandler.give(projects, "Successfully get all projects", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.give(null, "Failed to get all projects", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<Object> getProjectById(@PathVariable String id) {
        try {
            Project project = projectRepository.findById(Integer.parseInt(id)).orElse(null);
            if (project == null) {
                return ResponseHandler.give(null, "Project not found", HttpStatus.NOT_FOUND);
            }
            return ResponseHandler.give(project, "Successfully get project", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.give(null, "Failed to get project", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* POST */
    @PostMapping("/projects")
    public ResponseEntity<Object> createProject(@RequestBody Project project) {
        try {
            Project newProject = projectRepository.save(project);
            return ResponseHandler.give(newProject, "Successfully create project", HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseHandler.give(null, "Failed to create project", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* PUT */
    @PutMapping("/projects/{id}")
    public ResponseEntity<Object> updateProject(@PathVariable String id, @RequestBody Project project) {
        try {
            project.setId(Integer.parseInt(id));
            Project updatedProject = projectRepository.save(project);
            return ResponseHandler.give(updatedProject, "Successfully update project", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.give(null, "Failed to update project", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* DELETE */
    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Object> deleteProject(@PathVariable String id) {
        try {
            projectRepository.deleteById(Integer.parseInt(id));
            return ResponseHandler.give(null, "Successfully delete project", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.give(null, "Failed to delete project", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<Object> deleteAllProjects() {
        try {
            projectRepository.deleteAll();
            return ResponseHandler.give(null, "Successfully delete all projects", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.give(null, "Failed to delete all projects", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
