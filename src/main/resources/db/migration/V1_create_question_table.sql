create table question (
    id text not null,
    is_multuple_answer_question boolean not null,
    media_url text,
    answer_list text[] not null
)