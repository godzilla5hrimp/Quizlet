CREATE TABLE quiz_questions (
    quiz_id UUID NOT NULL,
    question_id UUID NOT NULL,
    PRIMARY KEY (quiz_id, question_id),
    FOREIGN KEY (quiz_id) REFERENCES quiz(id)
);
