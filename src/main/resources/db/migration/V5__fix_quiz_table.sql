-- Rename columns only if they exist
DO $$ 
BEGIN
    IF EXISTS (SELECT 1 FROM information_schema.columns 
               WHERE table_name = 'quiz' AND column_name = 'quiz_config') 
    THEN 
        ALTER TABLE quiz RENAME COLUMN quiz_config TO quiz_name;
    END IF;

    IF EXISTS (SELECT 1 FROM information_schema.columns 
               WHERE table_name = 'quiz' AND column_name = 'questions') 
    THEN 
        ALTER TABLE quiz RENAME COLUMN questions TO questions_list;
    END IF;
END $$;

-- Add new columns if they do not already exist
ALTER TABLE quiz ADD COLUMN IF NOT EXISTS created_date TIMESTAMP DEFAULT NOW();
ALTER TABLE quiz ADD COLUMN IF NOT EXISTS updated_date TIMESTAMP DEFAULT NOW();
ALTER TABLE quiz ADD COLUMN IF NOT EXISTS user_created VARCHAR(255);
ALTER TABLE quiz ADD COLUMN IF NOT EXISTS user_updated VARCHAR(255);
ALTER TABLE quiz ADD COLUMN IF NOT EXISTS quiz_version BIGINT DEFAULT 1;
ALTER TABLE quiz ADD COLUMN IF NOT EXISTS is_private BOOLEAN DEFAULT FALSE;
