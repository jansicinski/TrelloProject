CREATE TABLE `trelloboard` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  board_id VARCHAR(50),
  name varchar(50),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `trellolist` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  list_id VARCHAR(50),
  board_id int(10),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `trellocard` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  card_id VARCHAR(50),
  list_id int(10),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



