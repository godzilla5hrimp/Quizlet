CREATE TABLE question (
    id UUID PRIMARY KEY,
    is_multiple_answer_question BOOLEAN NOT NULL,
    media_url TEXT,
    answer_list TEXT NOT NULL,  -- H2 does not support arrays natively, storing as TEXT (comma-separated)
    correct_answer TEXT NOT NULL
);