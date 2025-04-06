CREATE TABLE IF NOT EXISTS quiz (
    id UUID PRIMARY KEY, 
    quiz_config TEXT NOT NULL,  -- H2 does not support JSON, stored as TEXT
    questions TEXT  -- Stored as TEXT (comma-separated UUIDs)
);