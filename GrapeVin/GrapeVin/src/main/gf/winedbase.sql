USE winedb;
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
INSERT INTO WhiteWines(w_name, w_color, w_flavor) VALUES ("winename", "white", "dry");
SELECT * FROM WhiteWines;
SELECT * FROM RedWines;
SELECT * FROM RoseWines;

DELETE FROM WhiteWines;
DELETE FROM RedWines;
DELETE FROM RoseWines;

DROP TABLE RedWines;
DROP TABLE WhiteWines;
DROP TABLE RoseWines;

ALTER TABLE redwines DROP COLUMN w_key;
ALTER TABLE whitewines DROP COLUMN w_key;
ALTER TABLE rosewines DROP COLUMN w_key;

DELETE FROM WhiteWines WHERE w_name = Albariño;