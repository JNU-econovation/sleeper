drop table if exists user CASCADE;
drop table if exists deal CASCADE;
drop table if exists character CASCADE ;
drop table if exists sleep CASCADE ;
drop table if exists diary CASCADE ;

create table user (
    id bigint generated by default as identity,
    user_role_type varchar(255),
    user_age bigint,
    user_id varchar(255),
    user_message varchar(255),
    user_nick_name varchar(20),
    user_password varchar(255),
    user_goal_sleep_time time,
    user_goal_wake_time time,
    character_fk bigint,
    deal_fk bigint,
    primary key (id)
);

create table deal (
   id bigint generated by default as identity,
   money_change integer,
   money_date timestamp,
   money_now integer,
   primary key (id)
);

create table character (
   id bigint generated by default as identity,
   character_color varchar(255),
   character_experience integer,
   character_level bigint,
   character_speech_bubble varchar(255),
   character_status varchar(255),
   primary key (id)
);

create table diary (
   id bigint generated by default as identity,
   diary_content text,
   diary_delete_date date,
   SAVING_DATE_TIME timestamp,
   saving_date date,
   user_fk bigint,
   primary key (id)
);

create table sleep (
   id bigint generated by default as identity,
   SAVING_DATE_TIME timestamp,
   actual_wake_time TIMESTAMP,
   set_sleep_time TIMESTAMP,
   set_wake_time TIMESTAMP,
   saving_date DATE,
   user_fk bigint,
   primary key (id)
);

INSERT INTO USER (USER_ROLE_TYPE, USER_AGE, USER_ID, USER_MESSAGE, USER_NICK_NAME, USER_PASSWORD, user_goal_wake_time, user_goal_sleep_time, deal_fk, character_fk) VALUES ('ADMIN',24,'sleeper','관리자다','관리자','sleeper123@','07:30','23:30',1,1);
INSERT INTO deal(MONEY_CHANGE, MONEY_DATE, MONEY_NOW) VALUES (10,'2023-01-02T01:36',10);
INSERT INTO character (character_color, character_experience, character_level, character_speech_bubble, character_status) VALUES ('GRAY',0,0,'HELLO','NO_SLEEP');
INSERT INTO DIARY (diary_content, saving_date, SAVING_DATE_TIME, user_fk) VALUES ('오늘도 너무나 행복한 하루였습니다!!','2023-01-01','2023-01-02T01:36',1);
INSERT INTO SLEEP (SAVING_DATE_TIME, actual_wake_time, set_sleep_time, set_wake_time,saving_date,user_fk) VALUES ('2023-01-02T01:36','2023-01-02T08:10','2023-01-02T00:10','2023-01-02T07:00','2023-01-01',1);
