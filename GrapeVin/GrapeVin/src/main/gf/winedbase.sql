USE winedb;
CREATE TABLE users (
	username VARCHAR(8) NOT NULL UNIQUE,
	user_password VARCHAR(8) NOT NULL,
	PRIMARY KEY (username)
);

CREATE TABLE QuizAnswers (
	username VARCHAR(8) NOT NULL,
	w_name1 VARCHAR(45) NOT NULL,
	w_name2 VARCHAR(45) NOT NULL,
	w_name3 VARCHAR(45) NOT NULL,
	FOREIGN KEY (username) REFERENCES users(username)
);

CREATE TABLE WhiteWines (
	w_name VARCHAR(45) NOT NULL UNIQUE,
	w_color VARCHAR(45) NOT NULL,
	w_flavor VARCHAR(45) NOT NULL,
    PRIMARY KEY (w_name)
    );

CREATE TABLE RedWines (
	w_name VARCHAR(45) NOT NULL UNIQUE,
	w_color VARCHAR(45) NOT NULL,
	w_flavor VARCHAR(45) NOT NULL,
    PRIMARY KEY (w_name)
);

CREATE TABLE RoseWines (
	w_name VARCHAR(45) NOT NULL UNIQUE,
	w_color VARCHAR(45) NOT NULL,
	w_flavor VARCHAR(45) NOT NULL,
    PRIMARY KEY (w_name)
);

SELECT * FROM WhiteWines;
SELECT * FROM RedWines;
SELECT * FROM RoseWines;

DELETE FROM WhiteWines;
DELETE FROM RedWines;
DELETE FROM RoseWines;

DROP TABLE RedWines;
DROP TABLE WhiteWines;
DROP TABLE RoseWines;
