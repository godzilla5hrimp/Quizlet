-- Rename column if it exists
ALTER TABLE answer ALTER COLUMN question_id RENAME TO colour_answer;

-- Add new column if it does not exist
ALTER TABLE answer ADD COLUMN IF NOT EXISTS is_right BOOLEAN DEFAULT FALSE;