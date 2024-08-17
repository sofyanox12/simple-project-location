CREATE TABLE IF NOT EXISTS projects (
    id          INT          GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    client      VARCHAR(255) NOT NULL,
    start_date  DATE         NOT NULL,
    end_date    DATE         NOT NULL,
    leader      VARCHAR(255) NOT NULL,
    description TEXT,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);