CREATE TABLE USER_TABLE(
user_id varchar(20) primary key,
login varchar(20) not null,
password varchar(20) not null,
email varchar(20) not null,
phone_number varchar(15),
user_name varchar(40) not null,
user_surname varchar(40) not null
);