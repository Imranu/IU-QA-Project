drop table if exists `springprojectdatabase` CASCADE;
CREATE TABLE `springprojectdatabase` (
    id BIGINT AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email_name VARCHAR(255),
    date_of_birth DATE,
    gender VARCHAR(6),
    PRIMARY KEY (id)
);