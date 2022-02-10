drop table if exists `person` CASCADE;
create table `person` (
    id bigint not null auto_increment,
    date_of_birth date,
    email varchar(255),
    first_name varchar(255),
    gender integer,
    last_name varchar(255),
    primary key (id)
);