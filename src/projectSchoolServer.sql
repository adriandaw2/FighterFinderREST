CREATE TABLE `user` (
	id INT(6),
	nick VARCHAR(30),
	email VARCHAR(100),
	password VARCHAR(100),
        ubication VARCHAR(100),
	skill INT(1),
        avaible TINYINT(1),
        showinmap TINYINT(1),
        glat DECIMAL(15),
        glon DECIMAL(15),
	id_profile INT(1) DEFAULT 2,
	id_objective INT(2) DEFAULT 1
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
	user_id INT(6),
	game_id INT(6)
)ENGINE=InnoDB;

CREATE TABLE `user_character`(
	user_id INT(6),
	character_id INT(6)
)ENGINE=InnoDB;

CREATE TABLE `user_skill_rates`(
	user_id_who_rate INT(6),
	user_rated INT(6),
	skill_rate INT(1)
)ENGINE=InnoDB;

CREATE TABLE `user_user_fav`(
        user_id INT(6),
        user_added_fav INT(6)
)ENGINE=InnoDB;

CREATE TABLE `user_user_recommend`(
        user_who_rec INT(6),
        user_to_rec INT(6),
        user_recommended INT(6)
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
	ADD PRIMARY KEY (user_id, game_id);
	
ALTER TABLE `user_character`
	ADD PRIMARY KEY (user_id, character_id);

ALTER TABLE `user_skill_rates`
	ADD PRIMARY KEY (user_id_who_rate, user_rated);

ALTER TABLE `user_user_fav`
        ADD PRIMARY KEY (user_id, user_added_fav);

ALTER TABLE `user_user_recommend`
        ADD PRIMARY KEY (user_who_rec, user_to_rec, user_recommended);

ALTER TABLE `user`
	ADD FOREIGN KEY (id_objective) REFERENCES `objective` (id),
	ADD FOREIGN KEY (id_profile) REFERENCES `profile` (id),
	MODIFY nick VARCHAR(30) NOT NULL UNIQUE,
	MODIFY email VARCHAR(100) NOT NULL UNIQUE,
	MODIFY password VARCHAR(100) NOT NULL,
        MODIFY skill INT(1) DEFAULT 3 NOT NULL,
        MODIFY ubication VARCHAR(100)DEFAULT 'Somewhere' NOT NULL,
        MODIFY avaible TINYINT(1) DEFAULT 0 NOT NULL,
        MODIFY showinmap TINYINT(1) DEFAULT 1 NOT NULL,
        MODIFY glat DECIMAL(15) DEFAULT 0.0 NOT NULL,
        MODIFY glon DECIMAL(15) DEFAULT 0.0 NOT NULL;        

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

ALTER TABLE `user_user_fav`
        ADD FOREIGN KEY (user_id) REFERENCES `user` (id),
        ADD FOREIGN KEY (user_added_fav) REFERENCES `user` (id);

ALTER TABLE `user_user_recommend`
        ADD FOREIGN KEY (user_who_rec) REFERENCES `user` (id),
        ADD FOREIGN KEY (user_to_rec) REFERENCES `user` (id),
        ADD FOREIGN KEY (user_recommended) REFERENCES `user` (id);


	

	
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


DELIMITER $$
CREATE TRIGGER after_update_skill_rate AFTER UPDATE 
	ON `user_skill_rates` FOR EACH ROW

BEGIN
	
	DECLARE mResult INT(1);
	SELECT AVG(skill_rate) INTO mResult FROM `user_skill_rates` WHERE `user_rated` = NEW.user_rated;

	UPDATE `user` SET `skill` = mResult WHERE `id` = NEW.user_rated;

END; $$

DELIMITER ;

