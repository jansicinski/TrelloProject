CREATE TABLE `trelloboard` (
  board_id VARCHAR(50),
  name varchar(50),
  PRIMARY KEY (`board_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `trellolist` (
  list_id VARCHAR(50),
  board_id int(10),
  PRIMARY KEY (`list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `trellocard` (
  card_id VARCHAR(50),
  list_id int(10),
  PRIMARY KEY (`card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;