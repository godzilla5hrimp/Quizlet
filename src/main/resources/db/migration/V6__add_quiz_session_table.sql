create table quiz_session (
    id bigint not null,
    quiz_id bigint not null,
    users_connected bigint[],
    time_started date,
    time_ended date
);