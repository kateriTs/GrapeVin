hUSE winedb;
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
