ALTER TABLE
    project_locations
ADD
    CONSTRAINT fk_project_locations_project_id FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE ON UPDATE CASCADE,
ADD
    CONSTRAINT fk_project_locations_location_id FOREIGN KEY (location_id) REFERENCES locations (id) ON DELETE CASCADE ON UPDATE CASCADE;