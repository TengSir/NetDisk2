
/* Drop Tables */

DROP TABLE USERS;


CREATE DATABASE netdisk2 CHARSET=UTF8;

/* Create Tables */

CREATE TABLE USERS
(
	USERNAME VARCHAR(20) NOT NULL,
	PASSWORD VARCHAR(20) NOT NULL,
	SEX CHAR(1) NOT NULL,
	AGE INT,
	EMAIL VARCHAR(100),
	TEL VARCHAR(20),
	PRIMARY KEY (USERNAME)
) CHARSET=UTF8;

