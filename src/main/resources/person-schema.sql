DROP TABLE if EXISTS `person` CASCADE;
CREATE TABLE person (
    id bigint not null auto_increment,
    date_of_birth date,
    email varchar(255),
    first_name varchar(255),
    gender integer,
    last_name varchar(255),
    primary key (id)
);