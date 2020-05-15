-- liquibase formatted sql

-- changeset clayton:2


CREATE TABLE `tb_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_profile` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `rl_user_profile` (
  `user_id` int NOT NULL,
  `profile_id` int NOT NULL,
  CONSTRAINT PK_user_profile PRIMARY KEY (user_id, profile_id),
  FOREIGN KEY (user_id) REFERENCES tb_user (id),
  FOREIGN KEY (profile_id) REFERENCES tb_profile (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_album` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (user_id) REFERENCES tb_user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_photo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `web_path` varchar(255) DEFAULT NULL,
  `album_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (album_id) REFERENCES tb_album (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (user_id) REFERENCES tb_user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


