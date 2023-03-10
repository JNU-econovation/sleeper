drop table if exists user CASCADE;
drop table if exists money CASCADE;
drop table if exists character CASCADE ;
drop table if exists sleep CASCADE ;
drop table if exists diary CASCADE ;
drop table if exists user_sleep_info CASCADE;

create table user (
    id bigint generated by default as identity,
    user_role_type varchar(255),
    user_age bigint not null,
    user_id varchar(255) not null,
    user_nick_name varchar(20) not null,
    user_password varchar(255) not null,
    character_fk bigint,
    user_sleep_info_fk bigint,
    primary key (id)
);

create table user_sleep_info (
     id bigint generated by default as identity,
     user_goal_sleep_time TIME,
     user_goal_wake_time TIME,
     user_minimum_sleep_time TIME,
     primary key (id)
);

create table money (
    id bigint generated by default as identity,
    money_date timestamp,
    money_holding_cash integer,
    user_fk bigint,
    primary key (id)
);

create table character (
    id bigint generated by default as identity,
    character_color varchar(255),
    character_experience integer,
    character_level bigint,
    character_status varchar(255),
    primary key (id)
);

create table diary (
    id bigint generated by default as identity,
    diary_content TEXT,
    diary_delete_date DATE,
    diary_date date,
    DIARY_WRITING_TIME timestamp,
    user_fk bigint,
    primary key (id)
);

create table sleep (
    id bigint generated by default as identity,
    actual_sleep_time TIMESTAMP,
    actual_wake_time TIMESTAMP,
    set_sleep_time TIMESTAMP,
    set_wake_time TIMESTAMP,
    sleep_date date,
    user_fk bigint,
    primary key (id)
);

INSERT INTO USER (USER_ROLE_TYPE, USER_AGE, USER_ID, USER_NICK_NAME, USER_PASSWORD, character_fk, user_sleep_info_fk) VALUES ('USER',24,'sleeper','테스터','sleeper12@@',1,1);
INSERT INTO user_sleep_info(user_goal_sleep_time, user_goal_wake_time, user_minimum_sleep_time) values ('01:40','07:50','6:00');
INSERT INTO SLEEP (actual_sleep_time, actual_wake_time, set_sleep_time, set_wake_time,sleep_date,user_fk) VALUES ('2023-03-05T01:36','2023-03-05T08:10','2023-03-05T01:30','2023-03-05T09:00','2023-03-04',1);
INSERT INTO money(MONEY_DATE, money_holding_cash, user_fk) VALUES ('2023-03-06T01:36',0, 1);
INSERT INTO character (character_color, character_experience, character_level, character_status) VALUES ('YELLOW',0,1,'NO_SLEEP');
INSERT INTO DIARY (diary_content,diary_date, DIARY_WRITING_TIME, user_fk) VALUES ('오늘도 너무나 행복한 하루였습니다!!','2023-03-05','2023-03-06T01:36',1);

