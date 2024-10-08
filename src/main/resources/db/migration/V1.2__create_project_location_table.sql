CREATE TABLE IF NOT EXISTS project_locations (
    project_id      INT NOT NULL,
    location_id     INT NOT NULL,
    PRIMARY KEY (project_id, location_id),
    FOREIGN KEY (project_id)    REFERENCES projects (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (location_id)   REFERENCES locations (id) ON DELETE CASCADE ON UPDATE CASCADE
);