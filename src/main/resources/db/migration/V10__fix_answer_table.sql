ALTER TABLE answer RENAME COLUMN colour_answer TO colour_answer_old;
ALTER TABLE answer ADD COLUMN colour_answer TEXT;
UPDATE answer SET colour_answer = CAST(colour_answer_old AS VARCHAR);
ALTER TABLE answer DROP COLUMN colour_answer_old;