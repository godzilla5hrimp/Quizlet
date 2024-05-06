create table quiz (
    id text not null, 
    creation_date date not null,
    update_date date, 
    user_update text,
    user_create text not null,
    quiz_version bigint not null,
    json_quiz_export json
)