CREATE TABLE quiz_session (
    id UUID PRIMARY KEY,
    quiz_id UUID NOT NULL,
    users_connected TEXT,  -- Storing as TEXT (comma-separated UUIDs)
    time_started DATE,
    time_ended DATE
);
