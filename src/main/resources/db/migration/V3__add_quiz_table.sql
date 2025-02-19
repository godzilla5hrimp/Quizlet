create table if not exists quiz (
    id bigint not null, 
    quiz_config json not null,
    questions text[]
);