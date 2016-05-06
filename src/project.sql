CREATE DATABASE fighterfinderdb DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;


CREATE USER 'adminff'@'localhost' IDENTIFIED BY 'password1234@';
CREATE USER 'adminff'@'%' IDENTIFIED BY 'password1234@';

CREATE USER 'standuser'@'localhost' IDENTIFIED BY 'normal4321@';
CREATE USER 'standuser'@'%' IDENTIFIED BY 'normal4321@';

GRANT ALL PRIVILEGES ON fighterfinderdb.* TO 'adminff'@'localhost' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON fighterfinderdb.* TO 'adminff'@'%' WITH GRANT OPTION;

GRANT SELECT, INSERT, DELETE ON fighterfinderdb.* TO 'standuser'@'localhost';
GRANT SELECT, INSERT, DELETE ON fighterfinderdb.* TO 'standuser'@'%';

USE fighterfinderdb;

CREATE TABLE `user` (
	id INT(6),
	nick VARCHAR(30),
	email VARCHAR(100),
	password VARCHAR(100),
	skill INT(1),
	id_profile INT(1),
	id_objective INT(6)
)ENGINE=InnoDB;

CREATE TABLE `game`(
	id INT(6),
	name VARCHAR(100)
)ENGINE=InnoDB;

CREATE TABLE `character`(
	id INT(6),
	name VARCHAR(50),
	id_game INT(6)
)ENGINE=InnoDB;

CREATE TABLE `objective`(
	id INT(6),
	message VARCHAR(100)
)ENGINE=InnoDB;

CREATE TABLE `profile`(
	id INT(1),
	profiletype VARCHAR(15)
)ENGINE=InnoDB;

CREATE TABLE `user_game`(
	id INT(6),
	user_id INT(6),
	game_id INT(6)
)ENGINE=InnoDB;

CREATE TABLE `user_character`(
	id INT(6),
	user_id INT(6),
	character_id INT(6)
)ENGINE=InnoDB;

CREATE TABLE `user_skill_rates`(
	id INT(8),
	user_id_who_rate INT(6),
	user_rated INT(6),
	skill_rate INT(5)
)ENGINE=InnoDB;

ALTER TABLE `objective`
	MODIFY id INT AUTO_INCREMENT PRIMARY KEY;

ALTER TABLE `user`
	MODIFY id INT AUTO_INCREMENT PRIMARY KEY;
	
ALTER TABLE `game`
	MODIFY id INT AUTO_INCREMENT PRIMARY KEY;

ALTER TABLE `character`
	MODIFY id INT AUTO_INCREMENT PRIMARY KEY;

ALTER TABLE `profile`
	MODIFY id INT AUTO_INCREMENT PRIMARY KEY;
	
ALTER TABLE `user_game`
	MODIFY id INT AUTO_INCREMENT PRIMARY KEY;
	
ALTER TABLE `user_character`
	MODIFY id INT AUTO_INCREMENT PRIMARY KEY;

ALTER TABLE `user_skill_rates`
	MODIFY id INT AUTO_INCREMENT PRIMARY KEY;

ALTER TABLE `user`
	ADD FOREIGN KEY (id_objective) REFERENCES `objective` (id),
	ADD FOREIGN KEY (id_profile) REFERENCES `profile` (id),
	MODIFY nick VARCHAR(30) NOT NULL UNIQUE,
	MODIFY email VARCHAR(100) NOT NULL UNIQUE,
	MODIFY password VARCHAR(100) NOT NULL,
	MODIFY skill INT(1) NOT NULL,
	ALTER skill SET DEFAULT 3,
	ALTER id_profile SET DEFAULT 2,
	ALTER id_objective SET DEFAULT 1;

ALTER TABLE `game`
	MODIFY name VARCHAR(100) NOT NULL;

ALTER TABLE `character`
	MODIFY name VARCHAR(50) NOT NULL,
	ADD FOREIGN KEY (id_game) REFERENCES `game` (id);
	
ALTER TABLE `objective`
	MODIFY message VARCHAR(100) NOT NULL;

ALTER TABLE `profile`
	MODIFY profiletype VARCHAR(15) NOT NULL;
	
ALTER TABLE `user_game`
	ADD FOREIGN KEY (user_id) REFERENCES `user` (id),
	ADD FOREIGN KEY (game_id) REFERENCES `game` (id);

ALTER TABLE `user_character`
	ADD FOREIGN KEY (user_id) REFERENCES `user` (id),
	ADD FOREIGN KEY (character_id) REFERENCES `character` (id);
	
ALTER TABLE `user_skill_rates`
	ADD FOREIGN KEY (user_id_who_rate) REFERENCES `user` (id),
	ADD FOREIGN KEY (user_rated) REFERENCES `user` (id),
	MODIFY skill_rate INT(1) NOT NULL;

INSERT INTO `profile` (profiletype) VALUES
	('admin'), ('user');

INSERT INTO `game` (name) VALUES
	('Street Fighter V'), ('BlazBlue Chronophantasma Extend'), ('Guilty Gear Xrd - Revelator'), ('Super Smash Bros. for Wii u'), ('Super Smash Bros. Melee'),('Killer Instinct'), ('Mortal Kombat X'), ('Skullgirls'), ('Under Night In-Birth: Exe Late'), ('Arcana Heart 3: Love Max'), ('Dengeki Bunko Fighting Climax: Ignition');

INSERT INTO `character` (name, id_game) VALUES
	('Ryu', 1), ('Ken', 1), ('Ragna the Bloodedge', 2), ('Jin Kisaragi', 2), ('Sol Badguy', 3), ('Jam Kuradoberi', 3);
	
INSERT INTO `objective` (message) VALUES
	('Searching for friends'), ('Having fun'), ('Compete'), ('I want to be the very best');
	
INSERT INTO `user` (nick, email, password, skill, id_profile, id_objective) VALUES 
	('FFAdmin', 'ffadmin@gmail.com', 'admin@pass1234', 5, 1, 1),('Nemo', 'nemoemail@gmail.com', 'nemo1234', 3, 2, 4), ('Person', 'personemail@gmail.com', 'person1234', 3, 2, 2);
	
INSERT INTO `user_game` (user_id, game_id) VALUES
	(1, 1), (1, 2), (1, 3), (2, 9), (2, 10),
	(1, 1), (1, 2), (1, 3), (2, 9), (2, 10);
	
	
/*TRIGGER*/	
DELIMITER $$
CREATE TRIGGER after_insert_skill_rate AFTER INSERT 
	ON `user_skill_rates` FOR EACH ROW

BEGIN
	
	DECLARE mResult INT(1);
	SELECT AVG(skill_rate) INTO mResult FROM `user_skill_rates` WHERE `user_rated` = NEW.user_rated;

	UPDATE `user` SET `skill` = mResult WHERE `id` = NEW.user_rated;

END; $$

DELIMITER ;

