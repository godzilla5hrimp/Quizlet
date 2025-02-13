create table quiz (
    id text not null, 
    quiz_config json not null,
    questions text[]
);