CREATE TABLE IF NOT EXISTS answer (
    id UUID PRIMARY KEY,
    question_id UUID NOT NULL,
    text_answer TEXT,
    shape_answer TEXT
);
