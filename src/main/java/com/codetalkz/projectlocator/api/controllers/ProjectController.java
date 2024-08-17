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
import com.codetalkz.projectlocator.api.repositories.ProjectRepository;

@CrossOrigin(origins = "http://localhost:8088")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;

    ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /* GET */
    @GetMapping("/")
    public ResponseEntity<List<Project>> getAllProjects(@RequestParam String requestParams) {
        return ResponseEntity.ok(projectRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable String id) {
        return ResponseEntity.ok(projectRepository.findById(Integer.parseInt(id)).get());
    }

    /* POST */
    @PostMapping("/")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectRepository.save(project));
    }

    /* PUT */
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable String id, @RequestBody Project project) {
        project.setId(Integer.parseInt(id));
        return ResponseEntity.ok(projectRepository.save(project));
    }

    /* DELETE */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProject(@PathVariable String id) {
        try {
            projectRepository.deleteById(Integer.parseInt(id));
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<Boolean> deleteAllProjects() {
        try {
            projectRepository.deleteAll();
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
