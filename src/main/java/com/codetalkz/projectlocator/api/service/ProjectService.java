package com.codetalkz.projectlocator.api.service;

import java.util.ArrayList;
import java.util.List;

import com.codetalkz.projectlocator.api.models.Project;

public class ProjectService {
    static List<Project> projects = new ArrayList<>();
    private int id = 0;

    /* GET */
    public List<Project> getAll() {
        return projects;
    }

    public Project getById(int id) {
        for (Project project : projects) {
            if (project.getId() == id) {
                return project;
            }
        }
        return null;
    }

    /* CREATE */
    public Project create(Project project) {
        project.setId(id++);
        projects.add(project);
        this.increaseId();
        return project;
    }

    /* UPDATE */
    public Project update(int id, Project project) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getId() == id) {
                projects.set(i, project);
                return project;
            }
        }
        return null;
    }

    /* DELETE */
    public boolean deleteById(int id) {
        return projects.removeIf(project -> project.getId() == id);
    }

    public boolean deleteAll() {
        projects.clear();
        return projects.isEmpty();
    }

    private void increaseId() {
        this.id++;
    }
}
