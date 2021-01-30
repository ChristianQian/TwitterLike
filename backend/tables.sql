create table users(
       id Integer primary key auto_increment,
       login VARCHAR(32) unique,
       password BLOB,
       prenom VARCHAR(255),
       nom VARCHAR(255));

create table friends(
       from_id Integer,
       to_id Integer,
       valid BOOLEAN,
       primary key (from_id, to_id));

create table login(
       ikey integer,
       id integer,
       root boolean);
