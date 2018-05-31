CREATE TABLE `TrelloBoard` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  trello_id VARCHAR(50),
  name varchar(50),
  PRIMARY KEY (`db_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `TrelloList` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  trello_id VARCHAR(50),
  board_id int(10),
  PRIMARY KEY (`db_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `TrelloCard` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  trello_id VARCHAR(50),
  list_id int(10),
  PRIMARY KEY (`db_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



