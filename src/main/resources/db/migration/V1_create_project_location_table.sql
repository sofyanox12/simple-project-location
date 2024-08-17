CREATE TABLE IF NOT EXIST project_locations
(
    project_id INTEGER NOT NULL,
    location_id INTEGER NOT NULL,
    PRIMARY KEY (project_id, location_id),
    FOREIGN KEY (project_id) REFERENCES projects (id),
    FOREIGN KEY (location_id) REFERENCES locations (id)
);