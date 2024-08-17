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

import com.codetalkz.projectlocator.api.models.Project;
import com.codetalkz.projectlocator.api.service.ProjectService;

@CrossOrigin(origins = "http://localhost:8088")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    
    private final ProjectService projectService = new ProjectService();

    /* GET */
    @GetMapping("")
    public ResponseEntity<List<Project>> getAllProjects(@RequestParam String param) {
        try {
            List<Project> projects = projectService.getAll();
            return ResponseEntity.ok(projects);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable String id) {
        try {
            Project project = projectService.getById(Integer.parseInt(id));
            return ResponseEntity.ok(project);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /* POST */
    @PostMapping("")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        try {
            Project createdProject = projectService.create(project);
            return ResponseEntity.ok(createdProject);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /* PUT */
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable String id, @RequestBody Project project) {
        try {
            Project updatedProject = projectService.update(Integer.parseInt(id), project);
            return ResponseEntity.ok(updatedProject);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /* DELETE */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProject(@PathVariable String id) {
        try {
            boolean deleted = projectService.deleteById(Integer.parseInt(id));
            return ResponseEntity.ok(deleted);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Boolean> deleteAllProjects() {
        try {
            boolean deleted = projectService.deleteAll();
            return ResponseEntity.ok(deleted);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
