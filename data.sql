create table account
(
	id int auto_increment	primary key,
	user_name varchar(32) null,
	password varchar(32) null,
	user_sex varchar(2) null,
	nick_name varchar(32) null
);
INSERT INTO account (user_name, password, user_sex, nick_name) VALUES ('json', '123456', 'M', 'json');