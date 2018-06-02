CREATE TABLE `trelloboard` (
  `board_id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`board_id`),
  UNIQUE KEY `board_id_UNIQUE` (`board_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `trellocard` (
  `card_id` varchar(50) NOT NULL,
  `list_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`card_id`),
  UNIQUE KEY `card_id_UNIQUE` (`card_id`),
  KEY `list_id_idx` (`list_id`),
  CONSTRAINT `list_id` FOREIGN KEY (`list_id`) REFERENCES `trellolist` (`list_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `trellolist` (
  `list_id` varchar(50) NOT NULL,
  `board_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`list_id`),
  UNIQUE KEY `list_id_UNIQUE` (`list_id`),
  KEY `board_id_idx` (`board_id`),
  CONSTRAINT `board_id` FOREIGN KEY (`board_id`) REFERENCES `trelloboard` (`board_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
