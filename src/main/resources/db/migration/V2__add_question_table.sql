create table question (
    id bigint not null,
    is_multuple_answer_question boolean not null,
    media_url text,
    answer_list text[] not null,
    correct_answer text not null
);